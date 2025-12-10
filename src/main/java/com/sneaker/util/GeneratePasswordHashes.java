package com.sneaker.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Generate BCrypt password hashes for default accounts
 * Run this main method in your IDE to generate hashes for insert_data.sql
 * 
 * Instructions:
 * 1. Right-click on this file -> Run 'GeneratePasswordHashes.main()'
 * 2. Copy the hashes from console output
 * 3. Update insert_data.sql with the new hashes
 */
public class GeneratePasswordHashes {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        System.out.println("\n==========================================");
        System.out.println("BCrypt Password Hashes for insert_data.sql");
        System.out.println("==========================================\n");
        
        // Generate fresh hashes
        String adminHash = encoder.encode("Admin123!");
        String customerHash = encoder.encode("Customer123!");
        String staffHash = encoder.encode("Staff123!");
        
        System.out.println("1. Admin123!");
        System.out.println("   Hash: " + adminHash);
        System.out.println();
        
        System.out.println("2. Customer123!");
        System.out.println("   Hash: " + customerHash);
        System.out.println();
        
        System.out.println("3. Staff123!");
        System.out.println("   Hash: " + staffHash);
        System.out.println();
        
        System.out.println("==========================================");
        System.out.println("For insert_data.sql - Update these lines:");
        System.out.println("==========================================\n");
        
        System.out.println("ADMIN accounts (lines 7, 21):");
        System.out.println("  Replace old hash with: " + adminHash);
        System.out.println();
        
        System.out.println("CUSTOMER accounts (lines 8, 10-19):");
        System.out.println("  Replace old hash with: " + customerHash);
        System.out.println();
        
        System.out.println("STAFF accounts (lines 9, 20):");
        System.out.println("  Replace old hash with: " + staffHash);
        System.out.println();
        
        System.out.println("==========================================");
        System.out.println("SQL UPDATE Commands (if needed):");
        System.out.println("==========================================\n");
        System.out.println("UPDATE accounts SET password = '" + adminHash + "' WHERE role = 'ADMIN';");
        System.out.println("UPDATE accounts SET password = '" + customerHash + "' WHERE role = 'CUSTOMER';");
        System.out.println("UPDATE accounts SET password = '" + staffHash + "' WHERE role = 'STAFF';");
        System.out.println();
    }
}

