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

        long count = accountRepository.count() + 1;
        String code;
        do {
            String number = String.format("%04d", count++);
            code = prefix + number + year;
        } while (accountRepository.existsByCode(code));

        return code;
    }

    public String generateProductCode() {
        long count = productRepository.count() + 1;
        String code;
        do {
            code = String.format("PRD%06d", count++);
        } while (productRepository.existsByCode(code));

        return code;
    }

    public String generateOrderCode() {
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear()).substring(2);
        String month = String.format("%02d", now.getMonthValue());

        long count = orderRepository.count() + 1;
        String code;
        do {
            String number = String.format("%04d", count++);
            code = "DH" + year + month + number;
        } while (orderRepository.existsByCode(code));

        return code;
    }

    public String generateReturnCode() {
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear()).substring(2);
        String month = String.format("%02d", now.getMonthValue());

        long count = returnRepository.count() + 1;
        String code;
        do {
            String number = String.format("%04d", count++);
            code = "RT" + year + month + number;
        } while (returnRepository.existsByCode(code));

        return code;
    }
}
