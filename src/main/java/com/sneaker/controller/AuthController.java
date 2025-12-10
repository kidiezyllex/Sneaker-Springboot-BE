package com.sneaker.controller;

import com.sneaker.dto.request.*;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.dto.response.AuthResponse;
import com.sneaker.entity.Account;
import com.sneaker.entity.AccountAddress;
import com.sneaker.service.AccountService;
import com.sneaker.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {
    
    private final AuthService authService;
    private final AccountService accountService;
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new customer account")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(ApiResponse.success("Đăng ký tài khoản thành công", response));
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticate user and get JWT token")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Đăng nhập thành công", response));
    }
    
    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh token", description = "Get new access token using refresh token")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(@RequestBody String refreshToken) {
        AuthResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(ApiResponse.success("Token refreshed successfully", response));
    }
    
    @GetMapping("/profile")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get current account", description = "Get current authenticated user's account information")
    public ResponseEntity<ApiResponse<Account>> getCurrentAccount() {
        Account account = accountService.getCurrentAccount();
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin tài khoản thành công", account));
    }
    
    @PutMapping("/change-password")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Change password", description = "Change password for current authenticated user")
    public ResponseEntity<ApiResponse<Void>> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        accountService.changePassword(request);
        return ResponseEntity.ok(ApiResponse.success("Thay đổi mật khẩu thành công", null));
    }
    
    @PutMapping("/update-profile")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update profile", description = "Update profile for current authenticated user")
    public ResponseEntity<ApiResponse<Account>> updateProfile(@Valid @RequestBody ProfileUpdateRequest request) {
        Account account = accountService.updateProfile(request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thông tin thành công", account));
    }
    
    @PostMapping("/address")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add address", description = "Add new address for current authenticated user")
    public ResponseEntity<ApiResponse<AccountAddress>> addAddress(@Valid @RequestBody AddressRequest request) {
        AccountAddress address = accountService.addAddress(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Thêm địa chỉ thành công", address));
    }
    
    @PutMapping("/address/{addressId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update address", description = "Update address for current authenticated user")
    public ResponseEntity<ApiResponse<AccountAddress>> updateAddress(
            @PathVariable Integer addressId,
            @Valid @RequestBody AddressRequest request) {
        AccountAddress address = accountService.updateAddress(addressId, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật địa chỉ thành công", address));
    }
    
    @DeleteMapping("/address/{addressId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete address", description = "Delete address for current authenticated user")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable Integer addressId) {
        accountService.deleteAddress(addressId);
        return ResponseEntity.ok(ApiResponse.success("Xóa địa chỉ thành công", null));
    }
    
    @PutMapping("/address/{addressId}/default")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Set default address", description = "Set address as default for current authenticated user")
    public ResponseEntity<ApiResponse<AccountAddress>> setDefaultAddress(@PathVariable Integer addressId) {
        AccountAddress address = accountService.setDefaultAddress(addressId);
        return ResponseEntity.ok(ApiResponse.success("Đặt địa chỉ mặc định thành công", address));
    }
    
    // TEMPORARY: Generate password hashes for insert_data.sql
    @GetMapping("/temp/generate-hashes")
    @Operation(summary = "Generate password hashes", description = "Temporary endpoint to generate BCrypt hashes")
    public ResponseEntity<ApiResponse<Object>> generateHashes() {
        Map<String, String> hashes = authService.generatePasswordHashes();
        return ResponseEntity.ok(ApiResponse.success("Password hashes generated", hashes));
    }
}

