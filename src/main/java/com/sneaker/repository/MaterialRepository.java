package com.sneaker.repository;

import com.sneaker.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findByStatus(Material.Status status);
    boolean existsByName(String name);
}

