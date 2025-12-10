package com.sneaker.service;

import com.sneaker.dto.request.SizeCreateRequest;
import com.sneaker.dto.request.SizeUpdateRequest;
import com.sneaker.entity.Size;
import com.sneaker.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeService {
    
    private final SizeRepository sizeRepository;
    
    public List<Size> getAllSizes(String status) {
        if (status != null) {
            return sizeRepository.findByStatus(Size.Status.valueOf(status));
        }
        return sizeRepository.findAll();
    }
    
    public Size getSizeById(Integer id) {
        return sizeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kích thước"));
    }
    
    @Transactional
    public Size createSize(SizeCreateRequest request) {
        Size size = new Size();
        size.setValue(request.getValue());
        size.setStatus(Size.Status.valueOf(request.getStatus()));
        return sizeRepository.save(size);
    }
    
    @Transactional
    public Size updateSize(Integer id, SizeUpdateRequest request) {
        Size size = getSizeById(id);
        if (request.getValue() != null) size.setValue(request.getValue());
        if (request.getStatus() != null) size.setStatus(Size.Status.valueOf(request.getStatus()));
        return sizeRepository.save(size);
    }
    
    @Transactional
    public void deleteSize(Integer id) {
        Size size = getSizeById(id);
        sizeRepository.delete(size);
    }
}

