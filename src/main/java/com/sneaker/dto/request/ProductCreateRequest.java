package com.sneaker.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductCreateRequest {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Thương hiệu không được để trống")
    private Integer brand;

    @NotNull(message = "Danh mục không được để trống")
    private Integer category;

    @NotNull(message = "Chất liệu không được để trống")
    private Integer material;

    private String description;

    @DecimalMin(value = "0", message = "Trọng lượng phải lớn hơn hoặc bằng 0")
    private BigDecimal weight;

    @NotEmpty(message = "Sản phẩm phải có ít nhất một biến thể")
    @Valid
    private List<VariantRequest> variants;

    @Data
    public static class VariantRequest {
        @NotNull(message = "colorId không được để trống")
        private Integer colorId;

        @NotNull(message = "sizeId không được để trống")
        private Integer sizeId;

        @NotNull(message = "Giá không được để trống")
        @DecimalMin(value = "0.01", message = "Giá phải lớn hơn 0")
        private BigDecimal price;

        @Min(value = 0, message = "Số lượng tồn kho không được âm")
        private Integer stock = 0;

        private List<String> images;
    }
}
