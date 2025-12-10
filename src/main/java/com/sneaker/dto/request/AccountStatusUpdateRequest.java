package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountStatusUpdateRequest {
    @NotBlank(message = "Trạng thái không được để trống")
    private String status;
}

