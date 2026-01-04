package com.parkrabbit.backend.messaging;

import com.parkrabbit.backend.config.RabbitMQConfig;
import com.parkrabbit.backend.dto.ReservationExpiredEvent;
import com.parkrabbit.backend.entity.UserNotification;
import com.parkrabbit.backend.repository.UserNotificationRepository;
import com.parkrabbit.backend.websocket.NotificationWebSocketHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationExpiredConsumer {

    private final NotificationWebSocketHandler socketHandler;
    private final UserNotificationRepository notificationRepository;

    public ReservationExpiredConsumer(
            NotificationWebSocketHandler socketHandler,
            UserNotificationRepository notificationRepository
    ) {
        this.socketHandler = socketHandler;
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.EXPIRED_QUEUE)
    public void handleReservationExpired(ReservationExpiredEvent event) {

        // 1️⃣ Persist notification to DB
        UserNotification notification = new UserNotification();
        notification.setUserId(event.getUserId());
        notification.setType("RESERVATION_EXPIRED");
        notification.setMessage(
                "Your reservation at " + event.getParkingLotName() + " has expired"
        );
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        UserNotification savedNotification =
                notificationRepository.save(notification);

        // 2️⃣ Push persisted notification via WebSocket
        socketHandler.sendToUser(
                event.getUserId(),
                savedNotification
        );
    }
}
