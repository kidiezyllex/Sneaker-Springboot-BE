package com.sneaker.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ImageUpdateRequest {
    
    @NotNull(message = "variantId không được để trống")
    private Integer variantId;
    
    @NotEmpty(message = "Danh sách hình ảnh không được để trống")
    @Size(min = 1, max = 1, message = "Mỗi biến thể chỉ được phép có 1 ảnh duy nhất")
    private List<String> images;
}

