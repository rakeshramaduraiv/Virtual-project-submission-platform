package com.examly.springapp.service;

import com.examly.springapp.model.Notification;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(String title, String message, User user) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setUser(user);
        notification.setType(Notification.NotificationType.SYSTEM_ANNOUNCEMENT);
        
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUser(User user) {
        return notificationRepository.findByUserAndIsActiveTrueOrderByCreatedAtDesc(user);
    }

    public List<Notification> getUnreadNotificationsByUser(User user) {
        return notificationRepository.findByUserAndIsReadFalseAndIsActiveTrueOrderByCreatedAtDesc(user);
    }

    public Notification markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        return notificationRepository.save(notification);
    }
}