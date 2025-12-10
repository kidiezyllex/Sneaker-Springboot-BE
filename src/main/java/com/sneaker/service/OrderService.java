package com.sneaker.service;

import com.sneaker.dto.request.OrderCreateRequest;
import com.sneaker.entity.*;
import com.sneaker.repository.*;
import com.sneaker.security.SecurityUser;
import com.sneaker.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final AccountRepository accountRepository;
    private final VoucherRepository voucherRepository;
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CodeGenerator codeGenerator;
    
    @Transactional
    public Order createOrder(OrderCreateRequest request, SecurityUser user) {
        // Validate customer
        Account customer = accountRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + request.getCustomerId()));
        
        // Validate voucher if provided
        Voucher voucher = null;
        if (request.getVoucherId() != null) {
            voucher = voucherRepository.findById(request.getVoucherId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy voucher"));
        }
        
        // Validate items and check stock
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < request.getItems().size(); i++) {
            final int itemIndex = i;
            OrderCreateRequest.OrderItemRequest itemReq = request.getItems().get(i);
            Integer productId = itemReq.getProductId() != null ? itemReq.getProductId() : itemReq.getProduct();
            
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId + " trong item[" + itemIndex + "]"));
            
            ProductVariant variant = null;
            if (itemReq.getProductVariantId() != null) {
                variant = productVariantRepository.findById(itemReq.getProductVariantId())
                        .filter(v -> v.getProduct().getId().equals(productId))
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy biến thể sản phẩm"));
            } else if (itemReq.getVariant() != null) {
                variant = productVariantRepository.findByProductIdAndColorIdAndSizeId(
                    productId, itemReq.getVariant().getColorId(), itemReq.getVariant().getSizeId()
                ).orElseThrow(() -> new RuntimeException("Không tìm thấy biến thể sản phẩm cho sản phẩm \"" + 
                    product.getName() + "\" với colorId: " + itemReq.getVariant().getColorId() + 
                    ", sizeId: " + itemReq.getVariant().getSizeId() + " trong item[" + itemIndex + "]"));
            }
            
            if (variant == null) {
                throw new RuntimeException("Variant là bắt buộc trong item[" + itemIndex + "]");
            }
            
            // Check stock
            if (variant.getStock() < itemReq.getQuantity()) {
                throw new RuntimeException("Không đủ tồn kho cho sản phẩm \"" + product.getName() + 
                    "\" trong item[" + itemIndex + "]. Tồn kho hiện tại: " + variant.getStock() + 
                    ", yêu cầu: " + itemReq.getQuantity());
            }
            
            // Create order item
            OrderItem orderItem = new OrderItem();
            orderItem.setVariant(variant);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setPrice(itemReq.getPrice());
            orderItems.add(orderItem);
            
            // Update stock
            variant.setStock(variant.getStock() - itemReq.getQuantity());
            productVariantRepository.save(variant);
        }
        
        // Determine payment status
        Order.PaymentStatus paymentStatus = Order.PaymentStatus.PENDING;
        if (request.getPaymentMethod() == Order.PaymentMethod.BANK_TRANSFER) {
            if (request.getPaymentInfo() != null && "SUCCESS".equals(request.getPaymentInfo().getStatus())) {
                paymentStatus = Order.PaymentStatus.PAID;
            } else {
                paymentStatus = Order.PaymentStatus.PAID; // Based on Node.js logic
            }
        }
        
        // Create order
        Order order = new Order();
        if (request.getOrderId() != null && !request.getOrderId().isEmpty()) {
            order.setCode(request.getOrderId());
        } else {
            order.setCode(codeGenerator.generateOrderCode());
        }
        order.setCustomer(customer);
        if (user != null && user.getAccount().getRole() == Account.Role.ADMIN || 
            user.getAccount().getRole() == Account.Role.STAFF) {
            order.setStaff(user.getAccount());
        }
        order.setVoucher(voucher);
        order.setSubTotal(request.getSubTotal());
        order.setDiscount(request.getDiscount() != null ? request.getDiscount() : BigDecimal.ZERO);
        order.setTotal(request.getTotal());
        order.setShippingName(request.getShippingAddress().getName());
        order.setShippingPhoneNumber(request.getShippingAddress().getPhoneNumber());
        order.setShippingProvinceId(request.getShippingAddress().getProvinceId());
        order.setShippingDistrictId(request.getShippingAddress().getDistrictId());
        order.setShippingWardId(request.getShippingAddress().getWardId());
        order.setShippingSpecificAddress(request.getShippingAddress().getSpecificAddress());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setPaymentStatus(paymentStatus);
        order.setOrderStatus(Order.OrderStatus.CHO_XAC_NHAN);
        
        order = orderRepository.save(order);
        
        // Save order items
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }
        
        return orderRepository.findById(order.getId()).orElse(order);
    }
    
    public Page<Order> getOrders(Integer customerId, Order.OrderStatus orderStatus,
                                Order.PaymentStatus paymentStatus, LocalDateTime startDate,
                                LocalDateTime endDate, String search, Pageable pageable) {
        return orderRepository.findWithFilters(customerId, orderStatus, paymentStatus, startDate, endDate, search, pageable);
    }
    
    public Page<Order> getMyOrders(Integer customerId, Order.OrderStatus orderStatus, Pageable pageable) {
        return orderRepository.findByCustomerId(customerId, pageable);
    }
    
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }
    
    @Transactional
    public Order updateOrder(Integer id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        
        if (orderDetails.getShippingName() != null) order.setShippingName(orderDetails.getShippingName());
        if (orderDetails.getShippingPhoneNumber() != null) order.setShippingPhoneNumber(orderDetails.getShippingPhoneNumber());
        if (orderDetails.getShippingProvinceId() != null) order.setShippingProvinceId(orderDetails.getShippingProvinceId());
        if (orderDetails.getShippingDistrictId() != null) order.setShippingDistrictId(orderDetails.getShippingDistrictId());
        if (orderDetails.getShippingWardId() != null) order.setShippingWardId(orderDetails.getShippingWardId());
        if (orderDetails.getShippingSpecificAddress() != null) order.setShippingSpecificAddress(orderDetails.getShippingSpecificAddress());
        if (orderDetails.getOrderStatus() != null) order.setOrderStatus(orderDetails.getOrderStatus());
        if (orderDetails.getPaymentStatus() != null) order.setPaymentStatus(orderDetails.getPaymentStatus());
        
        return orderRepository.save(order);
    }
    
    @Transactional
    public Order cancelOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        
        if (order.getOrderStatus() == Order.OrderStatus.DA_HUY) {
            throw new RuntimeException("Đơn hàng đã bị hủy");
        }
        
        // Restore stock
        for (OrderItem item : order.getItems()) {
            ProductVariant variant = item.getVariant();
            variant.setStock(variant.getStock() + item.getQuantity());
            productVariantRepository.save(variant);
        }
        
        order.setOrderStatus(Order.OrderStatus.DA_HUY);
        return orderRepository.save(order);
    }
    
    @Transactional
    public Order updateOrderStatus(Integer id, Order.OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
}

