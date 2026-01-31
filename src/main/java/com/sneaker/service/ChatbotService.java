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

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.model}")
    private String model;

    @Value("${groq.api.url}")
    private String apiUrl;

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    private final ChatbotConfigRepository configRepository;
    private final ChatbotTrainingRepository trainingRepository;
    private final ChatHistoryRepository chatHistoryRepository;
    private final AccountRepository accountRepository;

    private static final String DEFAULT_SYSTEM_PROMPT = """
            Bạn là một trợ lý AI thân thiện và chuyên nghiệp của cửa hàng giày sneaker.
            Nhiệm vụ của bạn là hỗ trợ khách hàng với:
            1. Tư vấn về sản phẩm giày sneaker (size, màu sắc, chất liệu, phong cách)
            2. Hướng dẫn chọn size giày phù hợp
            3. Giới thiệu sản phẩm phù hợp với nhu cầu khách hàng
            4. Hướng dẫn đặt hàng và thanh toán
            5. Tra cứu thông tin đơn hàng
            6. Giải đáp chính sách bảo hành, đổi trả, vận chuyển
            7. Tư vấn về các chương trình khuyến mãi hiện có

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
        if (!config.containsKey("temperature"))
            config.put("temperature", 0.7);

        configRepository.findByConfigKey(ChatbotConfig.ConfigKey.MAX_OUTPUT_TOKENS.getValue())
                .ifPresent(c -> config.put("max_tokens", Integer.parseInt(c.getConfigValue())));
        if (!config.containsKey("max_tokens"))
            config.put("max_tokens", 1024);

        configRepository.findByConfigKey(ChatbotConfig.ConfigKey.TOP_P.getValue())
                .ifPresent(c -> config.put("top_p", Double.parseDouble(c.getConfigValue())));
        if (!config.containsKey("top_p"))
            config.put("top_p", 0.95);

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

            // Build request body for Groq API (OpenAI-compatible format)
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", model);

            // Build messages array
            List<Map<String, String>> messages = new ArrayList<>();

            // Add system message
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messages.add(systemMessage);

            // Load chat history for context (last 5 messages)
            if (sessionId != null && accountId != null) {
                List<ChatHistory> recentHistory = chatHistoryRepository
                        .findBySessionIdOrderByCreatedAtAsc(sessionId,
                                org.springframework.data.domain.PageRequest.of(0, 5))
                        .getContent();

                for (ChatHistory history : recentHistory) {
                    Map<String, String> userMsg = new HashMap<>();
                    userMsg.put("role", "user");
                    userMsg.put("content", history.getUserMessage());
                    messages.add(userMsg);

                    Map<String, String> assistantMsg = new HashMap<>();
                    assistantMsg.put("role", "assistant");
                    assistantMsg.put("content", history.getBotResponse());
                    messages.add(assistantMsg);
                }
            }

            // Add current user message
            Map<String, String> currentUserMsg = new HashMap<>();
            currentUserMsg.put("role", "user");
            currentUserMsg.put("content", userMessage);
            messages.add(currentUserMsg);

            requestBody.put("messages", messages);

            // Add generation config
            Map<String, Object> genConfig = getGenerationConfig();
            requestBody.putAll(genConfig);

            WebClient webClient = webClientBuilder.build();

            String response = webClient.post()
                    .uri(apiUrl)
                    .header("Authorization", "Bearer " + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Parse response
            String botResponse = parseGroqResponse(response);

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

    private String parseGroqResponse(String response) {
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode choices = jsonNode.get("choices");
            if (choices != null && choices.isArray() && choices.size() > 0) {
                JsonNode message = choices.get(0).get("message");
                if (message != null) {
                    JsonNode content = message.get("content");
                    if (content != null) {
                        return content.asText();
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

        if (request.getQuestion() != null)
            training.setQuestion(request.getQuestion());
        if (request.getAnswer() != null)
            training.setAnswer(request.getAnswer());
        if (request.getCategory() != null)
            training.setCategory(request.getCategory());
        if (request.getPriority() != null)
            training.setPriority(request.getPriority());
        if (request.getStatus() != null)
            training.setStatus(request.getStatus());

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

    public Page<ChatHistory> searchChatHistory(Integer accountId, String query,
            LocalDateTime startDate, LocalDateTime endDate,
            Pageable pageable) {
        return chatHistoryRepository.searchHistory(accountId, query, startDate, endDate, pageable);
    }

    public List<ChatHistory> getChatSession(String sessionId) {
        return chatHistoryRepository.findBySessionIdOrderByCreatedAtAsc(sessionId,
                org.springframework.data.domain.PageRequest.of(0, 100)).getContent();
    }
}
