package com.studydocs.modules.system.repository;

import com.studydocs.modules.system.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, String> {
    List<NotificationEntity> findByUserIdAndIsDeletedFalseOrderByReceivedAtDesc(String userId);
    long countByUserIdAndIsReadFalseAndIsDeletedFalse(String userId);
}
