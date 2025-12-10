package com.sneaker.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class to generate BCrypt password hashes
 * Run this main method to generate hashes for the SQL script
 */
public class PasswordHashGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        System.out.println("=== BCrypt Password Hashes ===");
        System.out.println("Admin123!    : " + encoder.encode("Admin123!"));
        System.out.println("Customer123! : " + encoder.encode("Customer123!"));
        System.out.println("Staff123!    : " + encoder.encode("Staff123!"));
        System.out.println("===============================");
    }
}

