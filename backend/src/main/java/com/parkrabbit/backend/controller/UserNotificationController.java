package com.parkrabbit.backend.controller;

import java.util.List;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkrabbit.backend.entity.User;
import com.parkrabbit.backend.entity.UserNotification;
import com.parkrabbit.backend.service.UserNotificationService;

@RestController
@RequestMapping("/api/notifications")
public class UserNotificationController {

    private final UserNotificationService service;

    public UserNotificationController(UserNotificationService service) {
        this.service = service;
    }

    // ✅ GET notifications for logged-in user
    @GetMapping
    public List<UserNotification> getMyNotifications(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return service.getUserNotifications(user.getId());
    }

    // ✅ PATCH mark as read
    @PatchMapping("/{id}/read")
    public void markAsRead(
            @PathVariable Long id,
            Authentication auth
    ) {
        User user = (User) auth.getPrincipal();
        service.markAsRead(id, user.getId());
    }
}
