package com.sneaker.repository;

import com.sneaker.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Page<Payment> findByOrderId(Integer orderId, Pageable pageable);
    List<Payment> findByOrderIdAndStatus(Integer orderId, Payment.PaymentStatus status);
    
    @Query("SELECT p FROM Payment p WHERE " +
           "(:orderId IS NULL OR p.order.id = :orderId) AND " +
           "(:status IS NULL OR p.status = :status) AND " +
           "(:method IS NULL OR p.method = :method) AND " +
           "(:startDate IS NULL OR p.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR p.createdAt <= :endDate)")
    Page<Payment> findWithFilters(
        @Param("orderId") Integer orderId,
        @Param("status") Payment.PaymentStatus status,
        @Param("method") Payment.PaymentMethod method,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        Pageable pageable
    );
}

