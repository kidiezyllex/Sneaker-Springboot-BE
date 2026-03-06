package com.sneaker.service;

import com.sneaker.entity.*;
import com.sneaker.repository.*;
import com.sneaker.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantImageRepository productVariantImageRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final MaterialRepository materialRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final CodeGenerator codeGenerator;

    public Page<Product> getAllProducts(String name, Integer brand, Integer category,
            Integer material, Integer color, Integer size,
            BigDecimal minPrice, BigDecimal maxPrice,
            Product.Status status, Pageable pageable) {
        return productRepository.findWithFilters(name, brand, category, material, status, pageable);
    }

    public Page<Product> searchProducts(String keyword, Integer brand, Integer category,
            Integer material, Integer color, Integer size,
            BigDecimal minPrice, BigDecimal maxPrice,
            Product.Status status, Pageable pageable) {
        Page<Product> products = productRepository.searchWithFilters(
                keyword, brand, category, material, status, pageable);

        return products;
    }

    public Map<String, Object> getProductFilters() {
        Map<String, Object> filters = new HashMap<>();

        // Basic filters
        filters.put("brands", productRepository.findAll().stream()
                .map(p -> p.getBrand())
                .distinct()
                .filter(b -> b.getStatus() == com.sneaker.entity.Brand.Status.ACTIVE)
                .toList());

        filters.put("categories", productRepository.findAll().stream()
                .map(p -> p.getCategory())
                .distinct()
                .filter(c -> c.getStatus() == com.sneaker.entity.Category.Status.ACTIVE)
                .toList());

        filters.put("materials", productRepository.findAll().stream()
                .map(p -> p.getMaterial())
                .distinct()
                .filter(m -> m.getStatus() == com.sneaker.entity.Material.Status.ACTIVE)
                .toList());

        // Variant filters
        List<ProductVariant> allVariants = productVariantRepository.findAll();

        filters.put("colors", allVariants.stream()
                .map(v -> v.getColor())
                .distinct()
                .filter(c -> c.getStatus() == com.sneaker.entity.Color.Status.ACTIVE)
                .toList());

        filters.put("sizes", allVariants.stream()
                .map(v -> v.getSize())
                .distinct()
                .filter(s -> s.getStatus() == com.sneaker.entity.Size.Status.ACTIVE)
                .sorted(Comparator.comparing(com.sneaker.entity.Size::getValue))
                .toList());

        // Price range
        Optional<BigDecimal> minPrice = allVariants.stream()
                .map(ProductVariant::getPrice)
                .min(BigDecimal::compareTo);

        Optional<BigDecimal> maxPrice = allVariants.stream()
                .map(ProductVariant::getPrice)
                .max(BigDecimal::compareTo);

        Map<String, BigDecimal> priceRange = new HashMap<>();
        priceRange.put("min", minPrice.orElse(BigDecimal.ZERO));
        priceRange.put("max", maxPrice.orElse(BigDecimal.ZERO));
        filters.put("priceRange", priceRange);

        return filters;
    }

    public List<Product> getLatestProducts() {
        return productRepository.findTop4ByStatusOrderByCreatedAtDesc(Product.Status.ACTIVE);
    }

    public List<Product> getBestSellingProducts() {
        return productRepository.findRandomTop4ByStatus(Product.Status.ACTIVE.name());
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Transactional
    public Product createProduct(com.sneaker.dto.request.ProductCreateRequest request) {
        // Find brand, category, material
        Brand brand = resolveBrand(request.getBrand());
        Category category = resolveCategory(request.getCategory());
        Material material = resolveMaterial(request.getMaterial());

        // Create product
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription() != null ? request.getDescription() : "");
        product.setWeight(request.getWeight() != null ? request.getWeight() : new BigDecimal("0.5"));
        product.setBrand(brand);
        product.setCategory(category);
        product.setMaterial(material);
        product.setStatus(Product.Status.ACTIVE);
        product.setCode(codeGenerator.generateProductCode());

        product = productRepository.save(product);

        // Create variants
        for (com.sneaker.dto.request.ProductCreateRequest.VariantRequest variantReq : request.getVariants()) {
            Color color = resolveColor(variantReq.getColorId());
            Size size = resolveSize(variantReq.getSizeId());

            ProductVariant variant = new ProductVariant();
            variant.setProduct(product);
            variant.setColor(color);
            variant.setSize(size);
            variant.setPrice(variantReq.getPrice());
            variant.setStock(variantReq.getStock() != null ? variantReq.getStock() : 0);
            variant = productVariantRepository.save(variant);

            // Create images
            if (variantReq.getImages() != null && !variantReq.getImages().isEmpty()) {
                for (String imageUrl : variantReq.getImages()) {
                    ProductVariantImage image = new ProductVariantImage();
                    image.setVariant(variant);
                    image.setImageUrl(imageUrl);
                    productVariantImageRepository.save(image);
                }
            }
        }

        // Return product with all details
        return productRepository.findById(product.getId())
                .orElse(product);
    }

    private Brand resolveBrand(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return brandRepository.findById(((Number) value).intValue())
                    .orElseThrow(() -> new RuntimeException("Thương hiệu không tồn tại với ID: " + value));
        }
        String str = value.toString();
        try {
            Integer id = Integer.parseInt(str);
            return brandRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Thương hiệu không tồn tại với ID: " + id));
        } catch (NumberFormatException e) {
            return brandRepository.findByName(str)
                    .orElseThrow(() -> new RuntimeException("Thương hiệu không tồn tại với tên: " + str));
        }
    }

    private Category resolveCategory(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return categoryRepository.findById(((Number) value).intValue())
                    .orElseThrow(() -> new RuntimeException("Danh mục không tồn tại với ID: " + value));
        }
        String str = value.toString();
        try {
            Integer id = Integer.parseInt(str);
            return categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Danh mục không tồn tại với ID: " + id));
        } catch (NumberFormatException e) {
            return categoryRepository.findByName(str)
                    .orElseThrow(() -> new RuntimeException("Danh mục không tồn tại với tên: " + str));
        }
    }

    private Material resolveMaterial(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return materialRepository.findById(((Number) value).intValue())
                    .orElseThrow(() -> new RuntimeException("Chất liệu không tồn tại với ID: " + value));
        }
        String str = value.toString();
        try {
            Integer id = Integer.parseInt(str);
            return materialRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Chất liệu không tồn tại với ID: " + id));
        } catch (NumberFormatException e) {
            return materialRepository.findByName(str)
                    .orElseThrow(() -> new RuntimeException("Chất liệu không tồn tại với tên: " + str));
        }
    }

    private Color resolveColor(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return colorRepository.findById(((Number) value).intValue())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy màu sắc với ID: " + value));
        }
        String str = value.toString();
        try {
            Integer id = Integer.parseInt(str);
            return colorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy màu sắc với ID: " + id));
        } catch (NumberFormatException e) {
            return colorRepository.findByName(str)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy màu sắc với tên: " + str));
        }
    }

    private Size resolveSize(Object value) {
        if (value == null) return null;
        if (value instanceof Number) {
            return sizeRepository.findById(((Number) value).intValue())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy kích cỡ với ID: " + value));
        }
        String str = value.toString();
        try {
            Integer id = Integer.parseInt(str);
            return sizeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy kích cỡ với ID: " + id));
        } catch (NumberFormatException e) {
            try {
                // If it's not a numeric ID, maybe it's a value (like size 42) represented as a string
                Integer sizeVal = Integer.parseInt(str);
                return sizeRepository.findByValue(sizeVal)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy kích cỡ với giá trị: " + str));
            } catch (NumberFormatException e2) {
                throw new RuntimeException("Giá trị kích cỡ không hợp lệ: " + str);
            }
        }
    }

    @Transactional
    public Product updateProduct(Integer id, com.sneaker.dto.request.ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        // Update basic info
        if (request.getName() != null)
            product.setName(request.getName());
        if (request.getDescription() != null)
            product.setDescription(request.getDescription());
        if (request.getWeight() != null)
            product.setWeight(request.getWeight());
        if (request.getStatus() != null) {
            product.setStatus(Product.Status.valueOf(request.getStatus()));
        }

        // Update brand, category, material
        if (request.getBrand() != null) {
            product.setBrand(resolveBrand(request.getBrand()));
        }
        if (request.getCategory() != null) {
            product.setCategory(resolveCategory(request.getCategory()));
        }
        if (request.getMaterial() != null) {
            product.setMaterial(resolveMaterial(request.getMaterial()));
        }

        product = productRepository.save(product);

        // Update variants if provided
        if (request.getVariants() != null && !request.getVariants().isEmpty()) {
            // Delete old variants
            productVariantRepository.deleteAll(product.getVariants());

            // Create new variants
            for (com.sneaker.dto.request.ProductUpdateRequest.VariantRequest variantReq : request.getVariants()) {
                Color color = resolveColor(variantReq.getColorId());
                Size size = resolveSize(variantReq.getSizeId());

                ProductVariant variant = new ProductVariant();
                variant.setProduct(product);
                variant.setColor(color);
                variant.setSize(size);
                variant.setPrice(variantReq.getPrice());
                variant.setStock(variantReq.getStock() != null ? variantReq.getStock()
                        : (variantReq.getQuantity() != null ? variantReq.getQuantity() : 0));
                variant = productVariantRepository.save(variant);

                // Create images
                if (variantReq.getImages() != null && !variantReq.getImages().isEmpty()) {
                    for (String imageUrl : variantReq.getImages()) {
                        ProductVariantImage image = new ProductVariantImage();
                        image.setVariant(variant);
                        image.setImageUrl(imageUrl);
                        productVariantImageRepository.save(image);
                    }
                }
            }
        }

        return productRepository.findById(id).orElse(product);
    }

    @Transactional
    public Product updateProductStatus(Integer id, Product.Status status) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(status);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProductStock(Integer id,
            List<com.sneaker.dto.request.StockUpdateRequest.VariantStockUpdate> variantUpdates) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        for (com.sneaker.dto.request.StockUpdateRequest.VariantStockUpdate update : variantUpdates) {
            ProductVariant variant = productVariantRepository.findById(update.getVariantId())
                    .orElseThrow(
                            () -> new RuntimeException("Không tìm thấy biến thể với ID: " + update.getVariantId()));

            if (!variant.getProduct().getId().equals(id)) {
                throw new RuntimeException("Biến thể không thuộc về sản phẩm này");
            }

            variant.setStock(update.getStock());
            productVariantRepository.save(variant);
        }

        return productRepository.findById(id).orElse(product);
    }

    @Transactional
    public Product updateProductImages(Integer id, Integer variantId, List<String> images) {
        // Verify product exists
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        if (!variant.getProduct().getId().equals(id)) {
            throw new RuntimeException("Variant does not belong to this product");
        }

        // Validate: Mỗi biến thể chỉ được phép có 1 ảnh duy nhất
        if (images == null || images.isEmpty()) {
            throw new RuntimeException("Danh sách ảnh không được để trống");
        }

        if (images.size() > 1) {
            throw new RuntimeException(
                    "Mỗi biến thể chỉ được phép có 1 ảnh duy nhất. Bạn đã cung cấp " + images.size() + " ảnh.");
        }

        // Delete existing images by variantId (sử dụng query để đảm bảo xóa tất cả)
        productVariantImageRepository.deleteByVariantId(variantId);
        productVariantImageRepository.flush(); // Flush để đảm bảo xóa được commit trước khi thêm mới

        // Add only the first image (since we validated it's the only one)
        String imageUrl = images.get(0);
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new RuntimeException("URL ảnh không được để trống");
        }

        ProductVariantImage image = new ProductVariantImage();
        image.setVariant(variant);
        image.setImageUrl(imageUrl.trim());
        productVariantImageRepository.saveAndFlush(image);

        // Refresh product để load lại dữ liệu mới nhất
        productRepository.flush();
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found after update"));
    }

    @Transactional
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
