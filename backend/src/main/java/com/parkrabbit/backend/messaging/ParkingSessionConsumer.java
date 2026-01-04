package com.parkrabbit.backend.messaging;

import com.parkrabbit.backend.dto.*;
import com.parkrabbit.backend.entity.UserNotification;
import com.parkrabbit.backend.repository.UserNotificationRepository;
import com.parkrabbit.backend.websocket.NotificationWebSocketHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParkingSessionConsumer {

    private final NotificationWebSocketHandler socketHandler;
    private final UserNotificationRepository notificationRepository;

    public ParkingSessionConsumer(
            NotificationWebSocketHandler socketHandler,
            UserNotificationRepository notificationRepository
    ) {
        this.socketHandler = socketHandler;
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = "parking.session.started.queue")
    public void handleStarted(ParkingSessionStartedEvent event) {

        UserNotification notification = new UserNotification();
        notification.setUserId(event.getUserId());
        notification.setType("SESSION_STARTED");
        notification.setMessage("Parking session started");
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        UserNotification saved = notificationRepository.save(notification);
        socketHandler.sendToUser(event.getUserId(), saved);
    }

    @RabbitListener(queues = "parking.session.ending.queue")
    public void handleEndingSoon(ParkingSessionEndingSoonEvent event) {

        UserNotification notification = new UserNotification();
        notification.setUserId(event.getUserId());
        notification.setType("SESSION_ENDING_SOON");
        notification.setMessage("Parking session ending soon");
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        UserNotification saved = notificationRepository.save(notification);
        socketHandler.sendToUser(event.getUserId(), saved);
    }

    @RabbitListener(queues = "parking.session.ended.queue")
    public void handleEnded(ParkingSessionEndedEvent event) {

        UserNotification notification = new UserNotification();
        notification.setUserId(event.getUserId());
        notification.setType("SESSION_ENDED");
        notification.setMessage("Parking session ended");
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        UserNotification saved = notificationRepository.save(notification);
        socketHandler.sendToUser(event.getUserId(), saved);
    }

   // 🔒 KEEPING THIS EXACTLY AS REQUESTED
@RabbitListener(queues = "user.notification.queue")
public void handleUserNotification(UserNotificationEvent event) {

    System.out.println(
            "🔔 NOTIFICATION [" + event.getType() + "] → User "
                    + event.getUserId() + " : " + event.getMessage()
    );

    // ✅ Persist notification
    UserNotification notification = new UserNotification();
    notification.setUserId(event.getUserId());
    notification.setType(event.getType());
    notification.setMessage(event.getMessage());
    notification.setRead(false);
    notification.setCreatedAt(LocalDateTime.now());

    UserNotification saved = notificationRepository.save(notification);

    // 🌐 WebSocket → frontend (send persisted version)
    socketHandler.sendToUser(event.getUserId(), saved);
}

}
