package com.sneaker.dto.response;

import com.sneaker.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String refreshToken;
    private AccountInfo account;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountInfo {
        private Integer id;
        private String code;
        private String fullName;
        private String email;
        private String phoneNumber;
        private Account.Role role;
        private String avatar;
        
        public static AccountInfo from(Account account) {
            return new AccountInfo(
                account.getId(),
                account.getCode(),
                account.getFullName(),
                account.getEmail(),
                account.getPhoneNumber(),
                account.getRole(),
                account.getAvatar()
            );
        }
    }
}

