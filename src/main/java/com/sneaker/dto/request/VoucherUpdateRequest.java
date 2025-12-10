package com.sneaker.dto.request;

import com.sneaker.entity.Voucher;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VoucherUpdateRequest {
    private String name;
    private Integer quantity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal minOrderValue;
    private BigDecimal maxDiscount;
    private Voucher.Status status;
}

