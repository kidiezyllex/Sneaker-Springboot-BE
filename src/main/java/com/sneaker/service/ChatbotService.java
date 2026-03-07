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
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    @Value("${groq.api.key:}")
    private String apiKey;

    @Value("${groq.api.model:llama-3.3-70b-versatile}")
    private String model;

    @Value("${groq.api.url:https://api.groq.com/openai/v1/chat/completions}")
    private String apiUrl;

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    private final ChatbotConfigRepository configRepository;
    private final ChatbotTrainingRepository trainingRepository;
    private final ChatHistoryRepository chatHistoryRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    private static final String DEFAULT_SYSTEM_PROMPT = """
            Bạn là một trợ lý AI chuyên nghiệp và trung thực của cửa hàng StreetSneaker.

            QUY TẮC CỐT LÕI (PHẢI TUÂN THỦ):
            1. CHỈ TRẢ LỜI dựa trên thông tin trong mục "FAQ" hoặc "Sản phẩm thực tế" được cung cấp bên dưới.
            2. TUYỆT ĐỐI KHÔNG tự bịa ra sản phẩm, giá cả, hoặc thông số kỹ thuật nếu không có trong dữ liệu.
            3. Nếu khách hỏi về sản phẩm KHÔNG CÓ trong danh sách, hãy nói: "Hiện tại shop chưa có mẫu này, bạn tham khảo các mẫu tương tự khác nhé."
            4. Luôn báo GIÁ CHÍNH XÁC và TÌNH TRẠNG KHO từ dữ liệu.
            5. Nếu không biết chắc chắn, hãy hướng dẫn khách liên hệ Hotline/Zalo của shop (012345678) để được tư vấn kĩ hơn.

            Phong thái: Thân thiện, chuyên nghiệp, hỗ trợ nhiệt tình. Luôn trả lời bằng Tiếng Việt.
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
            // Log API configuration for debugging
            System.out.println("=== Groq API Configuration ===");
            System.out.println("API URL: " + apiUrl);
            System.out.println("Model: " + model);
            System.out.println("API Key exists: " + (apiKey != null && !apiKey.isEmpty()));
            System.out.println("API Key length: " + (apiKey != null ? apiKey.length() : 0));

            // First, try to find matching training data
            String trainingContext = buildTrainingContext(userMessage);

            // Search for real products in database
            String productContext = buildProductContext(userMessage);

            String systemPrompt = getSystemPrompt();
            if (!trainingContext.isEmpty()) {
                systemPrompt += "\n\n[HÀNH TRANG KIẾN THỨC - FAQ]:\n" + trainingContext;
            }

            if (!productContext.isEmpty()) {
                systemPrompt += "\n\n[DANH SÁCH SẢN PHẨM THỰC TẾ TRONG KHO]:\n" + productContext;
            } else {
                // Thêm một chút sản phẩm gợi ý nếu không tìm thấy đúng mẫu khách hỏi
                String latestProducts = buildLatestProductsContext();
                if (!latestProducts.isEmpty()) {
                    systemPrompt += "\n\n[CÁC MẪU GIÀY MỚI NHẤT TẠI SHOP (THAM KHẢO)]:\n" + latestProducts;
                }
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

            System.out.println("=== Request Body ===");
            System.out.println(objectMapper.writeValueAsString(requestBody));

            WebClient webClient = webClientBuilder.build();

            String response = webClient.post()
                    .uri(apiUrl)
                    .header("Authorization", "Bearer " + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .flatMap(errorBody -> {
                                        System.err.println("=== API Error Response ===");
                                        System.err.println("Status: " + clientResponse.statusCode());
                                        System.err.println("Body: " + errorBody);
                                        return Mono.<Throwable>error(new RuntimeException(
                                                "API Error: " + clientResponse.statusCode() + " - " + errorBody));
                                    }))
                    .bodyToMono(String.class)
                    .block();

            System.out.println("=== API Response ===");
            System.out.println(response);

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
            System.err.println("=== Chat Error ===");
            System.err.println("Error Type: " + e.getClass().getName());
            System.err.println("Error Message: " + e.getMessage());
            e.printStackTrace();
            return "Đã xảy ra lỗi khi xử lý câu hỏi. Vui lòng thử lại sau. Chi tiết: " + e.getMessage();
        }
    }

    private String buildTrainingContext(String userMessage) {
        List<ChatbotTraining> trainings = trainingRepository
                .findActiveTrainingsOrderedByPriority(ChatbotTraining.Status.ACTIVE);

        String lowerMessage = userMessage.toLowerCase();

        // Use a simple score-based matching instead of strict "contains"
        return trainings.stream()
                .map(t -> {
                    double score = calculateRelevance(lowerMessage, t.getQuestion().toLowerCase());
                    return new AbstractMap.SimpleEntry<>(t, score);
                })
                .filter(e -> e.getValue() > 0.3) // Relevancy threshold
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(e -> "Q: " + e.getKey().getQuestion() + "\nA: " + e.getKey().getAnswer())
                .collect(Collectors.joining("\n\n"));
    }

    private double calculateRelevance(String message, String question) {
        if (message.contains(question) || question.contains(message))
            return 1.0;

        Set<String> messageWords = new HashSet<>(Arrays.asList(message.split("\\s+")));
        Set<String> questionWords = new HashSet<>(Arrays.asList(question.split("\\s+")));

        long intersect = messageWords.stream().filter(questionWords::contains).count();
        return (double) intersect / Math.max(messageWords.size(), questionWords.size());
    }

    private String buildProductContext(String userMessage) {
        String cleanMessage = userMessage.toLowerCase()
                .replaceAll(
                        "\\b(giày|shop|bán|có|không|tìm|muốn|mua|tư|vấn|cho|hỏi|mẫu|loại|đôi|nào|giá|bao|nhiêu|ở|đâu)\\b",
                        "")
                .replaceAll("\\s+", " ")
                .trim();

        if (cleanMessage.isEmpty())
            return "";

        // Tách từ để tìm kiếm linh hoạt hơn (ví dụ: "NIKE AIR MAX" -> tìm cả "NIKE",
        // "AIR", "MAX")
        String[] keywords = cleanMessage.split("\\s+");
        Set<Product> foundProducts = new LinkedHashSet<>();

        for (String kw : keywords) {
            if (kw.length() < 2)
                continue;
            Page<Product> productPage = productRepository.searchWithFilters(
                    kw, null, null, null, null, null, null, null, Product.Status.ACTIVE,
                    org.springframework.data.domain.PageRequest.of(0, 5));
            foundProducts.addAll(productPage.getContent());
        }

        // Nếu vẫn ít kết quả, thử tìm theo nguyên câu
        if (foundProducts.size() < 3) {
            Page<Product> fallbackPage = productRepository.searchWithFilters(
                    cleanMessage, null, null, null, null, null, null, null, Product.Status.ACTIVE,
                    org.springframework.data.domain.PageRequest.of(0, 10));
            foundProducts.addAll(fallbackPage.getContent());
        }

        if (foundProducts.isEmpty())
            return "";

        return formatProductsForContext(new ArrayList<>(foundProducts).stream().limit(15).collect(Collectors.toList()));
    }

    private String buildLatestProductsContext() {
        List<Product> latest = productRepository.findTop4ByStatusOrderByCreatedAtDesc(Product.Status.ACTIVE);
        return formatProductsForContext(latest);
    }

    private String formatProductsForContext(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        for (Product p : products) {
            sb.append("- ").append(p.getName());
            if (p.getVariants() != null && !p.getVariants().isEmpty()) {
                sb.append(" | Giá: ").append(String.format("%,.0f VNĐ", p.getVariants().get(0).getPrice()));

                String sizes = p.getVariants().stream()
                        .map(v -> String.valueOf(v.getSize().getValue()))
                        .distinct()
                        .collect(Collectors.joining(", "));
                sb.append(" | Size: ").append(sizes);
            }
            sb.append(" | Hãng: ").append(p.getBrand().getName());
            sb.append("\n");
        }
        return sb.toString();
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

    @Transactional
    public Map<String, Object> syncProductsToTrainingData() {
        // 1. Delete old product-related training data to avoid duplicates
        trainingRepository.deleteByCategory(ChatbotTraining.Category.PRODUCT_INFO.name());

        List<Product> products = productRepository.findAll();
        int count = 0;

        for (Product p : products) {
            String brandName = p.getBrand().getName();
            String categoryName = p.getCategory().getName();

            // Generate basic info Q&A
            ChatbotTraining info = new ChatbotTraining();
            info.setCategory(ChatbotTraining.Category.PRODUCT_INFO.name());
            info.setQuestion("Thông tin về giày " + p.getName());

            StringBuilder answer = new StringBuilder();
            answer.append(p.getName()).append(" là mẫu giày thuộc thương hiệu ").append(brandName);
            answer.append(", nằm trong danh mục ").append(categoryName).append(".");
            if (p.getDescription() != null && !p.getDescription().isEmpty()) {
                answer.append("\n\nMô tả: ").append(p.getDescription());
            }

            if (p.getVariants() != null && !p.getVariants().isEmpty()) {
                ProductVariant first = p.getVariants().get(0);
                answer.append("\n\nGiá bán: ").append(String.format("%,.0f VNĐ", first.getPrice()));

                String sizes = p.getVariants().stream()
                        .map(v -> String.valueOf(v.getSize().getValue()))
                        .distinct()
                        .collect(Collectors.joining(", "));
                answer.append("\nCác size hiện có: ").append(sizes);
            }

            info.setAnswer(answer.toString());
            trainingRepository.save(info);
            count++;

            // Generate price-specific Q&A
            ChatbotTraining price = new ChatbotTraining();
            price.setCategory(ChatbotTraining.Category.PRODUCT_INFO.name());
            price.setQuestion("Giày " + p.getName() + " giá bao nhiêu?");
            if (p.getVariants() != null && !p.getVariants().isEmpty()) {
                price.setAnswer("Mẫu " + p.getName() + " hiện có giá là " +
                        String.format("%,.0f VNĐ", p.getVariants().get(0).getPrice()) + " tại shop.");
                trainingRepository.save(price);
                count++;
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("syncedProducts", products.size());
        result.put("createdTrainings", count);
        result.put("timestamp", LocalDateTime.now());
        return result;
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
