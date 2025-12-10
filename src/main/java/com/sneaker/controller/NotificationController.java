package com.sneaker.controller;

import com.sneaker.dto.request.NotificationCreateRequest;
import com.sneaker.dto.request.NotificationUpdateRequest;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.entity.Notification;
import com.sneaker.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "Notification Management APIs")
@SecurityRequirement(name = "bearerAuth")
public class NotificationController {
    
    private final NotificationService notificationService;
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create notification", description = "Create a new notification (Admin only)")
    public ResponseEntity<ApiResponse<Notification>> createNotification(
            @Valid @RequestBody NotificationCreateRequest request) {
        Notification notification = notificationService.createNotification(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo thông báo thành công", notification));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all notifications", description = "Get paginated list of notifications (Admin only)")
    public ResponseEntity<ApiResponse<Page<Notification>>> getNotifications(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer accountId,
            @RequestParam(required = false) Boolean isRead,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Notification.NotificationType typeEnum = type != null ? 
                Notification.NotificationType.valueOf(type) : null;
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        Page<Notification> notifications = notificationService.getNotifications(
                typeEnum, accountId, isRead, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách thông báo thành công", notifications));
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get notification by ID", description = "Get notification details by ID (Admin only)")
    public ResponseEntity<ApiResponse<Notification>> getNotificationById(@PathVariable Integer id) {
        Notification notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin thông báo thành công", notification));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update notification", description = "Update notification information (Admin only)")
    public ResponseEntity<ApiResponse<Notification>> updateNotification(
            @PathVariable Integer id,
            @Valid @RequestBody NotificationUpdateRequest request) {
        Notification notification = notificationService.updateNotification(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thông báo thành công", notification));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete notification", description = "Delete notification (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteNotification(@PathVariable Integer id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa thông báo thành công", null));
    }
    
    @PostMapping("/{id}/send")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Send notification", description = "Send notification (Admin only)")
    public ResponseEntity<ApiResponse<Notification>> sendNotification(@PathVariable Integer id) {
        Notification notification = notificationService.getNotificationById(id);
        // Logic gửi thông báo có thể được implement ở đây
        return ResponseEntity.ok(ApiResponse.success("Gửi thông báo thành công", notification));
    }
    
    @GetMapping("/user")
    @Operation(summary = "Get user notifications", description = "Get notifications for current authenticated user")
    public ResponseEntity<ApiResponse<Page<Notification>>> getUserNotifications(
            @RequestParam(required = false) Boolean isRead,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Notification> notifications = notificationService.getUserNotifications(isRead, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách thông báo thành công", notifications));
    }
    
    @PostMapping("/send-all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Send notification to all customers", description = "Send notification to all customers (Admin only)")
    public ResponseEntity<ApiResponse<Void>> sendNotificationToAllCustomers(
            @Valid @RequestBody NotificationCreateRequest request) {
        notificationService.sendNotificationToAllCustomers(request);
        return ResponseEntity.ok(ApiResponse.success("Đã gửi thông báo cho tất cả khách hàng", null));
    }
    
    @PatchMapping("/{id}/mark-read")
    @Operation(summary = "Mark notification as read", description = "Mark notification as read for current user")
    public ResponseEntity<ApiResponse<Notification>> markAsRead(@PathVariable Integer id) {
        Notification notification = notificationService.markAsRead(id);
        return ResponseEntity.ok(ApiResponse.success("Đánh dấu thông báo đã đọc thành công", notification));
    }
    
    @PatchMapping("/mark-all-read")
    @Operation(summary = "Mark all notifications as read", description = "Mark all notifications as read for current user")
    public ResponseEntity<ApiResponse<Void>> markAllAsRead() {
        notificationService.markAllAsRead();
        return ResponseEntity.ok(ApiResponse.success("Đánh dấu tất cả thông báo đã đọc thành công", null));
    }
}

