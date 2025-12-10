package com.sneaker.controller;

import com.sneaker.dto.request.*;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.entity.Payment;
import com.sneaker.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Payments", description = "Payment Management APIs")
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {
    
    private final PaymentService paymentService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Create payment", description = "Create a new payment (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Payment>> createPayment(@Valid @RequestBody PaymentCreateRequest request) {
        Payment payment = paymentService.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo thanh toán thành công", payment));
    }
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get all payments", description = "Get paginated list of payments (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Page<Payment>>> getPayments(
            @RequestParam(required = false) Integer orderId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String method,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Payment.PaymentStatus statusEnum = status != null ? 
                Payment.PaymentStatus.valueOf(status) : null;
        Payment.PaymentMethod methodEnum = method != null ? 
                Payment.PaymentMethod.valueOf(method) : null;
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        Page<Payment> payments = paymentService.getPayments(
                orderId, statusEnum, methodEnum, fromDate, toDate, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách thanh toán thành công", payments));
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get payment by ID", description = "Get payment details by ID (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Payment>> getPaymentById(@PathVariable Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin thanh toán thành công", payment));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Update payment status", description = "Update payment status (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Payment>> updatePaymentStatus(
            @PathVariable Integer id,
            @Valid @RequestBody PaymentStatusUpdateRequest request) {
        Payment payment = paymentService.updatePaymentStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật trạng thái thanh toán thành công", payment));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Delete payment", description = "Delete payment (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Void>> deletePayment(@PathVariable Integer id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa thanh toán thành công", null));
    }
    
    @GetMapping("/orders/{orderId}/payments")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get payments by order ID", description = "Get payments for a specific order (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Page<Payment>>> getPaymentsByOrderId(
            @PathVariable Integer orderId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Payment> payments = paymentService.getPaymentsByOrderId(orderId, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách thanh toán theo đơn hàng thành công", payments));
    }
    
    @PostMapping("/cod")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Create COD payment", description = "Create a COD (Cash on Delivery) payment (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Payment>> createCODPayment(@Valid @RequestBody CODPaymentRequest request) {
        Payment payment = paymentService.createCODPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo thanh toán COD thành công", payment));
    }
}

