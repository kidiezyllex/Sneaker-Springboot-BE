package com.sneaker.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountUpdateRequest {
    private String fullName;

    @Email
    private String email;

    private String phoneNumber;

    private Boolean gender;

    private LocalDate birthday;

    private String citizenId;

    private String avatar;

    private String status;
}
