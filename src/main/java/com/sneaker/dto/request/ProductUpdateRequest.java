package com.sneaker.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductUpdateRequest {
    private String name;
    private Integer brand;
    private Integer category;
    private Integer material;
    private String description;
    private BigDecimal weight;
    private String status;
    private List<VariantRequest> variants;
    
    @Data
    public static class VariantRequest {
        private Integer colorId;
        private Integer sizeId;
        private BigDecimal price;
        private Integer quantity;
        private Integer stock;
        private List<String> images;
    }
}

