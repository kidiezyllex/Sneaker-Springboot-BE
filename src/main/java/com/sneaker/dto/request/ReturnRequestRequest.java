package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ReturnRequestRequest {
    @NotNull(message = "ID đơn hàng gốc không được để trống")
    private Integer originalOrderId;
    
    @NotNull(message = "Danh sách sản phẩm không được để trống")
    private List<ReturnItemSimpleRequest> items;
    
    @NotBlank(message = "Lý do trả hàng không được để trống")
    private String reason;
    
    @Data
    public static class ReturnItemSimpleRequest {
        @NotNull(message = "ID sản phẩm không được để trống")
        private Integer productId;
        
        @NotNull(message = "ID variant không được để trống")
        private Integer productVariantId;
        
        @NotNull(message = "Số lượng không được để trống")
        private Integer quantity;
    }
}

