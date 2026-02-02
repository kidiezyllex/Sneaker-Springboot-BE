package com.sneaker.controller;

import com.sneaker.dto.request.PromotionCreateRequest;
import com.sneaker.dto.request.PromotionUpdateRequest;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.entity.Promotion;
import com.sneaker.service.PromotionService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
@Tag(name = "Promotions", description = "Promotion management APIs")
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Create promotion", description = "Create a new promotion (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Promotion>> createPromotion(@Valid @RequestBody PromotionCreateRequest request) {
        Promotion created = promotionService.createPromotion(request);
        return ResponseEntity.status(201).body(ApiResponse.success("Tạo chương trình khuyến mãi thành công", created));
    }

    @GetMapping
    @Operation(summary = "Get all promotions", description = "Get paginated list of promotions")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPromotions(
            @RequestParam(required = false) Promotion.Status status,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Promotion> promotions = promotionService.getPromotions(status, search, startDate, endDate, pageable);

        Map<String, Object> response = Map.of(
                "promotions", promotions.getContent(),
                "pagination", Map.of(
                        "totalItems", promotions.getTotalElements(),
                        "totalPages", promotions.getTotalPages(),
                        "currentPage", page,
                        "limit", limit));

        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách chương trình khuyến mãi thành công", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get promotion by ID", description = "Get promotion details by ID")
    public ResponseEntity<ApiResponse<Promotion>> getPromotionById(@PathVariable Integer id) {
        return promotionService.getPromotionById(id)
                .map(promotion -> ResponseEntity
                        .ok(ApiResponse.success("Lấy thông tin chương trình khuyến mãi thành công", promotion)))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error("Không tìm thấy chương trình khuyến mãi")));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Update promotion", description = "Update promotion details (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Promotion>> updatePromotion(
            @PathVariable Integer id,
            @Valid @RequestBody PromotionUpdateRequest request) {
        Promotion updated = promotionService.updatePromotion(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật chương trình khuyến mãi thành công", updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Delete promotion", description = "Delete a promotion (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Object>> deletePromotion(@PathVariable Integer id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa chương trình khuyến mãi thành công", null));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get promotions for product", description = "Get active promotions for a specific product")
    public ResponseEntity<ApiResponse<List<Promotion>>> getPromotionsByProductId(@PathVariable Integer productId) {
        List<Promotion> promotions = promotionService.getPromotionsByProductId(productId);
        return ResponseEntity.ok(ApiResponse.success("Lấy khuyến mãi của sản phẩm thành công", promotions));
    }
}
