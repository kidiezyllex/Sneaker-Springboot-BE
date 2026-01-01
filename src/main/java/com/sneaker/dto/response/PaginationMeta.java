package com.sneaker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationMeta {
    private Long total;          // Tổng số bản ghi
    private Integer count;       // Số bản ghi trong trang hiện tại
    private Integer perPage;     // Số bản ghi mỗi trang
    private Integer currentPage; // Trang hiện tại
    private Integer totalPages;  // Tổng số trang
    
    // Factory method để tạo từ Spring Data Page
    public static PaginationMeta fromPage(org.springframework.data.domain.Page<?> page) {
        return PaginationMeta.builder()
                .total(page.getTotalElements())
                .count(page.getNumberOfElements())
                .perPage(page.getSize())
                .currentPage(page.getNumber() + 1) // Spring Page bắt đầu từ 0
                .totalPages(page.getTotalPages())
                .build();
    }
    
    // Factory method tạo thủ công
    public static PaginationMeta create(Long total, Integer count, Integer perPage, Integer currentPage) {
        int totalPages = (int) Math.ceil((double) total / perPage);
        return PaginationMeta.builder()
                .total(total)
                .count(count)
                .perPage(perPage)
                .currentPage(currentPage)
                .totalPages(totalPages)
                .build();
    }
}
