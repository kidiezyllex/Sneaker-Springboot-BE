package com.sneaker.repository;

import com.sneaker.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Page<Material> findByStatus(Material.Status status, Pageable pageable);

    boolean existsByName(String name);
}
