package com.sneaker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressRequest {
    @NotBlank(message = "Tên người nhận không được để trống")
    private String name;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phoneNumber;
    
    @NotBlank(message = "Tỉnh/Thành phố không được để trống")
    private String provinceId;
    
    @NotBlank(message = "Quận/Huyện không được để trống")
    private String districtId;
    
    @NotBlank(message = "Phường/Xã không được để trống")
    private String wardId;
    
    @NotBlank(message = "Địa chỉ cụ thể không được để trống")
    private String specificAddress;
    
    private Boolean type = false;
    
    private Boolean isDefault = false;
}

