package com.sneaker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true)
    private String code;
    
    @Column(nullable = false)
    private String fullName;
    
    private String phoneNumber;
    
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private LocalDateTime birthday;
    
    private Boolean gender;
    
    private String avatar;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.CUSTOMER;
    
    private String citizenId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountAddress> addresses;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> customerOrders;
    
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<Order> staffOrders;
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    public enum Role {
        CUSTOMER, ADMIN, STAFF
    }
    
    public enum AccountStatus {
        ACTIVE, INACTIVE
    }
}

