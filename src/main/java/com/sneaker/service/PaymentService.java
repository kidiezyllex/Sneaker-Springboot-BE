package com.sneaker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sneaker.dto.request.*;
import com.sneaker.entity.Order;
import com.sneaker.entity.Payment;
import com.sneaker.repository.OrderRepository;
import com.sneaker.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public Payment createPayment(PaymentCreateRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        if (order.getOrderStatus() == Order.OrderStatus.HOAN_THANH ||
                order.getOrderStatus() == Order.OrderStatus.DA_HUY) {
            throw new RuntimeException("Không thể tạo thanh toán cho đơn hàng đã " + order.getOrderStatus());
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(request.getAmount());
        payment.setMethod(Payment.PaymentMethod.valueOf(request.getMethod()));

        if (request.getMethod().equals("BANK_TRANSFER")) {
            payment.setStatus(Payment.PaymentStatus.PENDING);
            if (request.getBankTransferInfo() != null) {
                try {
                    payment.setBankTransferInfo(objectMapper.writeValueAsString(request.getBankTransferInfo()));
                } catch (Exception e) {
                    throw new RuntimeException("Lỗi khi serialize bankTransferInfo: " + e.getMessage());
                }
            }
        } else {
            payment.setStatus(Payment.PaymentStatus.COMPLETED);
        }

        payment.setNote(request.getNote());

        payment = paymentRepository.save(payment);

        // Cập nhật trạng thái thanh toán của đơn hàng
        updateOrderPaymentStatus(order);

        return payment;
    }

    public Page<Payment> getPayments(Integer orderId, Payment.PaymentStatus status,
            Payment.PaymentMethod method,
            java.time.LocalDateTime startDate,
            java.time.LocalDateTime endDate,
            Pageable pageable) {
        return paymentRepository.findWithFilters(orderId, status, method, startDate, endDate, pageable);
    }

    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thanh toán"));
    }

    @Transactional
    public Payment updatePaymentStatus(Integer id, PaymentStatusUpdateRequest request) {
        Payment payment = getPaymentById(id);

        try {
            Payment.PaymentStatus newStatus = Payment.PaymentStatus.valueOf(request.getStatus());
            payment.setStatus(newStatus);

            if (request.getNote() != null) {
                payment.setNote(request.getNote());
            }

            payment = paymentRepository.save(payment);

            // Cập nhật trạng thái thanh toán của đơn hàng
            updateOrderPaymentStatus(payment.getOrder());

            return payment;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Trạng thái không hợp lệ");
        }
    }

    @Transactional
    public void deletePayment(Integer id) {
        Payment payment = getPaymentById(id);

        if (payment.getStatus() == Payment.PaymentStatus.COMPLETED) {
            throw new RuntimeException("Không thể xóa thanh toán đã hoàn thành");
        }

        paymentRepository.delete(payment);
    }

    public Page<Payment> getPaymentsByOrderId(Integer orderId, Pageable pageable) {
        return paymentRepository.findByOrderId(orderId, pageable);
    }

    @Transactional
    public Payment createCODPayment(CODPaymentRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        if (order.getOrderStatus() != Order.OrderStatus.HOAN_THANH) {
            throw new RuntimeException("Chỉ có thể tạo thanh toán COD cho đơn hàng đã hoàn thành");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(request.getAmount());
        payment.setMethod(Payment.PaymentMethod.COD);
        payment.setStatus(Payment.PaymentStatus.COMPLETED);
        payment.setNote(request.getNote() != null ? request.getNote() : "Thanh toán COD khi giao hàng");

        payment = paymentRepository.save(payment);

        // Cập nhật trạng thái thanh toán của đơn hàng
        updateOrderPaymentStatus(order);

        return payment;
    }

    private void updateOrderPaymentStatus(Order order) {
        List<Payment> completedPayments = paymentRepository.findByOrderIdAndStatus(
                order.getId(), Payment.PaymentStatus.COMPLETED);

        BigDecimal totalPaid = completedPayments.stream()
                .map(payment -> BigDecimal.valueOf(payment.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalPaid.compareTo(order.getTotal()) >= 0) {
            order.setPaymentStatus(Order.PaymentStatus.PAID);
        } else if (totalPaid.compareTo(BigDecimal.ZERO) > 0) {
            order.setPaymentStatus(Order.PaymentStatus.PARTIAL_PAID);
        } else {
            order.setPaymentStatus(Order.PaymentStatus.PENDING);
        }

        orderRepository.save(order);
    }
}
