package com.sneaker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sneaker.dto.request.*;
import com.sneaker.entity.*;
import com.sneaker.repository.*;
import com.sneaker.security.SecurityUser;
import com.sneaker.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReturnService {
    
    private final ReturnRepository returnRepository;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CodeGenerator codeGenerator;
    private final ObjectMapper objectMapper;
    
    @Transactional
    public Return createReturn(ReturnCreateRequest request) {
        Order originalOrder = orderRepository.findById(request.getOriginalOrderId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng gốc"));
        
        if (originalOrder.getOrderStatus() != Order.OrderStatus.HOAN_THANH) {
            throw new RuntimeException("Chỉ được trả hàng cho đơn hàng đã hoàn thành");
        }
        
        Account customer = accountRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
        
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Account staff = userDetails.getAccount();
        
        // Validate items
        for (ReturnCreateRequest.ReturnItemRequest item : request.getItems()) {
            OrderItem orderItem = originalOrder.getItems().stream()
                    .filter(oi -> oi.getVariant().getProduct().getId().equals(item.getProductId()) &&
                            oi.getVariant().getId().equals(item.getProductVariantId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Sản phẩm không tồn tại trong đơn hàng gốc: " + item.getProductId()));
            
            if (item.getQuantity() > orderItem.getQuantity()) {
                throw new RuntimeException(
                        "Số lượng trả (" + item.getQuantity() + ") vượt quá số lượng trong đơn hàng (" +
                        orderItem.getQuantity() + ")");
            }
        }
        
        Return returnOrder = new Return();
        returnOrder.setCode(codeGenerator.generateReturnCode());
        returnOrder.setOriginalOrder(originalOrder);
        returnOrder.setCustomer(customer);
        returnOrder.setStaff(staff);
        returnOrder.setTotalRefund(request.getTotalRefund());
        returnOrder.setReason(request.getReason());
        returnOrder.setStatus(Return.ReturnStatus.CHO_XU_LY);
        
        try {
            String itemsJson = objectMapper.writeValueAsString(request.getItems());
            returnOrder.setItems(itemsJson);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi serialize items: " + e.getMessage());
        }
        
        return returnRepository.save(returnOrder);
    }
    
    public Page<Return> getReturns(Return.ReturnStatus status, Integer customerId, 
                                   String search, Pageable pageable) {
        return returnRepository.findWithFilters(status, customerId, search, pageable);
    }
    
    public Return getReturnById(Integer id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn trả hàng"));
    }
    
    @Transactional
    public Return updateReturn(Integer id, ReturnUpdateRequest request) {
        Return returnOrder = getReturnById(id);
        
        if (returnOrder.getStatus() != Return.ReturnStatus.CHO_XU_LY) {
            throw new RuntimeException("Chỉ có thể cập nhật đơn trả hàng ở trạng thái CHO_XU_LY");
        }
        
        if (request.getItems() != null) {
            try {
                String itemsJson = objectMapper.writeValueAsString(request.getItems());
                returnOrder.setItems(itemsJson);
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi serialize items: " + e.getMessage());
            }
        }
        
        if (request.getTotalRefund() != null) {
            returnOrder.setTotalRefund(request.getTotalRefund());
        }
        
        return returnRepository.save(returnOrder);
    }
    
    @Transactional
    public Return updateReturnStatus(Integer id, ReturnStatusUpdateRequest request) {
        Return returnOrder = getReturnById(id);
        
        try {
            Return.ReturnStatus newStatus = Return.ReturnStatus.valueOf(request.getStatus());
            returnOrder.setStatus(newStatus);
            
            // Nếu trạng thái là DA_HOAN_TIEN, cập nhật lại stock
            if (newStatus == Return.ReturnStatus.DA_HOAN_TIEN && returnOrder.getItems() != null) {
                try {
                    List<Map<String, Object>> items = objectMapper.readValue(
                            returnOrder.getItems(), new TypeReference<List<Map<String, Object>>>() {});
                    
                    for (Map<String, Object> item : items) {
                        Integer variantId = (Integer) item.get("productVariantId");
                        Integer quantity = (Integer) item.get("quantity");
                        
                        ProductVariant variant = productVariantRepository.findById(variantId)
                                .orElseThrow(() -> new RuntimeException("Không tìm thấy variant: " + variantId));
                        
                        variant.setStock(variant.getStock() + quantity);
                        productVariantRepository.save(variant);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Lỗi khi cập nhật stock: " + e.getMessage());
                }
            }
            
            return returnRepository.save(returnOrder);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Trạng thái không hợp lệ");
        }
    }
    
    @Transactional
    public void deleteReturn(Integer id) {
        Return returnOrder = getReturnById(id);
        
        if (returnOrder.getStatus() != Return.ReturnStatus.CHO_XU_LY) {
            throw new RuntimeException("Chỉ có thể xóa đơn trả hàng ở trạng thái CHO_XU_LY");
        }
        
        returnRepository.delete(returnOrder);
    }
    
    public List<Return> searchReturns(String query) {
        // Search by code or original order code
        return returnRepository.findWithFilters(null, null, query, Pageable.unpaged()).getContent();
    }
    
    public Map<String, Object> getReturnStats(LocalDateTime startDate, LocalDateTime endDate) {
        long totalReturns = returnRepository.countByDateRange(startDate, endDate);
        long pendingReturns = returnRepository.countByStatusAndDateRange(
                Return.ReturnStatus.CHO_XU_LY, startDate, endDate);
        long refundedReturns = returnRepository.countByStatusAndDateRange(
                Return.ReturnStatus.DA_HOAN_TIEN, startDate, endDate);
        long cancelledReturns = returnRepository.countByStatusAndDateRange(
                Return.ReturnStatus.DA_HUY, startDate, endDate);
        Double totalRefundAmount = returnRepository.sumTotalRefundByDateRange(startDate, endDate);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalReturns", totalReturns);
        stats.put("pendingReturns", pendingReturns);
        stats.put("refundedReturns", refundedReturns);
        stats.put("cancelledReturns", cancelledReturns);
        stats.put("totalRefundAmount", totalRefundAmount != null ? totalRefundAmount : 0.0);
        
        return stats;
    }
    
    public Page<Order> getReturnableOrders(Integer customerId, Pageable pageable) {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        
        return orderRepository.findByCustomerIdAndOrderStatusAndCreatedAtAfter(
                customerId, Order.OrderStatus.HOAN_THANH, thirtyDaysAgo, pageable);
    }
    
    @Transactional
    public Return createReturnRequest(ReturnRequestRequest request) {
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer customerId = userDetails.getAccount().getId();
        
        Order originalOrder = orderRepository.findById(request.getOriginalOrderId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        
        if (!originalOrder.getCustomer().getId().equals(customerId)) {
            throw new RuntimeException("Đơn hàng không thuộc về bạn");
        }
        
        if (originalOrder.getOrderStatus() != Order.OrderStatus.HOAN_THANH) {
            throw new RuntimeException("Chỉ được trả hàng cho đơn hàng đã hoàn thành");
        }
        
        // Kiểm tra thời gian trả hàng (30 ngày)
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        if (originalOrder.getCreatedAt().isBefore(thirtyDaysAgo)) {
            throw new RuntimeException("Đơn hàng đã quá thời hạn trả hàng (30 ngày)");
        }
        
        // Tính tổng tiền hoàn trả
        BigDecimal totalRefund = BigDecimal.ZERO;
        for (ReturnRequestRequest.ReturnItemSimpleRequest item : request.getItems()) {
            OrderItem orderItem = originalOrder.getItems().stream()
                    .filter(oi -> oi.getVariant().getProduct().getId().equals(item.getProductId()) &&
                            oi.getVariant().getId().equals(item.getProductVariantId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Sản phẩm không tồn tại trong đơn hàng: " + item.getProductId()));
            
            if (item.getQuantity() > orderItem.getQuantity()) {
                throw new RuntimeException("Số lượng trả vượt quá số lượng đã mua");
            }
            
            totalRefund = totalRefund.add(orderItem.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        
        Return returnOrder = new Return();
        returnOrder.setCode(codeGenerator.generateReturnCode());
        returnOrder.setOriginalOrder(originalOrder);
        returnOrder.setCustomer(userDetails.getAccount());
        returnOrder.setTotalRefund(totalRefund.doubleValue());
        returnOrder.setReason(request.getReason());
        returnOrder.setStatus(Return.ReturnStatus.CHO_XU_LY);
        
        try {
            String itemsJson = objectMapper.writeValueAsString(request.getItems());
            returnOrder.setItems(itemsJson);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi serialize items: " + e.getMessage());
        }
        
        return returnRepository.save(returnOrder);
    }
    
    public Page<Return> getMyReturns(Return.ReturnStatus status, Pageable pageable) {
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer customerId = userDetails.getAccount().getId();
        
        if (status != null) {
            return returnRepository.findWithFilters(status, customerId, null, pageable);
        }
        return returnRepository.findByCustomerId(customerId, pageable);
    }
    
    public Return getMyReturnById(Integer id) {
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer customerId = userDetails.getAccount().getId();
        
        Return returnOrder = getReturnById(id);
        
        if (!returnOrder.getCustomer().getId().equals(customerId)) {
            throw new RuntimeException("Không có quyền truy cập đơn trả hàng này");
        }
        
        return returnOrder;
    }
    
    @Transactional
    public void cancelMyReturn(Integer id) {
        Return returnOrder = getMyReturnById(id);
        
        if (returnOrder.getStatus() != Return.ReturnStatus.CHO_XU_LY) {
            throw new RuntimeException("Chỉ có thể hủy đơn trả hàng ở trạng thái CHO_XU_LY");
        }
        
        returnOrder.setStatus(Return.ReturnStatus.DA_HUY);
        returnRepository.save(returnOrder);
    }
}

