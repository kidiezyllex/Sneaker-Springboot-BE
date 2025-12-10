package com.sneaker.dto.request;

import lombok.Data;

@Data
public class ColorUpdateRequest {
    private String name;
    private String code;
    private String status;
}

