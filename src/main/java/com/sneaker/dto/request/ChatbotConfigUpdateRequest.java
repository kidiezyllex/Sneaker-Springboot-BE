package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatbotConfigUpdateRequest {
    @NotBlank(message = "Config value is required")
    private String configValue;
    
    private String description;
}

