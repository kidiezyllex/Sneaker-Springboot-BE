package com.sneaker.controller;

import com.sneaker.dto.request.*;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.entity.ChatbotConfig;
import com.sneaker.entity.ChatbotTraining;
import com.sneaker.entity.ChatHistory;
import com.sneaker.security.SecurityUser;
import com.sneaker.service.ChatbotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
@Tag(name = "Chatbot", description = "AI Chatbot APIs using Gemini")
@SecurityRequirement(name = "bearer-jwt")
public class ChatbotController {
    
    private final ChatbotService chatbotService;
    
    @PostMapping("/chat")
    @Operation(summary = "Chat with AI", description = "Send a message to the AI chatbot and get a response")
    public ResponseEntity<ApiResponse<Map<String, String>>> chat(
            @RequestBody Map<String, String> request,
            @AuthenticationPrincipal SecurityUser user) {
        String userMessage = request.get("message");
        String sessionId = request.get("sessionId");
        
        if (userMessage == null || userMessage.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Message is required"));
        }
        
        Integer accountId = user != null ? user.getId() : null;
        String response = chatbotService.chat(userMessage, sessionId, accountId);
        
        return ResponseEntity.ok(ApiResponse.success(
            Map.of("response", response)
        ));
    }
    
    // ========== Config Management (Admin) ==========
    
    @GetMapping("/config")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all chatbot configs", description = "Get all chatbot configuration settings (Admin only)")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllConfigs() {
        return ResponseEntity.ok(ApiResponse.success(
            Map.of("configs", chatbotService.getAllConfigs())
        ));
    }
    
    @GetMapping("/config/{configKey}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get chatbot config by key", description = "Get specific chatbot configuration (Admin only)")
    public ResponseEntity<ApiResponse<ChatbotConfig>> getConfig(@PathVariable String configKey) {
        ChatbotConfig config = chatbotService.getConfig(configKey);
        return ResponseEntity.ok(ApiResponse.success("Lấy cấu hình thành công", config));
    }
    
    @PutMapping("/config/{configKey}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update chatbot config", description = "Update chatbot configuration (Admin only)")
    public ResponseEntity<ApiResponse<ChatbotConfig>> updateConfig(
            @PathVariable String configKey,
            @Valid @RequestBody ChatbotConfigUpdateRequest request) {
        ChatbotConfig config = chatbotService.updateConfig(configKey, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật cấu hình thành công", config));
    }
    
    // ========== Training Management (Admin) ==========
    
    @PostMapping("/training")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create training data", description = "Create new chatbot training data (FAQ/Answers) (Admin only)")
    public ResponseEntity<ApiResponse<ChatbotTraining>> createTraining(
            @Valid @RequestBody ChatbotTrainingCreateRequest request) {
        ChatbotTraining training = chatbotService.createTraining(request);
        return ResponseEntity.ok(ApiResponse.success("Tạo dữ liệu huấn luyện thành công", training));
    }
    
    @GetMapping("/training")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all training data", description = "Get paginated list of chatbot training data (Admin only)")
    public ResponseEntity<ApiResponse<Page<ChatbotTraining>>> getTrainings(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        ChatbotTraining.Status statusEnum = status != null ? 
                ChatbotTraining.Status.valueOf(status) : ChatbotTraining.Status.ACTIVE;
        Pageable pageable = PageRequest.of(page - 1, limit);
        
        Page<ChatbotTraining> trainings = chatbotService.getTrainings(statusEnum, category, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách dữ liệu huấn luyện thành công", trainings));
    }
    
    @GetMapping("/training/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get training data by ID", description = "Get specific training data (Admin only)")
    public ResponseEntity<ApiResponse<ChatbotTraining>> getTrainingById(@PathVariable Integer id) {
        ChatbotTraining training = chatbotService.getTrainingById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin dữ liệu huấn luyện thành công", training));
    }
    
    @PutMapping("/training/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update training data", description = "Update chatbot training data (Admin only)")
    public ResponseEntity<ApiResponse<ChatbotTraining>> updateTraining(
            @PathVariable Integer id,
            @Valid @RequestBody ChatbotTrainingUpdateRequest request) {
        ChatbotTraining training = chatbotService.updateTraining(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật dữ liệu huấn luyện thành công", training));
    }
    
    @DeleteMapping("/training/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete training data", description = "Delete chatbot training data (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteTraining(@PathVariable Integer id) {
        chatbotService.deleteTraining(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa dữ liệu huấn luyện thành công", null));
    }
    
    // ========== Chat History ==========
    
    @GetMapping("/history")
    @Operation(summary = "Get chat history", description = "Get chat history of current user")
    public ResponseEntity<ApiResponse<Page<ChatHistory>>> getChatHistory(
            @AuthenticationPrincipal SecurityUser user,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<ChatHistory> history = chatbotService.getChatHistory(user.getId(), pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy lịch sử chat thành công", history));
    }
    
    @GetMapping("/history/session/{sessionId}")
    @Operation(summary = "Get chat history by session", description = "Get chat history by session ID")
    public ResponseEntity<ApiResponse<Page<ChatHistory>>> getChatHistoryBySession(
            @PathVariable String sessionId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<ChatHistory> history = chatbotService.getChatHistoryBySession(sessionId, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy lịch sử chat theo session thành công", history));
    }
    
    @PostMapping("/history/{id}/rate")
    @Operation(summary = "Rate chat response", description = "Rate and provide feedback for a chat response")
    public ResponseEntity<ApiResponse<ChatHistory>> rateChat(
            @PathVariable Integer id,
            @Valid @RequestBody ChatRatingRequest request) {
        ChatHistory chatHistory = chatbotService.rateChat(id, request);
        return ResponseEntity.ok(ApiResponse.success("Đánh giá chat thành công", chatHistory));
    }
    
    // ========== Statistics (Admin) ==========
    
    @GetMapping("/statistics")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get chat statistics", description = "Get chatbot statistics (Admin only)")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getChatStatistics(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        if (startDate == null) startDate = LocalDateTime.now().minusDays(30);
        if (endDate == null) endDate = LocalDateTime.now();
        
        Map<String, Object> stats = chatbotService.getChatStatistics(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Lấy thống kê chat thành công", stats));
    }
}

