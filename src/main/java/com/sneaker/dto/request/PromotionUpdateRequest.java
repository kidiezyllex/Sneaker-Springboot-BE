package com.sneaker.dto.request;

import com.sneaker.entity.Promotion;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PromotionUpdateRequest {
    private String name;
    private String description;
    private BigDecimal discountPercent;
    private List<Integer> products;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Promotion.Status status;
}

