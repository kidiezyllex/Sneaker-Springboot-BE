package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AttributeCreateRequest {
    @NotBlank(message = "Tên không được để trống")
    private String name;
    
    private String status = "ACTIVE";
}

