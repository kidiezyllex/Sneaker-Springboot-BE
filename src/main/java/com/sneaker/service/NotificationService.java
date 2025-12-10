package com.sneaker.service;

import com.sneaker.dto.request.NotificationCreateRequest;
import com.sneaker.dto.request.NotificationUpdateRequest;
import com.sneaker.entity.Account;
import com.sneaker.entity.Notification;
import com.sneaker.repository.AccountRepository;
import com.sneaker.repository.NotificationRepository;
import com.sneaker.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    
    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;
    
    @Transactional
    public Notification createNotification(NotificationCreateRequest request) {
        Notification notification = new Notification();
        notification.setType(Notification.NotificationType.valueOf(request.getType()));
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setIsRead(false);
        
        if (request.getAccountId() != null) {
            Account account = accountRepository.findById(request.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
            notification.setAccount(account);
        }
        
        return notificationRepository.save(notification);
    }
    
    public Page<Notification> getNotifications(Notification.NotificationType type, 
                                               Integer accountId, Boolean isRead, 
                                               Pageable pageable) {
        return notificationRepository.findWithFilters(type, accountId, isRead, pageable);
    }
    
    public Notification getNotificationById(Integer id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông báo"));
    }
    
    @Transactional
    public Notification updateNotification(Integer id, NotificationUpdateRequest request) {
        Notification notification = getNotificationById(id);
        if (request.getTitle() != null) notification.setTitle(request.getTitle());
        if (request.getMessage() != null) notification.setMessage(request.getMessage());
        if (request.getIsRead() != null) notification.setIsRead(request.getIsRead());
        return notificationRepository.save(notification);
    }
    
    @Transactional
    public void deleteNotification(Integer id) {
        Notification notification = getNotificationById(id);
        notificationRepository.delete(notification);
    }
    
    public Page<Notification> getUserNotifications(Boolean isRead, Pageable pageable) {
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer accountId = userDetails.getAccount().getId();
        
        if (isRead != null) {
            return notificationRepository.findByAccountIdAndIsRead(accountId, isRead, pageable);
        }
        return notificationRepository.findByAccountId(accountId, pageable);
    }
    
    @Transactional
    public void sendNotificationToAllCustomers(NotificationCreateRequest request) {
        List<Account> customers = accountRepository.findAll()
                .stream()
                .filter(acc -> acc.getRole() == Account.Role.CUSTOMER)
                .toList();
        
        for (Account customer : customers) {
            Notification notification = new Notification();
            notification.setType(Notification.NotificationType.valueOf(request.getType()));
            notification.setTitle(request.getTitle());
            notification.setMessage(request.getMessage());
            notification.setAccount(customer);
            notification.setIsRead(false);
            notificationRepository.save(notification);
        }
    }
    
    @Transactional
    public Notification markAsRead(Integer id) {
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer accountId = userDetails.getAccount().getId();
        
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông báo"));
        
        if (notification.getAccount() == null || 
            !notification.getAccount().getId().equals(accountId)) {
            throw new RuntimeException("Không có quyền truy cập thông báo này");
        }
        
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }
    
    @Transactional
    public void markAllAsRead() {
        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer accountId = userDetails.getAccount().getId();
        
        List<Notification> notifications = notificationRepository
                .findByAccountIdAndIsRead(accountId, false, Pageable.unpaged())
                .getContent();
        
        notifications.forEach(n -> n.setIsRead(true));
        notificationRepository.saveAll(notifications);
    }
}

