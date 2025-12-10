package com.sneaker.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class StockUpdateRequest {
    
    @NotEmpty(message = "Vui lòng cung cấp danh sách cập nhật tồn kho")
    @Valid
    private List<VariantStockUpdate> variantUpdates;
    
    @Data
    public static class VariantStockUpdate {
        @jakarta.validation.constraints.NotNull(message = "variantId không được để trống")
        private Integer variantId;
        
        @jakarta.validation.constraints.Min(value = 0, message = "Số lượng không được âm")
        private Integer stock;
    }
}

