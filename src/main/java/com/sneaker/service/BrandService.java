package com.sneaker.service;

import com.sneaker.dto.request.AttributeCreateRequest;
import com.sneaker.dto.request.AttributeUpdateRequest;
import com.sneaker.entity.Brand;
import com.sneaker.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    
    private final BrandRepository brandRepository;
    
    public List<Brand> getAllBrands(String status) {
        if (status != null) {
            return brandRepository.findByStatus(Brand.Status.valueOf(status));
        }
        return brandRepository.findAll();
    }
    
    public Brand getBrandById(Integer id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thương hiệu"));
    }
    
    @Transactional
    public Brand createBrand(AttributeCreateRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setStatus(Brand.Status.valueOf(request.getStatus()));
        return brandRepository.save(brand);
    }
    
    @Transactional
    public Brand updateBrand(Integer id, AttributeUpdateRequest request) {
        Brand brand = getBrandById(id);
        if (request.getName() != null) brand.setName(request.getName());
        if (request.getStatus() != null) brand.setStatus(Brand.Status.valueOf(request.getStatus()));
        return brandRepository.save(brand);
    }
    
    @Transactional
    public void deleteBrand(Integer id) {
        Brand brand = getBrandById(id);
        brandRepository.delete(brand);
    }
}

