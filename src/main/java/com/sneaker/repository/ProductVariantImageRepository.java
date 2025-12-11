package com.sneaker.repository;

import com.sneaker.entity.ProductVariantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantImageRepository extends JpaRepository<ProductVariantImage, Integer> {
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM ProductVariantImage pvi WHERE pvi.variant.id = :variantId")
    void deleteByVariantId(@Param("variantId") Integer variantId);
}

