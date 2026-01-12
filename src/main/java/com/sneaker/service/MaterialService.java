package com.sneaker.service;

import com.sneaker.dto.request.AttributeCreateRequest;
import com.sneaker.dto.request.AttributeUpdateRequest;
import com.sneaker.entity.Material;
import com.sneaker.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    public Page<Material> getAllMaterials(String status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").ascending());
        if (status != null) {
            return materialRepository.findByStatus(Material.Status.valueOf(status), pageable);
        }
        return materialRepository.findAll(pageable);
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
        if (request.getName() != null)
            material.setName(request.getName());
        if (request.getStatus() != null)
            material.setStatus(Material.Status.valueOf(request.getStatus()));
        return materialRepository.save(material);
    }

    @Transactional
    public void deleteMaterial(Integer id) {
        Material material = getMaterialById(id);
        materialRepository.delete(material);
    }
}
