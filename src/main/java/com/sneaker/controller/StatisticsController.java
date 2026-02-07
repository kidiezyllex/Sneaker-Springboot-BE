package com.sneaker.controller;

import com.sneaker.dto.response.ApiResponse;
import com.sneaker.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@Tag(name = "Statistics", description = "Statistics and Analytics APIs")
@SecurityRequirement(name = "bearerAuth")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get statistics", description = "Get statistics with filters (Admin/Staff only)")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getStatistics(
            @RequestParam(defaultValue = "MONTHLY") String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate endDate) {
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(java.time.LocalTime.MAX) : null;
        Map<String, Object> statistics = statisticsService.getStatistics(type, startDateTime, endDateTime);
        return ResponseEntity.ok(ApiResponse.success("Lấy thống kê thành công", statistics));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get statistic by ID", description = "Get statistic details by ID (Admin/Staff only) - Placeholder")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getStatisticById(@PathVariable Integer id) {
        Map<String, Object> response = Map.of(
                "id", id,
                "message", "API endpoint này đang được phát triển");
        return ResponseEntity.ok(ApiResponse.success("Chức năng đang được phát triển", response));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Create statistic", description = "Create a new statistic (Admin/Staff only) - Placeholder")
    public ResponseEntity<ApiResponse<Map<String, Object>>> createStatistic(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = Map.of("message", "API endpoint này đang được phát triển");
        return ResponseEntity.ok(ApiResponse.success("Chức năng đang được phát triển", response));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Update statistic", description = "Update statistic information (Admin/Staff only) - Placeholder")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateStatistic(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = Map.of(
                "id", id,
                "message", "API endpoint này đang được phát triển");
        return ResponseEntity.ok(ApiResponse.success("Chức năng đang được phát triển", response));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Delete statistic", description = "Delete statistic (Admin/Staff only) - Placeholder")
    public ResponseEntity<ApiResponse<Map<String, Object>>> deleteStatistic(@PathVariable Integer id) {
        Map<String, Object> response = Map.of(
                "id", id,
                "message", "API endpoint này đang được phát triển");
        return ResponseEntity.ok(ApiResponse.success("Chức năng đang được phát triển", response));
    }

    @GetMapping("/revenue")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get revenue report", description = "Get revenue report by period (Admin/Staff only)")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getRevenueReport(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate endDate,
            @RequestParam(defaultValue = "month") String groupBy) {
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(java.time.LocalTime.MAX) : null;
        List<Map<String, Object>> revenueData = statisticsService.getRevenueReport(startDateTime, endDateTime, groupBy);
        return ResponseEntity.ok(ApiResponse.success("Lấy báo cáo doanh thu thành công", revenueData));
    }

    @GetMapping("/top-products")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @Operation(summary = "Get top products", description = "Get top selling products (Admin/Staff only)")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getTopProducts(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate endDate,
            @RequestParam(defaultValue = "10") int limit) {
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(java.time.LocalTime.MAX) : null;
        List<Map<String, Object>> topProducts = statisticsService.getTopProducts(startDateTime, endDateTime, limit);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách sản phẩm bán chạy thành công", topProducts));
    }

    @PostMapping("/generate-daily")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Generate daily statistic", description = "Generate daily statistic (Admin only) - Placeholder")
    public ResponseEntity<ApiResponse<Map<String, Object>>> generateDailyStatistic() {
        Map<String, Object> response = Map.of("message", "API endpoint này đang được phát triển");
        return ResponseEntity.ok(ApiResponse.success("Chức năng đang được phát triển", response));
    }

    @GetMapping("/analytics")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get analytics", description = "Get analytics overview (Admin only)")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAnalytics(
            @RequestParam(defaultValue = "30") int period) {
        Map<String, Object> analytics = statisticsService.getAnalytics(period);
        return ResponseEntity.ok(ApiResponse.success("Lấy thống kê phân tích thành công", analytics));
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get dashboard stats", description = "Get dashboard statistics (Admin only)")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getDashboardStats() {
        Map<String, Object> stats = statisticsService.getDashboardStats();
        return ResponseEntity.ok(ApiResponse.success("Lấy thống kê dashboard thành công", stats));
    }
}
