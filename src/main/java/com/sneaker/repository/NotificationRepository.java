package com.sneaker.repository;

import com.sneaker.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    Page<Notification> findByAccountId(Integer accountId, Pageable pageable);
    Page<Notification> findByAccountIdAndIsRead(Integer accountId, Boolean isRead, Pageable pageable);
    long countByAccountIdAndIsRead(Integer accountId, Boolean isRead);
    
    @Query("SELECT n FROM Notification n WHERE " +
           "(:type IS NULL OR n.type = :type) AND " +
           "(:accountId IS NULL OR n.account.id = :accountId) AND " +
           "(:isRead IS NULL OR n.isRead = :isRead)")
    Page<Notification> findWithFilters(
        @Param("type") Notification.NotificationType type,
        @Param("accountId") Integer accountId,
        @Param("isRead") Boolean isRead,
        Pageable pageable
    );
}

