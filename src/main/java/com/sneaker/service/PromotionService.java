package com.sneaker.service;

import com.sneaker.dto.request.PromotionCreateRequest;
import com.sneaker.dto.request.PromotionUpdateRequest;
import com.sneaker.entity.Product;
import com.sneaker.entity.Promotion;
import com.sneaker.repository.ProductRepository;
import com.sneaker.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Promotion createPromotion(PromotionCreateRequest request) {
        // Validate discount percent
        if (request.getDiscountPercent().compareTo(BigDecimal.ZERO) < 0 ||
                request.getDiscountPercent().compareTo(new BigDecimal("100")) > 0) {
            throw new RuntimeException("Phần trăm giảm giá phải từ 0 đến 100");
        }

        // Validate dates
        if (request.getStartDate().isAfter(request.getEndDate()) ||
                request.getStartDate().isEqual(request.getEndDate())) {
            throw new RuntimeException("Thời gian kết thúc phải sau thời gian bắt đầu");
        }

        // Validate products if provided
        if (request.getProducts() != null && !request.getProducts().isEmpty()) {
            List<Product> existingProducts = productRepository.findAllById(request.getProducts());
            if (existingProducts.size() != request.getProducts().size()) {
                throw new RuntimeException("Một số sản phẩm không tồn tại");
            }
        }

        Promotion promotion = new Promotion();
        promotion.setName(request.getName());
        promotion.setDescription(request.getDescription());
        promotion.setDiscountPercent(request.getDiscountPercent());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        promotion.setStatus(Promotion.Status.ACTIVE);

        promotion = promotionRepository.save(promotion);

        // Set products if provided
        if (request.getProducts() != null && !request.getProducts().isEmpty()) {
            List<Product> products = productRepository.findAllById(request.getProducts());
            promotion.setProducts(products);
            promotion = promotionRepository.save(promotion);
        }

        return promotion;
    }

    public Page<Promotion> getPromotions(Promotion.Status status, String search,
            LocalDateTime startDate, LocalDateTime endDate,
            Pageable pageable) {
        // Note: Need to implement with Specification or custom query
        return promotionRepository.findAll(pageable);
    }

    public Optional<Promotion> getPromotionById(Integer id) {
        return promotionRepository.findById(id);
    }

    @Transactional
    public Promotion updatePromotion(Integer id, PromotionUpdateRequest request) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chương trình khuyến mãi"));

        if (request.getName() != null)
            promotion.setName(request.getName());
        if (request.getDescription() != null)
            promotion.setDescription(request.getDescription());
        if (request.getDiscountPercent() != null) {
            if (request.getDiscountPercent().compareTo(BigDecimal.ZERO) < 0 ||
                    request.getDiscountPercent().compareTo(new BigDecimal("100")) > 0) {
                throw new RuntimeException("Phần trăm giảm giá phải từ 0 đến 100");
            }
            promotion.setDiscountPercent(request.getDiscountPercent());
        }
        if (request.getStartDate() != null)
            promotion.setStartDate(request.getStartDate());
        if (request.getEndDate() != null)
            promotion.setEndDate(request.getEndDate());
        if (request.getStatus() != null)
            promotion.setStatus(request.getStatus());

        // Validate dates if both provided
        if (request.getStartDate() != null && request.getEndDate() != null) {
            if (request.getStartDate().isAfter(request.getEndDate()) ||
                    request.getStartDate().isEqual(request.getEndDate())) {
                throw new RuntimeException("Thời gian kết thúc phải sau thời gian bắt đầu");
            }
        }

        // Update products if provided
        if (request.getProducts() != null) {
            List<Product> products = productRepository.findAllById(request.getProducts());
            promotion.setProducts(products);
        }

        return promotionRepository.save(promotion);
    }

    @Transactional
    public void deletePromotion(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chương trình khuyến mãi"));
        promotionRepository.delete(promotion);
    }

    public List<Promotion> getPromotionsByProductId(Integer productId) {
        return promotionRepository.findActivePromotions(LocalDateTime.now()).stream()
                .filter(promotion -> promotion.getProducts() != null &&
                        promotion.getProducts().stream()
                                .anyMatch(product -> product.getId().equals(productId)))
                .toList();
    }
}
