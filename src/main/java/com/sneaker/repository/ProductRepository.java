package com.sneaker.repository;

import com.sneaker.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByCode(String code);

    boolean existsByCode(String code);

    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN p.variants v " +
            "WHERE " +
            "(:name IS NULL OR p.name LIKE %:name%) AND " +
            "(:brandId IS NULL OR p.brand.id = :brandId) AND " +
            "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
            "(:materialId IS NULL OR p.material.id = :materialId) AND " +
            "(:status IS NULL OR p.status = :status)")
    Page<Product> findWithFilters(
            @Param("name") String name,
            @Param("brandId") Integer brandId,
            @Param("categoryId") Integer categoryId,
            @Param("materialId") Integer materialId,
            @Param("status") Product.Status status,
            Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN p.variants v " +
            "WHERE " +
            "(:keyword IS NULL OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%) AND " +
            "(:brandId IS NULL OR p.brand.id = :brandId) AND " +
            "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
            "(:materialId IS NULL OR p.material.id = :materialId) AND " +
            "(:status IS NULL OR p.status = :status)")
    Page<Product> searchWithFilters(
            @Param("keyword") String keyword,
            @Param("brandId") Integer brandId,
            @Param("categoryId") Integer categoryId,
            @Param("materialId") Integer materialId,
            @Param("status") Product.Status status,
            Pageable pageable);

    java.util.List<Product> findTop4ByStatusOrderByCreatedAtDesc(Product.Status status);

    @Query(value = "SELECT * FROM products WHERE status = :status ORDER BY RAND() LIMIT 4", nativeQuery = true)
    java.util.List<Product> findRandomTop4ByStatus(@Param("status") String status);
}
