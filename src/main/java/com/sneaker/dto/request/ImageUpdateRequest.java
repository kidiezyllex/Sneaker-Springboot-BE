package com.sneaker.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ImageUpdateRequest {
    
    @NotNull(message = "variantId không được để trống")
    private Integer variantId;
    
    @NotEmpty(message = "Danh sách hình ảnh không được để trống")
    private List<String> images;
}

