package com.sneaker.repository;

import com.sneaker.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findByStatus(Size.Status status);
}

