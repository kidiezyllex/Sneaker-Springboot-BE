package com.sneaker.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PromotionCreateRequest {
    
    @NotBlank(message = "Tên chương trình khuyến mãi không được để trống")
    private String name;
    
    private String description;
    
    @NotNull(message = "Phần trăm giảm giá không được để trống")
    @DecimalMin(value = "0", message = "Phần trăm giảm giá phải từ 0 đến 100")
    @DecimalMax(value = "100", message = "Phần trăm giảm giá phải từ 0 đến 100")
    private BigDecimal discountPercent;
    
    private List<Integer> products;
    
    @NotNull(message = "Ngày bắt đầu không được để trống")
    private LocalDateTime startDate;
    
    @NotNull(message = "Ngày kết thúc không được để trống")
    private LocalDateTime endDate;
}

