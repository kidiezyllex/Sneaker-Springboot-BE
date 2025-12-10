package com.sneaker.controller;

import com.sneaker.dto.response.ApiResponse;
import com.sneaker.service.UploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@Tag(name = "Upload", description = "File Upload APIs")
@SecurityRequirement(name = "bearerAuth")
public class UploadController {
    
    private final UploadService uploadService;
    
    @PostMapping("/image")
    @Operation(summary = "Upload image", description = "Upload image to Cloudinary")
    public ResponseEntity<ApiResponse<Map<String, Object>>> uploadImage(
            @RequestParam("file") MultipartFile file) {
        try {
            Map<String, Object> result = uploadService.uploadImage(file);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Tải ảnh lên thành công", result));
        } catch (IOException e) {
            throw new RuntimeException("Đã xảy ra lỗi khi tải ảnh lên: " + e.getMessage());
        }
    }
}

