package com.sneaker.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sneaker.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {
    
    @Value("${cloudinary.cloud-name}")
    private String cloudName;
    
    @Value("${cloudinary.api-key}")
    private String apiKey;
    
    @Value("${cloudinary.api-secret}")
    private String apiSecret;
    
    private Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
        ));
    }
    
    public Map<String, Object> uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Không có ảnh nào được cung cấp");
        }
        
        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("File không phải là ảnh");
        }
        
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer accountId = userDetails.getAccount().getId();
        
        String originalName = file.getOriginalFilename();
        String fileExtension = originalName != null ? 
                originalName.substring(originalName.lastIndexOf(".") + 1) : "jpg";
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
        
        String folderPath = "sneaker/" + accountId + "/images";
        
        Cloudinary cloudinary = getCloudinary();
        
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
            file.getBytes(),
            ObjectUtils.asMap(
                "resource_type", "image",
                "public_id", folderPath + "/" + uniqueFileName.split("\\.")[0],
                "folder", folderPath,
                "overwrite", true,
                "use_filename", true,
                "unique_filename", false
            )
        );
        
        return Map.of(
            "imageUrl", uploadResult.get("secure_url"),
            "publicId", uploadResult.get("public_id")
        );
    }
}

