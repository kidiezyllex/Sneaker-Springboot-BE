package com.sneaker.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMeta {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;
    private String apiVersion;
    
    // Factory method để tạo meta mặc định
    public static ResponseMeta create() {
        return ResponseMeta.builder()
                .timestamp(LocalDateTime.now())
                .apiVersion("v1.2")
                .build();
    }
    
    // Factory method với custom version
    public static ResponseMeta create(String apiVersion) {
        return ResponseMeta.builder()
                .timestamp(LocalDateTime.now())
                .apiVersion(apiVersion)
                .build();
    }
}
