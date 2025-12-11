package com.sneaker.repository;

import com.sneaker.entity.ChatHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Integer> {
    Page<ChatHistory> findByAccountIdOrderByCreatedAtDesc(Integer accountId, Pageable pageable);
    Page<ChatHistory> findBySessionIdOrderByCreatedAtAsc(String sessionId, Pageable pageable);
    
    @Query("SELECT ch FROM ChatHistory ch WHERE ch.account.id = :accountId ORDER BY ch.createdAt DESC")
    List<ChatHistory> findByAccountId(@Param("accountId") Integer accountId);
    
    @Query("SELECT COUNT(ch) FROM ChatHistory ch WHERE ch.createdAt BETWEEN :startDate AND :endDate")
    Long countByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT AVG(ch.rating) FROM ChatHistory ch WHERE ch.rating IS NOT NULL AND ch.createdAt BETWEEN :startDate AND :endDate")
    Double getAverageRatingByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}

