package com.sneaker.service;

import com.sneaker.entity.Account;
import com.sneaker.entity.Order;
import com.sneaker.entity.Product;
import com.sneaker.repository.AccountRepository;
import com.sneaker.repository.OrderRepository;
import com.sneaker.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    
    public Map<String, Object> getStatistics(String type, LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findAll().stream()
                .filter(o -> o.getOrderStatus() == Order.OrderStatus.HOAN_THANH &&
                        (o.getPaymentStatus() == Order.PaymentStatus.PAID ||
                         o.getPaymentStatus() == Order.PaymentStatus.PARTIAL_PAID))
                .filter(o -> {
                    if (startDate != null && o.getCreatedAt().isBefore(startDate)) return false;
                    if (endDate != null && o.getCreatedAt().isAfter(endDate)) return false;
                    return true;
                })
                .toList();
        
        long totalOrders = orders.size();
        double totalRevenue = orders.stream().mapToDouble(o -> o.getTotal().doubleValue()).sum();
        double totalSubTotal = orders.stream().mapToDouble(o -> o.getSubTotal().doubleValue()).sum();
        double totalProfit = totalSubTotal * 0.3; // 30% profit margin
        double averageOrderValue = totalOrders > 0 ? totalRevenue / totalOrders : 0;
        
        long newCustomers = accountRepository.findAll().stream()
                .filter(a -> a.getRole() == Account.Role.CUSTOMER)
                .filter(a -> {
                    if (startDate != null && a.getCreatedAt().isBefore(startDate)) return false;
                    if (endDate != null && a.getCreatedAt().isAfter(endDate)) return false;
                    return true;
                })
                .count();
        
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalOrders", totalOrders);
        statistics.put("totalRevenue", totalRevenue);
        statistics.put("totalProfit", totalProfit);
        statistics.put("averageOrderValue", averageOrderValue);
        statistics.put("newCustomers", newCustomers);
        statistics.put("type", type != null ? type : "MONTHLY");
        statistics.put("date", LocalDateTime.now());
        
        return statistics;
    }
    
    public List<Map<String, Object>> getRevenueReport(LocalDateTime startDate, LocalDateTime endDate, String groupBy) {
        List<Order> orders = orderRepository.findAll().stream()
                .filter(o -> o.getOrderStatus() == Order.OrderStatus.HOAN_THANH &&
                        (o.getPaymentStatus() == Order.PaymentStatus.PAID ||
                         o.getPaymentStatus() == Order.PaymentStatus.PARTIAL_PAID))
                .filter(o -> {
                    if (startDate != null && o.getCreatedAt().isBefore(startDate)) return false;
                    if (endDate != null && o.getCreatedAt().isAfter(endDate)) return false;
                    return true;
                })
                .toList();
        
        Map<String, Map<String, Object>> grouped = new HashMap<>();
        DateTimeFormatter formatter = groupBy.equals("month") ? 
                DateTimeFormatter.ofPattern("yyyy-MM") : 
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        for (Order order : orders) {
            String period = order.getCreatedAt().format(formatter);
            grouped.computeIfAbsent(period, k -> {
                Map<String, Object> data = new HashMap<>();
                data.put("period", period);
                data.put("totalOrders", 0L);
                data.put("totalRevenue", 0.0);
                return data;
            });
            
            Map<String, Object> data = grouped.get(period);
            data.put("totalOrders", ((Long) data.get("totalOrders")) + 1);
            data.put("totalRevenue", ((Double) data.get("totalRevenue")) + order.getTotal().doubleValue());
        }
        
        return new ArrayList<>(grouped.values());
    }
    
    public List<Map<String, Object>> getTopProducts(LocalDateTime startDate, LocalDateTime endDate, int limit) {
        List<Order> orders = orderRepository.findAll().stream()
                .filter(o -> o.getOrderStatus() == Order.OrderStatus.HOAN_THANH)
                .filter(o -> {
                    if (startDate != null && o.getCreatedAt().isBefore(startDate)) return false;
                    if (endDate != null && o.getCreatedAt().isAfter(endDate)) return false;
                    return true;
                })
                .toList();
        
        Map<Integer, Map<String, Object>> productStats = new HashMap<>();
        
        for (Order order : orders) {
            for (var orderItem : order.getItems()) {
                Integer productId = orderItem.getVariant().getProduct().getId();
                Product product = orderItem.getVariant().getProduct();
                
                productStats.computeIfAbsent(productId, k -> {
                    Map<String, Object> stats = new HashMap<>();
                    stats.put("productId", productId);
                    stats.put("productName", product.getName());
                    stats.put("productCode", product.getCode());
                    stats.put("totalSold", 0);
                    stats.put("totalRevenue", 0.0);
                    return stats;
                });
                
                Map<String, Object> stats = productStats.get(productId);
                stats.put("totalSold", ((Integer) stats.get("totalSold")) + orderItem.getQuantity());
                stats.put("totalRevenue", ((Double) stats.get("totalRevenue")) + 
                        orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())).doubleValue());
            }
        }
        
        return productStats.values().stream()
                .sorted((a, b) -> ((Integer) b.get("totalSold")).compareTo((Integer) a.get("totalSold")))
                .limit(limit)
                .toList();
    }
    
    public Map<String, Object> getAnalytics(int period) {
        LocalDateTime daysAgo = LocalDateTime.now().minusDays(period);
        
        long totalOrders = orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().isAfter(daysAgo))
                .count();
        
        double totalRevenue = orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().isAfter(daysAgo) &&
                        o.getOrderStatus() == Order.OrderStatus.HOAN_THANH &&
                        (o.getPaymentStatus() == Order.PaymentStatus.PAID ||
                         o.getPaymentStatus() == Order.PaymentStatus.PARTIAL_PAID))
                .mapToDouble(o -> o.getTotal().doubleValue())
                .sum();
        
        long completedOrders = orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().isAfter(daysAgo) &&
                        o.getOrderStatus() == Order.OrderStatus.HOAN_THANH)
                .count();
        
        long pendingOrders = orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().isAfter(daysAgo) &&
                        o.getOrderStatus() == Order.OrderStatus.CHO_XAC_NHAN)
                .count();
        
        long newCustomers = accountRepository.findAll().stream()
                .filter(a -> a.getRole() == Account.Role.CUSTOMER &&
                        a.getCreatedAt().isAfter(daysAgo))
                .count();
        
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalOrders", totalOrders);
        analytics.put("totalRevenue", totalRevenue);
        analytics.put("completedOrders", completedOrders);
        analytics.put("pendingOrders", pendingOrders);
        analytics.put("newCustomers", newCustomers);
        analytics.put("period", period);
        
        return analytics;
    }
    
    public Map<String, Object> getDashboardStats() {
        LocalDateTime today = LocalDateTime.now();
        LocalDate todayDate = today.toLocalDate();
        LocalDateTime startOfToday = todayDate.atStartOfDay();
        LocalDateTime startOfMonth = todayDate.withDayOfMonth(1).atStartOfDay();
        
        long todayOrders = orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().isAfter(startOfToday) &&
                        o.getOrderStatus() == Order.OrderStatus.HOAN_THANH)
                .count();
        
        double todayRevenue = orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().isAfter(startOfToday) &&
                        o.getOrderStatus() == Order.OrderStatus.HOAN_THANH &&
                        (o.getPaymentStatus() == Order.PaymentStatus.PAID ||
                         o.getPaymentStatus() == Order.PaymentStatus.PARTIAL_PAID))
                .mapToDouble(o -> o.getTotal().doubleValue())
                .sum();
        
        long monthOrders = orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().isAfter(startOfMonth) &&
                        o.getOrderStatus() == Order.OrderStatus.HOAN_THANH)
                .count();
        
        double monthRevenue = orderRepository.findAll().stream()
                .filter(o -> o.getCreatedAt().isAfter(startOfMonth) &&
                        o.getOrderStatus() == Order.OrderStatus.HOAN_THANH &&
                        (o.getPaymentStatus() == Order.PaymentStatus.PAID ||
                         o.getPaymentStatus() == Order.PaymentStatus.PARTIAL_PAID))
                .mapToDouble(o -> o.getTotal().doubleValue())
                .sum();
        
        long totalCustomers = accountRepository.findAll().stream()
                .filter(a -> a.getRole() == Account.Role.CUSTOMER)
                .count();
        
        long totalProducts = productRepository.count();
        
        Map<String, Object> stats = new HashMap<>();
        Map<String, Object> todayStats = new HashMap<>();
        todayStats.put("orders", todayOrders);
        todayStats.put("revenue", todayRevenue);
        
        Map<String, Object> month = new HashMap<>();
        month.put("orders", monthOrders);
        month.put("revenue", monthRevenue);
        
        Map<String, Object> total = new HashMap<>();
        total.put("customers", totalCustomers);
        total.put("products", totalProducts);
        
        stats.put("today", todayStats);
        stats.put("month", month);
        stats.put("total", total);
        
        return stats;
    }
}

