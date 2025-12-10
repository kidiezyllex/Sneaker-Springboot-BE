package com.sneaker.dto.request;

import lombok.Data;

@Data
public class NotificationUpdateRequest {
    private String title;
    private String message;
    private Boolean isRead;
}

