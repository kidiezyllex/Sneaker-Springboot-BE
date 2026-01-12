package com.sneaker.repository;

import com.sneaker.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Page<Brand> findByStatus(Brand.Status status, Pageable pageable);

    boolean existsByName(String name);
}
