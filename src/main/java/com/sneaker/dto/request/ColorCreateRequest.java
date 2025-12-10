package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ColorCreateRequest {
    @NotBlank(message = "Tên màu không được để trống")
    private String name;
    
    @NotBlank(message = "Mã màu không được để trống")
    private String code;
    
    private String status = "ACTIVE";
}

