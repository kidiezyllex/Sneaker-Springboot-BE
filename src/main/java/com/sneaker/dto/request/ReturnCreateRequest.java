package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ReturnCreateRequest {
    @NotNull(message = "ID đơn hàng gốc không được để trống")
    private Integer originalOrderId;
    
    @NotNull(message = "ID khách hàng không được để trống")
    private Integer customerId;
    
    @NotNull(message = "Danh sách sản phẩm không được để trống")
    private List<ReturnItemRequest> items;
    
    @NotNull(message = "Tổng tiền hoàn trả không được để trống")
    @Positive(message = "Tổng tiền hoàn trả phải lớn hơn 0")
    private Double totalRefund;
    
    private String reason;
    
    @Data
    public static class ReturnItemRequest {
        @NotNull(message = "ID sản phẩm không được để trống")
        private Integer productId;
        
        @NotNull(message = "ID variant không được để trống")
        private Integer productVariantId;
        
        @NotNull(message = "Số lượng không được để trống")
        @Positive(message = "Số lượng phải lớn hơn 0")
        private Integer quantity;
        
        @NotNull(message = "Giá không được để trống")
        @Positive(message = "Giá phải lớn hơn 0")
        private Double price;
        
        private String reason;
    }
}

