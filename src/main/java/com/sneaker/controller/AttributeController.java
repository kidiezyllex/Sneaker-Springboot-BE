package com.sneaker.controller;

import com.sneaker.dto.request.*;
import com.sneaker.dto.response.ApiResponse;
import com.sneaker.entity.*;
import com.sneaker.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.sneaker.dto.response.PaginationMeta;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attributes")
@RequiredArgsConstructor
@Tag(name = "Attributes", description = "Product Attribute Management APIs")
public class AttributeController {

    private final BrandService brandService;
    private final CategoryService categoryService;
    private final MaterialService materialService;
    private final ColorService colorService;
    private final SizeService sizeService;

    // ========== BRAND APIs ==========
    @GetMapping("/brands")
    @Operation(summary = "Get all brands", description = "Get list of all brands")
    public ResponseEntity<ApiResponse<List<Brand>>> getAllBrands(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        Page<Brand> brandPage = brandService.getAllBrands(status, page, limit);
        return ResponseEntity.ok(ApiResponse.success(
                "Lấy danh sách thương hiệu thành công",
                brandPage.getContent(),
                PaginationMeta.fromPage(brandPage)));
    }

    @GetMapping("/brands/{id}")
    @Operation(summary = "Get brand by ID", description = "Get brand details by ID")
    public ResponseEntity<ApiResponse<Brand>> getBrandById(@PathVariable Integer id) {
        Brand brand = brandService.getBrandById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin thương hiệu thành công", brand));
    }

    @PostMapping("/brands")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create brand", description = "Create a new brand (Admin only)")
    public ResponseEntity<ApiResponse<Brand>> createBrand(@Valid @RequestBody AttributeCreateRequest request) {
        Brand brand = brandService.createBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo thương hiệu thành công", brand));
    }

    @PutMapping("/brands/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update brand", description = "Update brand information (Admin only)")
    public ResponseEntity<ApiResponse<Brand>> updateBrand(
            @PathVariable Integer id,
            @Valid @RequestBody AttributeUpdateRequest request) {
        Brand brand = brandService.updateBrand(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thương hiệu thành công", brand));
    }

    @DeleteMapping("/brands/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete brand", description = "Delete brand (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa thương hiệu thành công", null));
    }

    // ========== CATEGORY APIs ==========
    @GetMapping("/categories")
    @Operation(summary = "Get all categories", description = "Get list of all categories")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        Page<Category> categoryPage = categoryService.getAllCategories(status, page, limit);
        return ResponseEntity.ok(ApiResponse.success(
                "Lấy danh sách danh mục thành công",
                categoryPage.getContent(),
                PaginationMeta.fromPage(categoryPage)));
    }

    @GetMapping("/categories/{id}")
    @Operation(summary = "Get category by ID", description = "Get category details by ID")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin danh mục thành công", category));
    }

    @PostMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create category", description = "Create a new category (Admin only)")
    public ResponseEntity<ApiResponse<Category>> createCategory(@Valid @RequestBody AttributeCreateRequest request) {
        Category category = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo danh mục thành công", category));
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update category", description = "Update category information (Admin only)")
    public ResponseEntity<ApiResponse<Category>> updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody AttributeUpdateRequest request) {
        Category category = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật danh mục thành công", category));
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete category", description = "Delete category (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa danh mục thành công", null));
    }

    // ========== MATERIAL APIs ==========
    @GetMapping("/materials")
    @Operation(summary = "Get all materials", description = "Get list of all materials")
    public ResponseEntity<ApiResponse<List<Material>>> getAllMaterials(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        Page<Material> materialPage = materialService.getAllMaterials(status, page, limit);
        return ResponseEntity.ok(ApiResponse.success(
                "Lấy danh sách chất liệu thành công",
                materialPage.getContent(),
                PaginationMeta.fromPage(materialPage)));
    }

    @GetMapping("/materials/{id}")
    @Operation(summary = "Get material by ID", description = "Get material details by ID")
    public ResponseEntity<ApiResponse<Material>> getMaterialById(@PathVariable Integer id) {
        Material material = materialService.getMaterialById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin chất liệu thành công", material));
    }

    @PostMapping("/materials")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create material", description = "Create a new material (Admin only)")
    public ResponseEntity<ApiResponse<Material>> createMaterial(@Valid @RequestBody AttributeCreateRequest request) {
        Material material = materialService.createMaterial(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo chất liệu thành công", material));
    }

    @PutMapping("/materials/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update material", description = "Update material information (Admin only)")
    public ResponseEntity<ApiResponse<Material>> updateMaterial(
            @PathVariable Integer id,
            @Valid @RequestBody AttributeUpdateRequest request) {
        Material material = materialService.updateMaterial(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật chất liệu thành công", material));
    }

    @DeleteMapping("/materials/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete material", description = "Delete material (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteMaterial(@PathVariable Integer id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa chất liệu thành công", null));
    }

    // ========== COLOR APIs ==========
    @GetMapping("/colors")
    @Operation(summary = "Get all colors", description = "Get list of all colors")
    public ResponseEntity<ApiResponse<List<Color>>> getAllColors(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        Page<Color> colorPage = colorService.getAllColors(status, page, limit);
        return ResponseEntity.ok(ApiResponse.success(
                "Lấy danh sách màu sắc thành công",
                colorPage.getContent(),
                PaginationMeta.fromPage(colorPage)));
    }

    @GetMapping("/colors/{id}")
    @Operation(summary = "Get color by ID", description = "Get color details by ID")
    public ResponseEntity<ApiResponse<Color>> getColorById(@PathVariable Integer id) {
        Color color = colorService.getColorById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin màu sắc thành công", color));
    }

    @PostMapping("/colors")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create color", description = "Create a new color (Admin only)")
    public ResponseEntity<ApiResponse<Color>> createColor(@Valid @RequestBody ColorCreateRequest request) {
        Color color = colorService.createColor(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo màu sắc thành công", color));
    }

    @PutMapping("/colors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update color", description = "Update color information (Admin only)")
    public ResponseEntity<ApiResponse<Color>> updateColor(
            @PathVariable Integer id,
            @Valid @RequestBody ColorUpdateRequest request) {
        Color color = colorService.updateColor(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật màu sắc thành công", color));
    }

    @DeleteMapping("/colors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete color", description = "Delete color (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteColor(@PathVariable Integer id) {
        colorService.deleteColor(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa màu sắc thành công", null));
    }

    // ========== SIZE APIs ==========
    @GetMapping("/sizes")
    @Operation(summary = "Get all sizes", description = "Get list of all sizes")
    public ResponseEntity<ApiResponse<List<Size>>> getAllSizes(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        Page<Size> sizePage = sizeService.getAllSizes(status, page, limit);
        return ResponseEntity.ok(ApiResponse.success(
                "Lấy danh sách kích thước thành công",
                sizePage.getContent(),
                PaginationMeta.fromPage(sizePage)));
    }

    @GetMapping("/sizes/{id}")
    @Operation(summary = "Get size by ID", description = "Get size details by ID")
    public ResponseEntity<ApiResponse<Size>> getSizeById(@PathVariable Integer id) {
        Size size = sizeService.getSizeById(id);
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin kích thước thành công", size));
    }

    @PostMapping("/sizes")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create size", description = "Create a new size (Admin only)")
    public ResponseEntity<ApiResponse<Size>> createSize(@Valid @RequestBody SizeCreateRequest request) {
        Size size = sizeService.createSize(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Tạo kích thước thành công", size));
    }

    @PutMapping("/sizes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update size", description = "Update size information (Admin only)")
    public ResponseEntity<ApiResponse<Size>> updateSize(
            @PathVariable Integer id,
            @Valid @RequestBody SizeUpdateRequest request) {
        Size size = sizeService.updateSize(id, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật kích thước thành công", size));
    }

    @DeleteMapping("/sizes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete size", description = "Delete size (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteSize(@PathVariable Integer id) {
        sizeService.deleteSize(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa kích thước thành công", null));
    }
}
