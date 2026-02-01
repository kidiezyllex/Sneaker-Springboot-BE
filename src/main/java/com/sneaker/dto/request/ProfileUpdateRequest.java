package com.sneaker.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProfileUpdateRequest {
    private String fullName;
    private String phoneNumber;
    private LocalDate birthday;
    private Boolean gender;
    private String citizenId;
    private String avatar;
}
