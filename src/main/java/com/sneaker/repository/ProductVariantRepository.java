package com.sneaker.repository;

import com.sneaker.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    Optional<ProductVariant> findByProductIdAndColorIdAndSizeId(
        Integer productId, Integer colorId, Integer sizeId
    );
}

