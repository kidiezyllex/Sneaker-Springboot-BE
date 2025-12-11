package com.sneaker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sneaker.dto.request.*;
import com.sneaker.entity.*;
import com.sneaker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    private final ChatbotConfigRepository configRepository;
    private final ChatbotTrainingRepository trainingRepository;
    private final ChatHistoryRepository chatHistoryRepository;
    private final AccountRepository accountRepository;
    
    private static final String DEFAULT_SYSTEM_PROMPT = """
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
    
    private String getSystemPrompt() {
        return configRepository.findByConfigKey(ChatbotConfig.ConfigKey.SYSTEM_PROMPT.getValue())
                .map(ChatbotConfig::getConfigValue)
                .orElse(DEFAULT_SYSTEM_PROMPT);
    }
    
    private Map<String, Object> getGenerationConfig() {
        Map<String, Object> config = new HashMap<>();
        
        configRepository.findByConfigKey(ChatbotConfig.ConfigKey.TEMPERATURE.getValue())
                .ifPresent(c -> config.put("temperature", Double.parseDouble(c.getConfigValue())));
        if (!config.containsKey("temperature")) config.put("temperature", 0.7);
        
        configRepository.findByConfigKey(ChatbotConfig.ConfigKey.MAX_OUTPUT_TOKENS.getValue())
                .ifPresent(c -> config.put("maxOutputTokens", Integer.parseInt(c.getConfigValue())));
        if (!config.containsKey("maxOutputTokens")) config.put("maxOutputTokens", 1024);
        
        configRepository.findByConfigKey(ChatbotConfig.ConfigKey.TOP_K.getValue())
                .ifPresent(c -> config.put("topK", Integer.parseInt(c.getConfigValue())));
        if (!config.containsKey("topK")) config.put("topK", 40);
        
        configRepository.findByConfigKey(ChatbotConfig.ConfigKey.TOP_P.getValue())
                .ifPresent(c -> config.put("topP", Double.parseDouble(c.getConfigValue())));
        if (!config.containsKey("topP")) config.put("topP", 0.95);
        
        return config;
    }
    
    @Transactional
    public String chat(String userMessage, String sessionId, Integer accountId) {
        try {
            // First, try to find matching training data
            String trainingContext = buildTrainingContext(userMessage);
            
            String systemPrompt = getSystemPrompt();
            if (!trainingContext.isEmpty()) {
                systemPrompt += "\n\nThông tin tham khảo:\n" + trainingContext;
            }
            
            String url = String.format("%s/%s:generateContent?key=%s", 
                apiUrl, model, apiKey);
            
            Map<String, Object> requestBody = new HashMap<>();
            
            // Build contents array
            List<Map<String, Object>> contents = new ArrayList<>();
            
            // Add system prompt
            Map<String, Object> systemMessage = new HashMap<>();
            systemMessage.put("role", "user");
            systemMessage.put("parts", List.of(Map.of("text", systemPrompt)));
            contents.add(systemMessage);
            
            // Add current user message
            Map<String, Object> userMsg = new HashMap<>();
            userMsg.put("role", "user");
            userMsg.put("parts", List.of(Map.of("text", userMessage)));
            contents.add(userMsg);
            
            requestBody.put("contents", contents);
            requestBody.put("generationConfig", getGenerationConfig());
            
            WebClient webClient = webClientBuilder.build();
            
            String response = webClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            // Parse response
            String botResponse = parseGeminiResponse(response);
            
            // Save chat history
            if (accountId != null) {
                Account account = accountRepository.findById(accountId)
                        .orElse(null);
                if (account != null) {
                    ChatHistory chatHistory = new ChatHistory();
                    chatHistory.setAccount(account);
                    chatHistory.setUserMessage(userMessage);
                    chatHistory.setBotResponse(botResponse);
                    chatHistory.setSessionId(sessionId != null ? sessionId : UUID.randomUUID().toString());
                    chatHistoryRepository.save(chatHistory);
                }
            }
            
            return botResponse;
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi khi xử lý câu hỏi. Vui lòng thử lại sau.";
        }
    }
    
    private String buildTrainingContext(String userMessage) {
        List<ChatbotTraining> trainings = trainingRepository
                .findActiveTrainingsOrderedByPriority(ChatbotTraining.Status.ACTIVE);
        
        // Simple keyword matching (can be enhanced with NLP)
        String lowerMessage = userMessage.toLowerCase();
        return trainings.stream()
                .filter(t -> lowerMessage.contains(t.getQuestion().toLowerCase()) || 
                           t.getQuestion().toLowerCase().contains(lowerMessage))
                .limit(3)
                .map(t -> "Q: " + t.getQuestion() + "\nA: " + t.getAnswer())
                .collect(Collectors.joining("\n\n"));
    }
    
    private String parseGeminiResponse(String response) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Xin lỗi, tôi không thể xử lý câu hỏi của bạn lúc này. Vui lòng thử lại sau.";
    }
    
    public String chat(String userMessage) {
        return chat(userMessage, null, null);
    }
    
    // ========== Config Management ==========
    
    public ChatbotConfig getConfig(String configKey) {
        return configRepository.findByConfigKey(configKey)
                .orElseThrow(() -> new RuntimeException("Config not found: " + configKey));
    }
    
    public List<ChatbotConfig> getAllConfigs() {
        return configRepository.findAll();
    }
    
    @Transactional
    public ChatbotConfig updateConfig(String configKey, ChatbotConfigUpdateRequest request) {
        ChatbotConfig config = configRepository.findByConfigKey(configKey)
                .orElseGet(() -> {
                    ChatbotConfig newConfig = new ChatbotConfig();
                    newConfig.setConfigKey(configKey);
                    return newConfig;
                });
        
        config.setConfigValue(request.getConfigValue());
        if (request.getDescription() != null) {
            config.setDescription(request.getDescription());
        }
        
        return configRepository.save(config);
    }
    
    // ========== Training Management ==========
    
    @Transactional
    public ChatbotTraining createTraining(ChatbotTrainingCreateRequest request) {
        ChatbotTraining training = new ChatbotTraining();
        training.setQuestion(request.getQuestion());
        training.setAnswer(request.getAnswer());
        training.setCategory(request.getCategory());
        training.setPriority(request.getPriority() != null ? request.getPriority() : 0);
        training.setStatus(ChatbotTraining.Status.ACTIVE);
        
        return trainingRepository.save(training);
    }
    
    public Page<ChatbotTraining> getTrainings(ChatbotTraining.Status status, String category, Pageable pageable) {
        if (category != null && !category.isEmpty()) {
            return trainingRepository.findByCategoryAndStatus(category, status, pageable);
        }
        return trainingRepository.findByStatus(status, pageable);
    }
    
    public ChatbotTraining getTrainingById(Integer id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found"));
    }
    
    @Transactional
    public ChatbotTraining updateTraining(Integer id, ChatbotTrainingUpdateRequest request) {
        ChatbotTraining training = getTrainingById(id);
        
        if (request.getQuestion() != null) training.setQuestion(request.getQuestion());
        if (request.getAnswer() != null) training.setAnswer(request.getAnswer());
        if (request.getCategory() != null) training.setCategory(request.getCategory());
        if (request.getPriority() != null) training.setPriority(request.getPriority());
        if (request.getStatus() != null) training.setStatus(request.getStatus());
        
        return trainingRepository.save(training);
    }
    
    @Transactional
    public void deleteTraining(Integer id) {
        ChatbotTraining training = getTrainingById(id);
        trainingRepository.delete(training);
    }
    
    // ========== Chat History Management ==========
    
    public Page<ChatHistory> getChatHistory(Integer accountId, Pageable pageable) {
        return chatHistoryRepository.findByAccountIdOrderByCreatedAtDesc(accountId, pageable);
    }
    
    public Page<ChatHistory> getChatHistoryBySession(String sessionId, Pageable pageable) {
        return chatHistoryRepository.findBySessionIdOrderByCreatedAtAsc(sessionId, pageable);
    }
    
    @Transactional
    public ChatHistory rateChat(Integer chatId, ChatRatingRequest request) {
        ChatHistory chatHistory = chatHistoryRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat history not found"));
        
        chatHistory.setRating(request.getRating());
        if (request.getFeedback() != null) {
            chatHistory.setFeedback(request.getFeedback());
        }
        
        return chatHistoryRepository.save(chatHistory);
    }
    
    public Map<String, Object> getChatStatistics(LocalDateTime startDate, LocalDateTime endDate) {
        Long totalChats = chatHistoryRepository.countByDateRange(startDate, endDate);
        Double avgRating = chatHistoryRepository.getAverageRatingByDateRange(startDate, endDate);
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalChats", totalChats);
        stats.put("averageRating", avgRating != null ? avgRating : 0.0);
        stats.put("startDate", startDate);
        stats.put("endDate", endDate);
        
        return stats;
    }
}

