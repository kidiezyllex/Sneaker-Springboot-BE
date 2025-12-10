package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VoucherValidateRequest {
    
    @NotBlank(message = "Mã voucher không được để trống")
    private String code;
    
    private BigDecimal orderValue;
}

