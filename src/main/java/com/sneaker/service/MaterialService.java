package com.sneaker.service;

import com.sneaker.dto.request.AttributeCreateRequest;
import com.sneaker.dto.request.AttributeUpdateRequest;
import com.sneaker.entity.Material;
import com.sneaker.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {
    
    private final MaterialRepository materialRepository;
    
    public List<Material> getAllMaterials(String status) {
        if (status != null) {
            return materialRepository.findByStatus(Material.Status.valueOf(status));
        }
        return materialRepository.findAll();
    }
    
    public Material getMaterialById(Integer id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chất liệu"));
    }
    
    @Transactional
    public Material createMaterial(AttributeCreateRequest request) {
        Material material = new Material();
        material.setName(request.getName());
        material.setStatus(Material.Status.valueOf(request.getStatus()));
        return materialRepository.save(material);
    }
    
    @Transactional
    public Material updateMaterial(Integer id, AttributeUpdateRequest request) {
        Material material = getMaterialById(id);
        if (request.getName() != null) material.setName(request.getName());
        if (request.getStatus() != null) material.setStatus(Material.Status.valueOf(request.getStatus()));
        return materialRepository.save(material);
    }
    
    @Transactional
    public void deleteMaterial(Integer id) {
        Material material = getMaterialById(id);
        materialRepository.delete(material);
    }
}

