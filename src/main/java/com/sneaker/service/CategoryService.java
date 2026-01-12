package com.sneaker.service;

import com.sneaker.dto.request.AttributeCreateRequest;
import com.sneaker.dto.request.AttributeUpdateRequest;
import com.sneaker.entity.Category;
import com.sneaker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(String status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("name").ascending());
        if (status != null) {
            return categoryRepository.findByStatus(Category.Status.valueOf(status), pageable);
        }
        return categoryRepository.findAll(pageable);
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục"));
    }

    @Transactional
    public Category createCategory(AttributeCreateRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setStatus(Category.Status.valueOf(request.getStatus()));
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Integer id, AttributeUpdateRequest request) {
        Category category = getCategoryById(id);
        if (request.getName() != null)
            category.setName(request.getName());
        if (request.getStatus() != null)
            category.setStatus(Category.Status.valueOf(request.getStatus()));
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Integer id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
