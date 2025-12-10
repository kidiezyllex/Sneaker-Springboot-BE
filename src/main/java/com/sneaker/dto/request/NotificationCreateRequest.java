package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationCreateRequest {
    @NotBlank(message = "Loại thông báo không được để trống")
    private String type;
    
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;
    
    @NotBlank(message = "Nội dung không được để trống")
    private String message;
    
    private Integer accountId;
}

