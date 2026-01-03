package com.parkrabbit.backend.messaging;

import com.parkrabbit.backend.dto.*;
import com.parkrabbit.backend.websocket.NotificationWebSocketHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ParkingSessionConsumer {

    private final NotificationWebSocketHandler socketHandler;

    public ParkingSessionConsumer(NotificationWebSocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @RabbitListener(queues = "parking.session.started.queue")
    public void handleStarted(ParkingSessionStartedEvent event) {
        socketHandler.sendToUser(event.getUserId(), event);
    }



    @RabbitListener(queues = "parking.session.ending.queue")
    public void handleEndingSoon(ParkingSessionEndingSoonEvent event) {
        socketHandler.sendToUser(event.getUserId(), event);
    }
}
