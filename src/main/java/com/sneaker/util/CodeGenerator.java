package com.sneaker.util;

import com.sneaker.entity.Account;
import com.sneaker.entity.Order;
import com.sneaker.entity.Product;
import com.sneaker.repository.AccountRepository;
import com.sneaker.repository.OrderRepository;
import com.sneaker.repository.ProductRepository;
import com.sneaker.repository.ReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CodeGenerator {
    
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReturnRepository returnRepository;
    
    public String generateAccountCode(Account.Role role) {
        String prefix = role == Account.Role.CUSTOMER ? "CUS" : "ADM";
        String year = String.valueOf(LocalDateTime.now().getYear()).substring(2);
        
        long count = accountRepository.count();
        String number = String.format("%04d", count + 1);
        
        return prefix + number + year;
    }
    
    public String generateProductCode() {
        long count = productRepository.count();
        return String.format("PRD%06d", count + 1);
    }
    
    public String generateOrderCode() {
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear()).substring(2);
        String month = String.format("%02d", now.getMonthValue());
        
        long count = orderRepository.count();
        String number = String.format("%04d", count + 1);
        
        return "DH" + year + month + number;
    }
    
    public String generateReturnCode() {
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear()).substring(2);
        String month = String.format("%02d", now.getMonthValue());
        
        long count = returnRepository.count();
        String number = String.format("%04d", count + 1);
        
        return "RT" + year + month + number;
    }
}

