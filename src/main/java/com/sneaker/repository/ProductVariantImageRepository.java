package com.sneaker.repository;

import com.sneaker.entity.ProductVariantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantImageRepository extends JpaRepository<ProductVariantImage, Integer> {
}

