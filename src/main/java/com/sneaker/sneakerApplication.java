package com.sneaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class sneakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(sneakerApplication.class, args);
    }
}

