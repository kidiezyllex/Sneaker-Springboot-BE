package com.sneaker.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileUpdateRequest {
    private String fullName;
    private String phoneNumber;
    private LocalDateTime birthday;
    private Boolean gender;
    private String citizenId;
    private String avatar;
}

