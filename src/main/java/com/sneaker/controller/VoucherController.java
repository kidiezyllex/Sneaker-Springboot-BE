package com.sneaker.controller;

import com.sneaker.dto.request.VoucherCreateRequest;
import com.sneaker.dto.request.VoucherUpdateRequest;
import com.sneaker.dto.request.VoucherValidateRequest;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.entity.Voucher;
import com.sneaker.security.SecurityUser;
import com.sneaker.service.VoucherService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vouchers")
@RequiredArgsConstructor
@Tag(name = "Vouchers", description = "Voucher management APIs")
public class VoucherController {
    
    private final VoucherService voucherService;
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create voucher", description = "Create a new voucher (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Voucher>> createVoucher(@Valid @RequestBody VoucherCreateRequest request) {
        Voucher created = voucherService.createVoucher(request);
        return ResponseEntity.status(201).body(ApiResponse.success("Tạo phiếu giảm giá thành công", created));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all vouchers", description = "Get paginated list of vouchers (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getVouchers(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Voucher.Status status,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Voucher> vouchers = voucherService.getVouchers(code, name, status, startDate, endDate, pageable);
        
        Map<String, Object> response = Map.of(
            "vouchers", vouchers.getContent(),
            "pagination", Map.of(
                "totalItems", vouchers.getTotalElements(),
                "totalPages", vouchers.getTotalPages(),
                "currentPage", page,
                "limit", limit
            )
        );
        
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách phiếu giảm giá thành công", response));
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get voucher by ID", description = "Get voucher details by ID (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Voucher>> getVoucherById(@PathVariable Integer id) {
        return voucherService.getVoucherById(id)
                .map(voucher -> ResponseEntity.ok(ApiResponse.success("Lấy thông tin phiếu giảm giá thành công", voucher)))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error("Không tìm thấy phiếu giảm giá")));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update voucher", description = "Update voucher details (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Voucher>> updateVoucher(
            @PathVariable Integer id,
            @Valid @RequestBody VoucherUpdateRequest request) {
        Voucher updated = voucherService.updateVoucher(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật phiếu giảm giá thành công", updated));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete voucher", description = "Delete a voucher (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Object>> deleteVoucher(@PathVariable Integer id) {
        voucherService.deleteVoucher(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa phiếu giảm giá thành công", null));
    }
    
    @PostMapping("/validate")
    @Operation(summary = "Validate voucher", description = "Check if a voucher is valid and calculate discount")
    public ResponseEntity<ApiResponse<Map<String, Object>>> validateVoucher(@Valid @RequestBody VoucherValidateRequest request) {
        Map<String, Object> result = voucherService.validateVoucher(request);
        return ResponseEntity.ok(ApiResponse.success("Voucher hợp lệ", result));
    }
    
    @PutMapping("/{id}/increment-usage")
    @Operation(summary = "Increment voucher usage", description = "Increase voucher usage count")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Voucher>> incrementVoucherUsage(@PathVariable Integer id) {
        Voucher updated = voucherService.incrementVoucherUsage(id);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật số lần sử dụng voucher thành công", updated));
    }
    
    @PostMapping("/{id}/notify")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Notify voucher", description = "Send notification about voucher to all customers (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Map<String, Object>>> notifyVoucher(
            @PathVariable Integer id,
            @RequestBody(required = false) Map<String, String> request) {
        String title = request != null ? request.get("title") : null;
        String message = request != null ? request.get("message") : null;
        Map<String, Object> result = voucherService.notifyVoucher(id, title, message);
        return ResponseEntity.ok(ApiResponse.success("Đã gửi thông báo voucher", result));
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get available vouchers for user", description = "Get list of available vouchers for a user")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getAvailableVouchersForUser(
            @PathVariable Integer userId,
            @RequestParam(required = false) BigDecimal orderValue,
            @AuthenticationPrincipal SecurityUser user) {
        // Verify user can only access their own vouchers
        if (!user.getId().equals(userId) && !user.getAccount().getRole().equals(com.sneaker.entity.Account.Role.ADMIN)) {
            return ResponseEntity.status(403).body(ApiResponse.error("Không có quyền truy cập"));
        }
        
        List<Map<String, Object>> vouchers = voucherService.getAvailableVouchersForUser(orderValue);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách phiếu giảm giá có sẵn thành công", vouchers));
    }
}

