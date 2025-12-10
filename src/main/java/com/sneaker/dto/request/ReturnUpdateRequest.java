package com.sneaker.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ReturnUpdateRequest {
    private List<ReturnCreateRequest.ReturnItemRequest> items;
    private Double totalRefund;
}

