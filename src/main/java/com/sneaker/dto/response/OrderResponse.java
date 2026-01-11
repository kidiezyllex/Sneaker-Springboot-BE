package com.sneaker.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sneaker.entity.Order;
import com.sneaker.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Integer id;
    private String code;
    private CustomerInfo customer;
    private StaffInfo staff;
    private VoucherInfo voucher;
    private BigDecimal subTotal;
    private BigDecimal discount;
    private BigDecimal total;
    private String shippingName;
    private String shippingPhoneNumber;
    private String shippingProvinceId;
    private String shippingDistrictId;
    private String shippingWardId;
    private String shippingSpecificAddress;
    private Order.PaymentMethod paymentMethod;
    private Order.PaymentStatus paymentStatus;
    private Order.OrderStatus orderStatus;
    private List<OrderItemInfo> items;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomerInfo {
        private Integer id;
        private String code;
        private String fullName;
        private String email;
        private String phoneNumber;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StaffInfo {
        private Integer id;
        private String code;
        private String fullName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoucherInfo {
        private Integer id;
        private String code;
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemInfo {
        private Integer id;
        private ProductVariantInfo variant;
        private Integer quantity;
        private BigDecimal price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductVariantInfo {
        private Integer id;
        private String productName;
        private String colorName;
        private String sizeName;
    }

    public static OrderResponse from(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCode(order.getCode());

        // Customer info
        if (order.getCustomer() != null) {
            CustomerInfo customerInfo = new CustomerInfo();
            customerInfo.setId(order.getCustomer().getId());
            customerInfo.setCode(order.getCustomer().getCode());
            customerInfo.setFullName(order.getCustomer().getFullName());
            customerInfo.setEmail(order.getCustomer().getEmail());
            customerInfo.setPhoneNumber(order.getCustomer().getPhoneNumber());
            response.setCustomer(customerInfo);
        }

        // Staff info
        if (order.getStaff() != null) {
            StaffInfo staffInfo = new StaffInfo();
            staffInfo.setId(order.getStaff().getId());
            staffInfo.setCode(order.getStaff().getCode());
            staffInfo.setFullName(order.getStaff().getFullName());
            response.setStaff(staffInfo);
        }

        // Voucher info
        if (order.getVoucher() != null) {
            VoucherInfo voucherInfo = new VoucherInfo();
            voucherInfo.setId(order.getVoucher().getId());
            voucherInfo.setCode(order.getVoucher().getCode());
            voucherInfo.setName(order.getVoucher().getName());
            response.setVoucher(voucherInfo);
        }

        response.setSubTotal(order.getSubTotal());
        response.setDiscount(order.getDiscount());
        response.setTotal(order.getTotal());
        response.setShippingName(order.getShippingName());
        response.setShippingPhoneNumber(order.getShippingPhoneNumber());
        response.setShippingProvinceId(order.getShippingProvinceId());
        response.setShippingDistrictId(order.getShippingDistrictId());
        response.setShippingWardId(order.getShippingWardId());
        response.setShippingSpecificAddress(order.getShippingSpecificAddress());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setPaymentStatus(order.getPaymentStatus());
        response.setOrderStatus(order.getOrderStatus());

        // Order items
        if (order.getItems() != null) {
            List<OrderItemInfo> itemInfos = order.getItems().stream()
                    .map(item -> {
                        OrderItemInfo itemInfo = new OrderItemInfo();
                        itemInfo.setId(item.getId());
                        itemInfo.setQuantity(item.getQuantity());
                        itemInfo.setPrice(item.getPrice());

                        if (item.getVariant() != null) {
                            ProductVariantInfo variantInfo = new ProductVariantInfo();
                            variantInfo.setId(item.getVariant().getId());
                            variantInfo.setProductName(item.getVariant().getProduct().getName());
                            variantInfo.setColorName(item.getVariant().getColor().getName());
                            variantInfo.setSizeName(item.getVariant().getSize().getValue().toString());
                            itemInfo.setVariant(variantInfo);
                        }

                        return itemInfo;
                    })
                    .collect(Collectors.toList());
            response.setItems(itemInfos);
        }

        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());

        return response;
    }
}
