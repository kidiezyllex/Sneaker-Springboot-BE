package com.sneaker.controller;

import com.sneaker.dto.request.*;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.entity.Order;
import com.sneaker.entity.Return;
import com.sneaker.service.ReturnService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
@Tag(name = "Returns", description = "Return Management APIs")
@SecurityRequirement(name = "bearerAuth")
public class ReturnController {
    
    private final ReturnService returnService;
    
    // ========== ADMIN/STAFF APIs ==========
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Create return", description = "Create a new return order (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Return>> createReturn(@Valid @RequestBody ReturnCreateRequest request) {
        Return returnOrder = returnService.createReturn(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo đơn trả hàng thành công", returnOrder));
    }
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get all returns", description = "Get paginated list of returns (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Page<Return>>> getReturns(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Return.ReturnStatus statusEnum = status != null ? 
                Return.ReturnStatus.valueOf(status) : null;
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        Page<Return> returns = returnService.getReturns(statusEnum, customerId, search, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách đơn trả hàng thành công", returns));
    }
    
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Search returns", description = "Search returns by code or order code (Admin/Staff only)")
    public ResponseEntity<ApiResponse<List<Return>>> searchReturns(
            @RequestParam(required = true) String query) {
        List<Return> returns = returnService.searchReturns(query);
        return ResponseEntity.ok(ApiResponse.success("Tìm kiếm đơn trả hàng thành công", returns));
    }
    
    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get return statistics", description = "Get return statistics (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getReturnStats(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        Map<String, Object> stats = returnService.getReturnStats(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Lấy thống kê đơn trả hàng thành công", stats));
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get return by ID", description = "Get return details by ID (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Return>> getReturnById(@PathVariable Integer id) {
        Return returnOrder = returnService.getReturnById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin đơn trả hàng thành công", returnOrder));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Update return", description = "Update return information (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Return>> updateReturn(
            @PathVariable Integer id,
            @Valid @RequestBody ReturnUpdateRequest request) {
        Return returnOrder = returnService.updateReturn(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật đơn trả hàng thành công", returnOrder));
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Update return status", description = "Update return status (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Return>> updateReturnStatus(
            @PathVariable Integer id,
            @Valid @RequestBody ReturnStatusUpdateRequest request) {
        Return returnOrder = returnService.updateReturnStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật trạng thái đơn trả hàng thành công", returnOrder));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Delete return", description = "Delete return (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Void>> deleteReturn(@PathVariable Integer id) {
        returnService.deleteReturn(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa đơn trả hàng thành công", null));
    }
    
    // ========== CUSTOMER APIs ==========
    @GetMapping("/returnable-orders")
    @Operation(summary = "Get returnable orders", description = "Get orders that can be returned (Customer only)")
    public ResponseEntity<ApiResponse<Page<Order>>> getReturnableOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        // Get current user ID from security context
        org.springframework.security.core.Authentication auth = 
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        com.sneaker.security.SecurityUser userDetails = 
                (com.sneaker.security.SecurityUser) auth.getPrincipal();
        Integer customerId = userDetails.getAccount().getId();
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Order> orders = returnService.getReturnableOrders(customerId, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách đơn hàng có thể trả thành công", orders));
    }
    
    @PostMapping("/request")
    @Operation(summary = "Create return request", description = "Create a return request (Customer only)")
    public ResponseEntity<ApiResponse<Return>> createReturnRequest(
            @Valid @RequestBody ReturnRequestRequest request) {
        Return returnOrder = returnService.createReturnRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Yêu cầu trả hàng đã được gửi thành công", returnOrder));
    }
    
    @GetMapping("/my")
    @Operation(summary = "Get my returns", description = "Get returns of current authenticated user (Customer only)")
    public ResponseEntity<ApiResponse<Page<Return>>> getMyReturns(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Return.ReturnStatus statusEnum = status != null ? 
                Return.ReturnStatus.valueOf(status) : null;
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        Page<Return> returns = returnService.getMyReturns(statusEnum, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách đơn trả hàng thành công", returns));
    }
    
    @GetMapping("/my/{id}")
    @Operation(summary = "Get my return by ID", description = "Get return details by ID (Customer only)")
    public ResponseEntity<ApiResponse<Return>> getMyReturnById(@PathVariable Integer id) {
        Return returnOrder = returnService.getMyReturnById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin đơn trả hàng thành công", returnOrder));
    }
    
    @PutMapping("/my/{id}/cancel")
    @Operation(summary = "Cancel my return", description = "Cancel return request (Customer only)")
    public ResponseEntity<ApiResponse<Void>> cancelMyReturn(@PathVariable Integer id) {
        returnService.cancelMyReturn(id);
        return ResponseEntity.ok(ApiResponse.success("Hủy yêu cầu trả hàng thành công", null));
    }
}

