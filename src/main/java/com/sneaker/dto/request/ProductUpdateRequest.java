package com.sneaker.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductUpdateRequest {
    private String name;
    
    @JsonAlias("brandId")
    private Object brand;    // Can be Integer (ID) or String (Name)
    
    @JsonAlias("categoryId")
    private Object category; // Can be Integer (ID) or String (Name)
    
    @JsonAlias("materialId")
    private Object material; // Can be Integer (ID) or String (Name)
    private String description;
    private BigDecimal weight;
    private String status;
    private List<VariantRequest> variants;
    
    @Data
    public static class VariantRequest {
        private Object colorId;
        private Object sizeId;
        private BigDecimal price;
        private Integer quantity;
        private Integer stock;
        private List<String> images;
    }
}

