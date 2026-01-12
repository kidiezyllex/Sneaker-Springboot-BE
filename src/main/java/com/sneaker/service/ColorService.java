package com.sneaker.service;

import com.sneaker.dto.request.ColorCreateRequest;
import com.sneaker.dto.request.ColorUpdateRequest;
import com.sneaker.entity.Color;
import com.sneaker.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;

    public Page<Color> getAllColors(String status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").ascending());
        if (status != null) {
            return colorRepository.findByStatus(Color.Status.valueOf(status), pageable);
        }
        return colorRepository.findAll(pageable);
    }

    public Color getColorById(Integer id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy màu sắc"));
    }

    @Transactional
    public Color createColor(ColorCreateRequest request) {
        Color color = new Color();
        color.setName(request.getName());
        color.setCode(request.getCode());
        color.setStatus(Color.Status.valueOf(request.getStatus()));
        return colorRepository.save(color);
    }

    @Transactional
    public Color updateColor(Integer id, ColorUpdateRequest request) {
        Color color = getColorById(id);
        if (request.getName() != null)
            color.setName(request.getName());
        if (request.getCode() != null)
            color.setCode(request.getCode());
        if (request.getStatus() != null)
            color.setStatus(Color.Status.valueOf(request.getStatus()));
        return colorRepository.save(color);
    }

    @Transactional
    public void deleteColor(Integer id) {
        Color color = getColorById(id);
        colorRepository.delete(color);
    }
}
