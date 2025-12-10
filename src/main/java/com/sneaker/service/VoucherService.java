package com.sneaker.service;

import com.sneaker.dto.request.VoucherCreateRequest;
import com.sneaker.dto.request.VoucherUpdateRequest;
import com.sneaker.dto.request.VoucherValidateRequest;
import com.sneaker.entity.Account;
import com.sneaker.entity.Notification;
import com.sneaker.entity.Voucher;
import com.sneaker.repository.AccountRepository;
import com.sneaker.repository.NotificationRepository;
import com.sneaker.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoucherService {
    
    private final VoucherRepository voucherRepository;
    private final AccountRepository accountRepository;
    private final NotificationRepository notificationRepository;
    
    @Transactional
    public Voucher createVoucher(VoucherCreateRequest request) {
        // Check if code already exists
        if (voucherRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("Mã voucher đã tồn tại");
        }
        
        // Validate discount value
        if (request.getDiscountType() == Voucher.VoucherType.PERCENTAGE) {
            if (request.getDiscountValue().compareTo(BigDecimal.ZERO) <= 0 ||
                request.getDiscountValue().compareTo(new BigDecimal("100")) > 0) {
                throw new RuntimeException("Giá trị phần trăm phải từ 1 đến 100");
            }
        } else if (request.getDiscountType() == Voucher.VoucherType.FIXED_AMOUNT) {
            if (request.getDiscountValue().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Giá trị cố định phải lớn hơn 0");
            }
        }
        
        // Validate dates
        if (request.getStartDate().isAfter(request.getEndDate()) || 
            request.getStartDate().isEqual(request.getEndDate())) {
            throw new RuntimeException("Thời gian kết thúc phải sau thời gian bắt đầu");
        }
        
        Voucher voucher = new Voucher();
        voucher.setCode(request.getCode());
        voucher.setName(request.getName());
        voucher.setType(request.getDiscountType());
        voucher.setValue(request.getDiscountValue());
        voucher.setQuantity(request.getQuantity());
        voucher.setStartDate(request.getStartDate());
        voucher.setEndDate(request.getEndDate());
        voucher.setMinOrderValue(request.getMinOrderValue() != null ? request.getMinOrderValue() : BigDecimal.ZERO);
        voucher.setMaxDiscount(request.getMaxDiscount());
        voucher.setStatus(request.getStatus() != null ? request.getStatus() : Voucher.Status.ACTIVE);
        
        return voucherRepository.save(voucher);
    }
    
    public Page<Voucher> getVouchers(String code, String name, Voucher.Status status,
                                     LocalDateTime startDate, LocalDateTime endDate,
                                     Pageable pageable) {
        return voucherRepository.findWithFilters(code, name, status, startDate, endDate, pageable);
    }
    
    public Optional<Voucher> getVoucherById(Integer id) {
        return voucherRepository.findById(id);
    }
    
    @Transactional
    public Voucher updateVoucher(Integer id, VoucherUpdateRequest request) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu giảm giá"));
        
        // Validate dates if both provided
        if (request.getStartDate() != null && request.getEndDate() != null) {
            if (request.getStartDate().isAfter(request.getEndDate()) ||
                request.getStartDate().isEqual(request.getEndDate())) {
                throw new RuntimeException("Thời gian kết thúc phải sau thời gian bắt đầu");
            }
        }
        
        if (request.getName() != null) voucher.setName(request.getName());
        if (request.getQuantity() != null) voucher.setQuantity(request.getQuantity());
        if (request.getStartDate() != null) voucher.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) voucher.setEndDate(request.getEndDate());
        if (request.getMinOrderValue() != null) voucher.setMinOrderValue(request.getMinOrderValue());
        if (request.getMaxDiscount() != null) voucher.setMaxDiscount(request.getMaxDiscount());
        if (request.getStatus() != null) voucher.setStatus(request.getStatus());
        
        return voucherRepository.save(voucher);
    }
    
    @Transactional
    public void deleteVoucher(Integer id) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu giảm giá"));
        voucherRepository.delete(voucher);
    }
    
    public Map<String, Object> validateVoucher(VoucherValidateRequest request) {
        Voucher voucher = voucherRepository.findByCode(request.getCode())
                .orElseThrow(() -> new RuntimeException("Mã voucher không tồn tại"));
        
        // Check status
        if (voucher.getStatus() != Voucher.Status.ACTIVE) {
            throw new RuntimeException("Voucher không còn hoạt động");
        }
        
        // Check time
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(voucher.getStartDate())) {
            throw new RuntimeException("Voucher chưa có hiệu lực");
        }
        if (now.isAfter(voucher.getEndDate())) {
            throw new RuntimeException("Voucher đã hết hạn");
        }
        
        // Check quantity
        if (voucher.getUsedCount() >= voucher.getQuantity()) {
            throw new RuntimeException("Voucher đã hết lượt sử dụng");
        }
        
        // Check min order value
        if (request.getOrderValue() != null && voucher.getMinOrderValue() != null) {
            if (request.getOrderValue().compareTo(voucher.getMinOrderValue()) < 0) {
                throw new RuntimeException("Giá trị đơn hàng tối thiểu để sử dụng voucher này là " + 
                    voucher.getMinOrderValue() + "đ");
            }
        }
        
        // Calculate discount amount
        BigDecimal discountAmount = BigDecimal.ZERO;
        if (request.getOrderValue() != null) {
            if (voucher.getType() == Voucher.VoucherType.PERCENTAGE) {
                discountAmount = request.getOrderValue()
                    .multiply(voucher.getValue())
                    .divide(new BigDecimal("100"), 2, java.math.RoundingMode.HALF_UP);
                if (voucher.getMaxDiscount() != null && discountAmount.compareTo(voucher.getMaxDiscount()) > 0) {
                    discountAmount = voucher.getMaxDiscount();
                }
            } else if (voucher.getType() == Voucher.VoucherType.FIXED_AMOUNT) {
                discountAmount = voucher.getValue();
            }
        }
        
        return Map.of(
            "voucher", voucher,
            "discountAmount", discountAmount
        );
    }
    
    @Transactional
    public Voucher incrementVoucherUsage(Integer id) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu giảm giá"));
        
        if (voucher.getUsedCount() >= voucher.getQuantity()) {
            throw new RuntimeException("Voucher đã hết lượt sử dụng");
        }
        
        voucher.setUsedCount(voucher.getUsedCount() + 1);
        return voucherRepository.save(voucher);
    }
    
    @Transactional
    public Map<String, Object> notifyVoucher(Integer id, String title, String message) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu giảm giá"));
        
        // Get all customer accounts
        List<Account> customers = accountRepository.findAll().stream()
                .filter(account -> account.getRole() == Account.Role.CUSTOMER)
                .collect(Collectors.toList());
        
        // Create notifications
        List<Notification> notifications = new ArrayList<>();
        for (Account customer : customers) {
            Notification notification = new Notification();
            notification.setAccount(customer);
            notification.setTitle(title != null ? title : "Voucher mới: " + voucher.getName());
            notification.setMessage(message != null ? message : "Sử dụng mã " + voucher.getCode() + " để được giảm giá!");
            notification.setType(Notification.NotificationType.VOUCHER);
            notification.setIsRead(false);
            notifications.add(notification);
        }
        
        notificationRepository.saveAll(notifications);
        
        return Map.of(
            "voucherCode", voucher.getCode(),
            "notificationsSent", customers.size()
        );
    }
    
    public List<Map<String, Object>> getAvailableVouchersForUser(BigDecimal orderValue) {
        LocalDateTime now = LocalDateTime.now();
        Page<Voucher> vouchers = voucherRepository.findAvailableVouchers(now, PageRequest.of(0, 100));
        
        return vouchers.getContent().stream()
                .filter(voucher -> {
                    if (orderValue != null && voucher.getMinOrderValue() != null) {
                        return orderValue.compareTo(voucher.getMinOrderValue()) >= 0;
                    }
                    return true;
                })
                .map(voucher -> {
                    BigDecimal discountAmount = BigDecimal.ZERO;
                    if (orderValue != null) {
                        if (voucher.getType() == Voucher.VoucherType.PERCENTAGE) {
                            discountAmount = orderValue
                                .multiply(voucher.getValue())
                                .divide(new BigDecimal("100"), 2, java.math.RoundingMode.HALF_UP);
                            if (voucher.getMaxDiscount() != null && discountAmount.compareTo(voucher.getMaxDiscount()) > 0) {
                                discountAmount = voucher.getMaxDiscount();
                            }
                        } else if (voucher.getType() == Voucher.VoucherType.FIXED_AMOUNT) {
                            discountAmount = voucher.getValue();
                        }
                    }
                    
                    Map<String, Object> result = new java.util.HashMap<>();
                    result.put("id", voucher.getId());
                    result.put("code", voucher.getCode());
                    result.put("name", voucher.getName());
                    result.put("type", voucher.getType());
                    result.put("value", voucher.getValue());
                    result.put("quantity", voucher.getQuantity());
                    result.put("usedCount", voucher.getUsedCount());
                    result.put("startDate", voucher.getStartDate());
                    result.put("endDate", voucher.getEndDate());
                    result.put("minOrderValue", voucher.getMinOrderValue());
                    result.put("maxDiscount", voucher.getMaxDiscount());
                    result.put("status", voucher.getStatus());
                    result.put("discountAmount", discountAmount);
                    return result;
                })
                .collect(Collectors.toList());
    }
}

