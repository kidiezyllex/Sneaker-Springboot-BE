package com.sneaker.controller;

import com.sneaker.dto.request.*;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.entity.Account;
import com.sneaker.entity.AccountAddress;
import com.sneaker.security.SecurityUser;
import com.sneaker.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Tag(name = "Account", description = "Account Management APIs")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {
    
    private final AccountService accountService;
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all accounts", description = "Get paginated list of accounts (Admin only)")
    public ResponseEntity<ApiResponse<Page<Account>>> getAllAccounts(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Account.Role roleEnum = role != null ? Account.Role.valueOf(role) : null;
        Account.AccountStatus statusEnum = status != null ? Account.AccountStatus.valueOf(status) : null;
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        Page<Account> accounts = accountService.getAllAccounts(roleEnum, statusEnum, search, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách tài khoản thành công", accounts));
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get account by ID", description = "Get account details by ID (Admin only)")
    public ResponseEntity<ApiResponse<Account>> getAccountById(@PathVariable Integer id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin tài khoản thành công", account));
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register new account", description = "Create a new customer account")
    public ResponseEntity<ApiResponse<Account>> register(@Valid @RequestBody RegisterRequest request) {
        Account account = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo tài khoản thành công", account));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update account", description = "Update account information (Admin only)")
    public ResponseEntity<ApiResponse<Account>> updateAccount(
            @PathVariable Integer id,
            @Valid @RequestBody AccountUpdateRequest request) {
        Account account = accountService.updateAccount(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật tài khoản thành công", account));
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update account status", description = "Update account status (Admin only)")
    public ResponseEntity<ApiResponse<Account>> updateAccountStatus(
            @PathVariable Integer id,
            @Valid @RequestBody AccountStatusUpdateRequest request) {
        Account account = accountService.updateAccountStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success(
                request.getStatus().equals("HOAT_DONG") ? "Kích hoạt tài khoản thành công" : 
                "Vô hiệu hóa tài khoản thành công", account));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete account", description = "Delete account (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable Integer id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa tài khoản thành công", null));
    }
    
    @GetMapping("/profile")
    @Operation(summary = "Get current user profile", description = "Get profile of currently authenticated user")
    public ResponseEntity<ApiResponse<Account>> getProfile() {
        Account account = accountService.getCurrentAccount();
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin profile thành công", account));
    }
    
    @PutMapping("/profile")
    @Operation(summary = "Update profile", description = "Update profile of currently authenticated user")
    public ResponseEntity<ApiResponse<Account>> updateProfile(@Valid @RequestBody ProfileUpdateRequest request) {
        Account account = accountService.updateProfile(request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật profile thành công", account));
    }
    
    @PutMapping("/profile/password")
    @Operation(summary = "Change password", description = "Change password of currently authenticated user")
    public ResponseEntity<ApiResponse<Void>> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        accountService.changePassword(request);
        return ResponseEntity.ok(ApiResponse.success("Thay đổi mật khẩu thành công", null));
    }
    
    @PostMapping("/profile/addresses")
    @Operation(summary = "Add address", description = "Add new address for currently authenticated user")
    public ResponseEntity<ApiResponse<AccountAddress>> addAddress(@Valid @RequestBody AddressRequest request) {
        AccountAddress address = accountService.addAddress(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Thêm địa chỉ thành công", address));
    }
    
    @PutMapping("/profile/addresses/{addressId}")
    @Operation(summary = "Update address", description = "Update address for currently authenticated user")
    public ResponseEntity<ApiResponse<AccountAddress>> updateAddress(
            @PathVariable Integer addressId,
            @Valid @RequestBody AddressRequest request) {
        AccountAddress address = accountService.updateAddress(addressId, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật địa chỉ thành công", address));
    }
    
    @DeleteMapping("/profile/addresses/{addressId}")
    @Operation(summary = "Delete address", description = "Delete address for currently authenticated user")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable Integer addressId) {
        accountService.deleteAddress(addressId);
        return ResponseEntity.ok(ApiResponse.success("Xóa địa chỉ thành công", null));
    }
}

