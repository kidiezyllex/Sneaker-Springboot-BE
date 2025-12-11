package com.sneaker.dto.request;

import com.sneaker.entity.ChatbotTraining;
import lombok.Data;

@Data
public class ChatbotTrainingUpdateRequest {
    private String question;
    private String answer;
    private String category;
    private Integer priority;
    private ChatbotTraining.Status status;
}

