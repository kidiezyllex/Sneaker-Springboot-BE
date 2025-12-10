package com.sneaker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Voucher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoucherType type;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private Integer usedCount = 0;
    
    @Column(nullable = false)
    private LocalDateTime startDate;
    
    @Column(nullable = false)
    private LocalDateTime endDate;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal minOrderValue = BigDecimal.ZERO;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal maxDiscount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;
    
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL)
    private List<Order> orders;
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    public enum VoucherType {
        PERCENTAGE, FIXED_AMOUNT
    }
    
    public enum Status {
        ACTIVE, INACTIVE
    }
}

