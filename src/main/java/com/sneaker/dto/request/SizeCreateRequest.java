package com.sneaker.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SizeCreateRequest {
    @NotNull(message = "Giá trị kích thước không được để trống")
    private Integer value;
    
    private String status = "HOAT_DONG";
}

