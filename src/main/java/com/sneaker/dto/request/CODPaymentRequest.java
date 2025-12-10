package com.sneaker.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CODPaymentRequest {
    @NotNull(message = "ID đơn hàng không được để trống")
    private Integer orderId;
    
    @NotNull(message = "Số tiền không được để trống")
    @Positive(message = "Số tiền phải lớn hơn 0")
    private Double amount;
    
    private String note;
}

