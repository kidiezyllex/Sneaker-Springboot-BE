package com.sneaker;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Admin123!: " + encoder.encode("Admin123!"));
        System.out.println("Staff123!: " + encoder.encode("Staff123!"));
    }
}
