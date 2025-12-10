package com.sneaker.service;

import com.sneaker.dto.request.LoginRequest;
import com.sneaker.dto.request.RegisterRequest;
import com.sneaker.dto.response.AuthResponse;
import com.sneaker.entity.Account;
import com.sneaker.repository.AccountRepository;
import com.sneaker.security.JwtTokenProvider;
import com.sneaker.security.SecurityUser;
import com.sneaker.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final CodeGenerator codeGenerator;
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Check if email or phone already exists
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        if (request.getPhoneNumber() != null && 
            accountRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }
        
        // Create new account
        Account account = new Account();
        account.setFullName(request.getFullName());
        account.setEmail(request.getEmail());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(Account.Role.CUSTOMER);
        account.setStatus(Account.AccountStatus.HOAT_DONG);
        account.setCode(codeGenerator.generateAccountCode(Account.Role.CUSTOMER));
        
        account = accountRepository.save(account);
        
        // Generate tokens
        String token = tokenProvider.generateToken(account.getId(), account.getRole().name());
        String refreshToken = tokenProvider.generateRefreshToken(account.getId());
        
        return new AuthResponse(token, refreshToken, AuthResponse.AccountInfo.from(account));
    }
    
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        Account account = userDetails.getAccount();
        
        String token = tokenProvider.generateToken(account.getId(), account.getRole().name());
        String refreshToken = tokenProvider.generateRefreshToken(account.getId());
        
        return new AuthResponse(token, refreshToken, AuthResponse.AccountInfo.from(account));
    }
    
    public AuthResponse refreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }
        
        Integer userId = tokenProvider.getUserIdFromToken(refreshToken);
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        String newToken = tokenProvider.generateToken(account.getId(), account.getRole().name());
        String newRefreshToken = tokenProvider.generateRefreshToken(account.getId());
        
        return new AuthResponse(newToken, newRefreshToken, AuthResponse.AccountInfo.from(account));
    }
}

