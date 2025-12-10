package com.sneaker.repository;

import com.sneaker.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByPhoneNumber(String phoneNumber);
    Optional<Account> findByCode(String code);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByCode(String code);
    
    @Query("SELECT a FROM Account a WHERE " +
           "(:role IS NULL OR a.role = :role) AND " +
           "(:status IS NULL OR a.status = :status) AND " +
           "(:search IS NULL OR a.fullName LIKE %:search% OR a.email LIKE %:search% OR " +
           "a.phoneNumber LIKE %:search% OR a.code LIKE %:search%)")
    Page<Account> findWithFilters(
        @Param("role") Account.Role role,
        @Param("status") Account.AccountStatus status,
        @Param("search") String search,
        Pageable pageable
    );
}

