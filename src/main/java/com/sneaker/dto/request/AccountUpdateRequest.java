package com.sneaker.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountUpdateRequest {
    private String fullName;
    
    @Email
    private String email;
    
    private String phoneNumber;
    
    private Boolean gender;
    
    private LocalDateTime birthday;
    
    private String citizenId;
    
    private String avatar;
    
    private String status;
}

