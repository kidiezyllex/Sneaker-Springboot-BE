package com.sneaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class sneakerApplication {
    public static void main(String[] args) {
        System.out.println("=== DEPLOYMENT VERSION: 2026-03-07-v3 ===");
        SpringApplication.run(sneakerApplication.class, args);
    }
}
