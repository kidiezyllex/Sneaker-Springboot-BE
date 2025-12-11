package com.sneaker.repository;

import com.sneaker.entity.ChatbotConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatbotConfigRepository extends JpaRepository<ChatbotConfig, Integer> {
    Optional<ChatbotConfig> findByConfigKey(String configKey);
    boolean existsByConfigKey(String configKey);
}

