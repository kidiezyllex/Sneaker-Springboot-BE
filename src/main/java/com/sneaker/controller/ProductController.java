package com.sneaker.controller;

import com.sneaker.dto.request.ProductCreateRequest;
import com.sneaker.dto.request.ProductUpdateRequest;
import com.sneaker.dto.request.StockUpdateRequest;
import com.sneaker.dto.request.ImageUpdateRequest;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.dto.response.PaginationMeta;
import com.sneaker.entity.Product;
import com.sneaker.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Product management APIs")
public class ProductController {
    
    private final ProductService productService;
    
    @GetMapping
    @Operation(summary = "Get all products", description = "Get paginated list of products with filters")
    public ResponseEntity<ApiResponse<java.util.List<Product>>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer brand,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) Integer material,
            @RequestParam(required = false) Integer color,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Product.Status status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Product> productPage = productService.getAllProducts(
            name, brand, category, material, color, size, minPrice, maxPrice, status, pageable
        );
        
        return ResponseEntity.ok(
            ApiResponse.success(
                "Lấy danh sách sản phẩm thành công",
                productPage.getContent(),
                PaginationMeta.fromPage(productPage)
            )
        );
    }
    
    @GetMapping("/filters")
    @Operation(summary = "Get product filters", description = "Get all available filters (brands, categories, colors, sizes, etc.)")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getFilters() {
        Map<String, Object> filters = productService.getProductFilters();
        return ResponseEntity.ok(ApiResponse.success("Lấy bộ lọc thành công", filters));
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search products", description = "Search products by keyword with filters")
    public ResponseEntity<ApiResponse<java.util.List<Product>>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer brand,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) Integer material,
            @RequestParam(required = false) Integer color,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Product> productPage = productService.searchProducts(
            keyword, brand, category, material, color, size, minPrice, maxPrice, null, pageable
        );
        
        return ResponseEntity.ok(
            ApiResponse.success(
                "Tìm kiếm sản phẩm thành công",
                productPage.getContent(),
                PaginationMeta.fromPage(productPage)
            )
        );
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Get product details by ID")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(ApiResponse.success("Lấy thông tin sản phẩm thành công", product)))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error("Không tìm thấy sản phẩm")));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create product", description = "Create a new product (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        Product created = productService.createProduct(request);
        return ResponseEntity.status(201).body(ApiResponse.created("Tạo sản phẩm thành công", created));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update product", description = "Update product details (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable Integer id, 
            @Valid @RequestBody ProductUpdateRequest request) {
        Product updated = productService.updateProduct(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật sản phẩm thành công", updated));
    }
    
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update product status", description = "Update product status (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Product>> updateProductStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request) {
        Product.Status status = Product.Status.valueOf(request.get("status"));
        Product updated = productService.updateProductStatus(id, status);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật trạng thái sản phẩm thành công", updated));
    }
    
    @PatchMapping("/{id}/stock")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update product stock", description = "Update stock for product variants (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Product>> updateProductStock(
            @PathVariable Integer id,
            @Valid @RequestBody StockUpdateRequest request) {
        Product updated = productService.updateProductStock(id, request.getVariantUpdates());
        return ResponseEntity.ok(ApiResponse.success("Cập nhật tồn kho thành công", updated));
    }
    
    @PatchMapping("/{id}/images")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update product images", description = "Update images for a product variant (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Product>> updateProductImages(
            @PathVariable Integer id,
            @Valid @RequestBody ImageUpdateRequest request) {
        Product updated = productService.updateProductImages(id, request.getVariantId(), request.getImages());
        return ResponseEntity.ok(ApiResponse.success("Cập nhật hình ảnh sản phẩm thành công", updated));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete product", description = "Delete a product (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Object>> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa sản phẩm thành công", null));
    }
}

