package com.sneaker.repository;

import com.sneaker.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByCode(String code);
    boolean existsByCode(String code);
    
    Page<Order> findByCustomerId(Integer customerId, Pageable pageable);
    
    @Query("SELECT o FROM Order o WHERE " +
           "(:customerId IS NULL OR o.customer.id = :customerId) AND " +
           "(:orderStatus IS NULL OR o.orderStatus = :orderStatus) AND " +
           "(:paymentStatus IS NULL OR o.paymentStatus = :paymentStatus) AND " +
           "(:startDate IS NULL OR o.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR o.createdAt <= :endDate) AND " +
           "(:search IS NULL OR o.code LIKE %:search%)")
    Page<Order> findWithFilters(
        @Param("customerId") Integer customerId,
        @Param("orderStatus") Order.OrderStatus orderStatus,
        @Param("paymentStatus") Order.PaymentStatus paymentStatus,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("search") String search,
        Pageable pageable
    );
    
    Page<Order> findByCustomerIdAndOrderStatusAndCreatedAtAfter(
        Integer customerId, Order.OrderStatus orderStatus, LocalDateTime createdAt, Pageable pageable
    );
}

