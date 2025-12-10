package com.sneaker.repository;

import com.sneaker.entity.Return;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {
    Optional<Return> findByCode(String code);
    boolean existsByCode(String code);
    
    Page<Return> findByCustomerId(Integer customerId, Pageable pageable);
    
    @Query("SELECT r FROM Return r WHERE " +
           "(:status IS NULL OR r.status = :status) AND " +
           "(:customerId IS NULL OR r.customer.id = :customerId) AND " +
           "(:search IS NULL OR r.code LIKE %:search%)")
    Page<Return> findWithFilters(
        @Param("status") Return.ReturnStatus status,
        @Param("customerId") Integer customerId,
        @Param("search") String search,
        Pageable pageable
    );
    
    @Query("SELECT COUNT(r) FROM Return r WHERE " +
           "(:startDate IS NULL OR r.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR r.createdAt <= :endDate)")
    long countByDateRange(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    @Query("SELECT COUNT(r) FROM Return r WHERE r.status = :status AND " +
           "(:startDate IS NULL OR r.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR r.createdAt <= :endDate)")
    long countByStatusAndDateRange(
        @Param("status") Return.ReturnStatus status,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    @Query("SELECT COALESCE(SUM(r.totalRefund), 0) FROM Return r WHERE " +
           "r.status = 'DA_HOAN_TIEN' AND " +
           "(:startDate IS NULL OR r.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR r.createdAt <= :endDate)")
    Double sumTotalRefundByDateRange(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}

