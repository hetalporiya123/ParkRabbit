package com.parkrabbit.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parkrabbit.backend.entity.UserNotification;
import com.parkrabbit.backend.repository.UserNotificationRepository;

@Service
public class UserNotificationService {

    private final UserNotificationRepository repository;

    public UserNotificationService(UserNotificationRepository repository) {
        this.repository = repository;
    }

    public List<UserNotification> getUserNotifications(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public void markAsRead(Long notificationId, Long userId) {
        UserNotification notification = repository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        if (!notification.getUserId().equals(userId)) {
            throw new RuntimeException("Forbidden");
        }

        notification.setRead(true);
        repository.save(notification);
    }
}
