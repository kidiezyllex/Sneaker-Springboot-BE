package com.sneaker.dto.request;

import com.sneaker.entity.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateRequest {
    
    private String orderId; // Optional custom order code
    
    @NotNull(message = "customerId là bắt buộc")
    private Integer customerId;
    
    @NotEmpty(message = "items là bắt buộc và phải là mảng không rỗng")
    @Valid
    private List<OrderItemRequest> items;
    
    private Integer voucherId;
    
    @NotNull(message = "subTotal là bắt buộc")
    @DecimalMin(value = "0", message = "subTotal phải lớn hơn hoặc bằng 0")
    private BigDecimal subTotal;
    
    private BigDecimal discount = BigDecimal.ZERO;
    
    @NotNull(message = "total là bắt buộc")
    @DecimalMin(value = "0", message = "total phải lớn hơn hoặc bằng 0")
    private BigDecimal total;
    
    @NotNull(message = "shippingAddress là bắt buộc")
    @Valid
    private ShippingAddressRequest shippingAddress;
    
    @NotNull(message = "paymentMethod là bắt buộc")
    private Order.PaymentMethod paymentMethod;
    
    private PaymentInfoRequest paymentInfo;
    
    @Data
    public static class OrderItemRequest {
        private Integer productId;
        private Integer product; // Alternative field name
        
        @NotNull(message = "quantity phải là số nguyên dương")
        @Min(value = 1, message = "quantity phải lớn hơn 0")
        private Integer quantity;
        
        @NotNull(message = "price phải là số dương")
        @DecimalMin(value = "0.01", message = "price phải lớn hơn 0")
        private BigDecimal price;
        
        @Valid
        private VariantRequest variant;
        
        private Integer productVariantId; // Alternative field name
        
        @Data
        public static class VariantRequest {
            @NotNull(message = "variant.colorId phải là số nguyên dương")
            private Integer colorId;
            
            @NotNull(message = "variant.sizeId phải là số nguyên dương")
            private Integer sizeId;
        }
    }
    
    @Data
    public static class ShippingAddressRequest {
        @NotBlank(message = "shippingAddress.name là bắt buộc")
        private String name;
        
        @NotBlank(message = "shippingAddress.phoneNumber là bắt buộc")
        private String phoneNumber;
        
        private String provinceId;
        private String districtId;
        private String wardId;
        
        @NotBlank(message = "shippingAddress.specificAddress là bắt buộc")
        private String specificAddress;
    }
    
    @Data
    public static class PaymentInfoRequest {
        private String status;
    }
}

