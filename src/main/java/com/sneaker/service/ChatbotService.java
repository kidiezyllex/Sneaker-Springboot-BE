package com.sneaker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatbotService {
    
    @Value("${gemini.api.key}")
    private String apiKey;
    
    @Value("${gemini.api.model}")
    private String model;
    
    @Value("${gemini.api.url}")
    private String apiUrl;
    
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    
    private static final String SYSTEM_PROMPT = """
        Bạn là một trợ lý AI thân thiện và chuyên nghiệp của cửa hàng quần áo Sneaker.
        Nhiệm vụ của bạn là hỗ trợ khách hàng với:
        1. Tư vấn về sản phẩm quần áo
        2. Hướng dẫn đặt hàng
        3. Trả lời câu hỏi về chính sách đổi trả, vận chuyển
        4. Hỗ trợ tra cứu đơn hàng
        5. Giải đáp các thắc mắc khác về cửa hàng
        
        Hãy trả lời một cách thân thiện, chuyên nghiệp và hữu ích. Nếu không biết câu trả lời,
        hãy đề nghị khách hàng liên hệ bộ phận hỗ trợ.
        """;
    
    public String chat(String userMessage, String conversationHistory) {
        try {
            String url = String.format("%s/%s:generateContent?key=%s", 
                apiUrl, model, apiKey);
            
            Map<String, Object> requestBody = new HashMap<>();
            
            // Build contents array
            List<Map<String, Object>> contents = new java.util.ArrayList<>();
            
            // Add system prompt as first user message
            Map<String, Object> systemMessage = new HashMap<>();
            systemMessage.put("role", "user");
            systemMessage.put("parts", List.of(Map.of("text", SYSTEM_PROMPT)));
            contents.add(systemMessage);
            
            // Add conversation history if exists (can be enhanced later)
            // For now, we'll just add the current message
            
            // Add current user message
            Map<String, Object> userMsg = new HashMap<>();
            userMsg.put("role", "user");
            userMsg.put("parts", List.of(Map.of("text", userMessage)));
            contents.add(userMsg);
            
            requestBody.put("contents", contents);
            
            // Generation config
            Map<String, Object> generationConfig = new HashMap<>();
            generationConfig.put("temperature", 0.7);
            generationConfig.put("topK", 40);
            generationConfig.put("topP", 0.95);
            generationConfig.put("maxOutputTokens", 1024);
            requestBody.put("generationConfig", generationConfig);
            
            WebClient webClient = webClientBuilder.build();
            
            String response = webClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            // Parse response
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode candidates = jsonNode.get("candidates");
            if (candidates != null && candidates.isArray() && candidates.size() > 0) {
                JsonNode content = candidates.get(0).get("content");
                if (content != null) {
                    JsonNode parts = content.get("parts");
                    if (parts != null && parts.isArray() && parts.size() > 0) {
                        return parts.get(0).get("text").asText();
                    }
                }
            }
            
            return "Xin lỗi, tôi không thể xử lý câu hỏi của bạn lúc này. Vui lòng thử lại sau.";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi khi xử lý câu hỏi. Vui lòng thử lại sau.";
        }
    }
    
    public String chat(String userMessage) {
        return chat(userMessage, null);
    }
}

