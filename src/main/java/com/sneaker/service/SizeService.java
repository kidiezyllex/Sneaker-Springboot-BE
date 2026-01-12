package com.sneaker.service;

import com.sneaker.dto.request.SizeCreateRequest;
import com.sneaker.dto.request.SizeUpdateRequest;
import com.sneaker.entity.Size;
import com.sneaker.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;

    public Page<Size> getAllSizes(String status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("value").ascending());
        if (status != null) {
            return sizeRepository.findByStatus(Size.Status.valueOf(status), pageable);
        }
        return sizeRepository.findAll(pageable);
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
        if (request.getValue() != null)
            size.setValue(request.getValue());
        if (request.getStatus() != null)
            size.setStatus(Size.Status.valueOf(request.getStatus()));
        return sizeRepository.save(size);
    }

    @Transactional
    public void deleteSize(Integer id) {
        Size size = getSizeById(id);
        sizeRepository.delete(size);
    }
}
