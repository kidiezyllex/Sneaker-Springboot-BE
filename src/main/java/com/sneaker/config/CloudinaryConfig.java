package com.sneaker.config;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class CloudinaryConfig {
    
    private final Environment environment;
    
    @Value("${cloudinary.cloud-name:}")
    private String cloudName;
    
    @Value("${cloudinary.api-key:}")
    private String apiKey;
    
    @Value("${cloudinary.api-secret:}")
    private String apiSecret;
    
    public CloudinaryConfig(Environment environment) {
        this.environment = environment;
    }
    
    @Bean
    public Cloudinary cloudinary() {
        // Ưu tiên sử dụng CLOUDINARY_URL từ environment variable
        String cloudinaryUrl = environment.getProperty("CLOUDINARY_URL");
        if (cloudinaryUrl == null || cloudinaryUrl.isEmpty()) {
            cloudinaryUrl = System.getenv("CLOUDINARY_URL");
        }
        
        if (cloudinaryUrl != null && !cloudinaryUrl.isEmpty()) {
            log.info("Using CLOUDINARY_URL for Cloudinary configuration");
            return new Cloudinary(cloudinaryUrl);
        }
        
        // Đọc từ environment variables hoặc application.properties
        String finalCloudName = getProperty("CLOUDINARY_CLOUD_NAME", cloudName);
        String finalApiKey = getProperty("CLOUDINARY_API_KEY", apiKey);
        String finalApiSecret = getProperty("CLOUDINARY_API_SECRET", apiSecret);
        
        log.info("Cloudinary config - cloudName: {}, apiKey: {}, apiSecret: {}", 
                 finalCloudName != null && !finalCloudName.isEmpty() ? finalCloudName : "empty",
                 finalApiKey != null && !finalApiKey.isEmpty() ? "***" : "empty",
                 finalApiSecret != null && !finalApiSecret.isEmpty() ? "***" : "empty");
        
        if (finalCloudName == null || finalCloudName.isEmpty() || 
            finalApiKey == null || finalApiKey.isEmpty() || 
            finalApiSecret == null || finalApiSecret.isEmpty()) {
            log.error("Cloudinary configuration is missing! cloudName: {}, apiKey: {}, apiSecret: {}", 
                     finalCloudName, 
                     finalApiKey != null && !finalApiKey.isEmpty() ? "***" : "empty",
                     finalApiSecret != null && !finalApiSecret.isEmpty() ? "***" : "empty");
            throw new IllegalStateException(
                "Cloudinary configuration is missing. " +
                "Please set CLOUDINARY_URL environment variable or set " +
                "CLOUDINARY_CLOUD_NAME, CLOUDINARY_API_KEY, and CLOUDINARY_API_SECRET environment variables, " +
                "or configure them in application.properties"
            );
        }
        
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", finalCloudName);
        config.put("api_key", finalApiKey);
        config.put("api_secret", finalApiSecret);
        
        log.info("Cloudinary configured successfully with cloud_name: {}", finalCloudName);
        return new Cloudinary(config);
    }
    
    private String getProperty(String envKey, String defaultValue) {
        // Thử đọc từ Spring Environment trước
        String value = environment.getProperty(envKey);
        if (value == null || value.isEmpty()) {
            // Nếu không có, thử đọc từ System environment
            value = System.getenv(envKey);
        }
        // Nếu vẫn không có, sử dụng defaultValue từ @Value
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }
}

