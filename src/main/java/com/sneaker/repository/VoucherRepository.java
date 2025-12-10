package com.sneaker.repository;

import com.sneaker.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    Optional<Voucher> findByCode(String code);
    boolean existsByCode(String code);
    
    @Query("SELECT v FROM Voucher v WHERE " +
           "v.status = 'HOAT_DONG' AND " +
           "v.startDate <= :now AND " +
           "v.endDate >= :now AND " +
           "v.usedCount < v.quantity")
    Page<Voucher> findAvailableVouchers(@Param("now") LocalDateTime now, Pageable pageable);
    
    @Query("SELECT v FROM Voucher v WHERE " +
           "(:code IS NULL OR v.code LIKE %:code%) AND " +
           "(:name IS NULL OR v.name LIKE %:name%) AND " +
           "(:status IS NULL OR v.status = :status) AND " +
           "(:startDate IS NULL OR v.startDate >= :startDate) AND " +
           "(:endDate IS NULL OR v.endDate <= :endDate)")
    Page<Voucher> findWithFilters(
        @Param("code") String code,
        @Param("name") String name,
        @Param("status") Voucher.Status status,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        Pageable pageable
    );
}

