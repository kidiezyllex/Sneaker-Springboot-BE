package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Map;

@Data
public class PaymentCreateRequest {
    @NotNull(message = "ID đơn hàng không được để trống")
    private Integer orderId;
    
    @NotNull(message = "Số tiền không được để trống")
    @Positive(message = "Số tiền phải lớn hơn 0")
    private Double amount;
    
    @NotBlank(message = "Phương thức thanh toán không được để trống")
    private String method;
    
    private Map<String, Object> bankTransferInfo;
    
    private String note;
}

