package com.sneaker.repository;

import com.sneaker.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByStatus(Category.Status status, Pageable pageable);

    boolean existsByName(String name);
}
