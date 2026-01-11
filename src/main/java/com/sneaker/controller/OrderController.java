package com.sneaker.controller;

import com.sneaker.dto.request.OrderCreateRequest;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.dto.response.OrderResponse;
import com.sneaker.entity.Account;
import com.sneaker.entity.Order;
import com.sneaker.security.SecurityUser;
import com.sneaker.service.OrderService;
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

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Order management APIs")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create order", description = "Create a new order")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid @RequestBody OrderCreateRequest request,
            @AuthenticationPrincipal SecurityUser user) {
        Order created = orderService.createOrder(request, user);
        return ResponseEntity.status(201)
                .body(ApiResponse.success("Tạo đơn hàng thành công", OrderResponse.from(created)));
    }

    @PostMapping("/pos")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Create POS order", description = "Create order from POS system (Admin/Staff only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Order>> createPosOrder(
            @Valid @RequestBody OrderCreateRequest request,
            @AuthenticationPrincipal SecurityUser user) {
        Order created = orderService.createOrder(request, user);
        return ResponseEntity.status(201).body(ApiResponse.success("Tạo đơn hàng POS thành công", created));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all orders", description = "Get paginated list of orders (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getOrders(
            @RequestParam(required = false) Integer customer,
            @RequestParam(required = false) Order.OrderStatus orderStatus,
            @RequestParam(required = false) Order.PaymentStatus paymentStatus,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Order> orders = orderService.getOrders(customer, orderStatus, paymentStatus, startDate, endDate, search,
                pageable);

        Map<String, Object> response = Map.of(
                "orders", orders.getContent(),
                "pagination", Map.of(
                        "totalItems", orders.getTotalElements(),
                        "totalPages", orders.getTotalPages(),
                        "currentPage", page,
                        "limit", limit));

        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách đơn hàng thành công", response));
    }

    @GetMapping("/my-orders")
    @Operation(summary = "Get my orders", description = "Get orders of current user")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getMyOrders(
            @RequestParam(required = false) Order.OrderStatus orderStatus,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @AuthenticationPrincipal SecurityUser user) {

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Order> orders = orderService.getMyOrders(user.getId(), orderStatus, pageable);

        Map<String, Object> response = Map.of(
                "orders", orders.getContent(),
                "pagination", Map.of(
                        "totalItems", orders.getTotalElements(),
                        "totalPages", orders.getTotalPages(),
                        "currentPage", page,
                        "limit", limit));

        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách đơn hàng thành công", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Get order details by ID")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Order>> getOrderById(
            @PathVariable Integer id,
            @AuthenticationPrincipal SecurityUser user) {
        return orderService.getOrderById(id)
                .map(order -> {
                    // Check if user has access to this order
                    if (!order.getCustomer().getId().equals(user.getId()) &&
                            user.getAccount().getRole() != Account.Role.ADMIN &&
                            user.getAccount().getRole() != Account.Role.STAFF) {
                        return ResponseEntity.status(403).body(ApiResponse.<Order>error("Không có quyền truy cập"));
                    }
                    return ResponseEntity.ok(ApiResponse.success("Lấy thông tin đơn hàng thành công", order));
                })
                .orElse(ResponseEntity.status(404).body(ApiResponse.error("Không tìm thấy đơn hàng")));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update order", description = "Update order details (Admin only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Order>> updateOrder(
            @PathVariable Integer id,
            @RequestBody Order orderDetails) {
        Order updated = orderService.updateOrder(id, orderDetails);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật đơn hàng thành công", updated));
    }

    @PatchMapping("/{id}/cancel")
    @Operation(summary = "Cancel order", description = "Cancel an order")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Order>> cancelOrder(
            @PathVariable Integer id,
            @AuthenticationPrincipal SecurityUser user) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        // Check if user can cancel this order
        if (!order.getCustomer().getId().equals(user.getId()) &&
                user.getAccount().getRole() != Account.Role.ADMIN) {
            return ResponseEntity.status(403).body(ApiResponse.error("Không có quyền hủy đơn hàng này"));
        }

        Order cancelled = orderService.cancelOrder(id);
        return ResponseEntity.ok(ApiResponse.success("Hủy đơn hàng thành công", cancelled));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Update order status", description = "Update order status (Admin/Staff only)")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<ApiResponse<Order>> updateOrderStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request) {
        Order.OrderStatus status = Order.OrderStatus.valueOf(request.get("status"));
        Order updated = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật trạng thái đơn hàng thành công", updated));
    }
}
