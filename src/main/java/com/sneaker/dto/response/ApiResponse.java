package com.sneaker.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
    private PaginationMeta pagination;
    private ResponseMeta meta;
    
    // Success response without pagination
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .statusCode(200)
                .message(message)
                .data(data)
                .meta(ResponseMeta.create())
                .build();
    }
    
    // Success response with default message
    public static <T> ApiResponse<T> success(T data) {
        return success("Success", data);
    }
    
    // Success response with pagination
    public static <T> ApiResponse<T> success(String message, T data, PaginationMeta pagination) {
        return ApiResponse.<T>builder()
                .statusCode(200)
                .message(message)
                .data(data)
                .pagination(pagination)
                .meta(ResponseMeta.create())
                .build();
    }
    
    // Error response
    public static <T> ApiResponse<T> error(String message) {
        return error(400, message);
    }
    
    // Error response with custom status code
    public static <T> ApiResponse<T> error(Integer statusCode, String message) {
        return ApiResponse.<T>builder()
                .statusCode(statusCode)
                .message(message)
                .data(null)
                .meta(ResponseMeta.create())
                .build();
    }
    
    // Created response (201)
    public static <T> ApiResponse<T> created(String message, T data) {
        return ApiResponse.<T>builder()
                .statusCode(201)
                .message(message)
                .data(data)
                .meta(ResponseMeta.create())
                .build();
    }
}

