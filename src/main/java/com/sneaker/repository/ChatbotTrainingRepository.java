package com.sneaker.repository;

import com.sneaker.entity.ChatbotTraining;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatbotTrainingRepository extends JpaRepository<ChatbotTraining, Integer> {
    Page<ChatbotTraining> findByStatus(ChatbotTraining.Status status, Pageable pageable);
    Page<ChatbotTraining> findByCategoryAndStatus(String category, ChatbotTraining.Status status, Pageable pageable);
    
    @Query("SELECT ct FROM ChatbotTraining ct WHERE ct.status = :status ORDER BY ct.priority DESC, ct.createdAt DESC")
    List<ChatbotTraining> findActiveTrainingsOrderedByPriority(@Param("status") ChatbotTraining.Status status);
    
    List<ChatbotTraining> findByCategory(String category);
}

