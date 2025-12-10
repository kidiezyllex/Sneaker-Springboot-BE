package com.sneaker.dto.request;

import com.sneaker.entity.Voucher;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VoucherCreateRequest {
    
    @NotBlank(message = "Mã voucher không được để trống")
    private String code;
    
    @NotBlank(message = "Tên voucher không được để trống")
    private String name;
    
    @NotNull(message = "Loại giảm giá không được để trống")
    private Voucher.VoucherType discountType;
    
    @NotNull(message = "Giá trị giảm giá không được để trống")
    @DecimalMin(value = "0.01", message = "Giá trị giảm giá phải lớn hơn 0")
    private BigDecimal discountValue;
    
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer quantity;
    
    @NotNull(message = "Ngày bắt đầu không được để trống")
    private LocalDateTime startDate;
    
    @NotNull(message = "Ngày kết thúc không được để trống")
    private LocalDateTime endDate;
    
    private BigDecimal minOrderValue;
    
    private BigDecimal maxDiscount;
    
    private Voucher.Status status;
}

