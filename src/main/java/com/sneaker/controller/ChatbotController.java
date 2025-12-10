package com.sneaker.controller;

import com.sneaker.dto.response.ApiResponse;
import com.sneaker.service.ChatbotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        String conversationHistory = request.get("history");
        
        if (userMessage == null || userMessage.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Message is required"));
        }
        
        String response = chatbotService.chat(userMessage, conversationHistory);
        
        return ResponseEntity.ok(ApiResponse.success(
            Map.of("response", response)
        ));
    }
}

