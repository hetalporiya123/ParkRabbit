package com.parkrabbit.backend.messaging;


import com.parkrabbit.backend.dto.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class ParkingSessionProducer {


    private final RabbitTemplate rabbitTemplate;


    public ParkingSessionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendSessionStarted(ParkingSessionStartedEvent event) {
        rabbitTemplate.convertAndSend("parking.exchange", "session.started", event);
    }


    public void sendSessionReminder(ParkingSessionReminderEvent event) {
        rabbitTemplate.convertAndSend("parking.exchange", "session.reminder", event);
    }


    public void sendSessionEnded(ParkingSessionEndedEvent event) {
        rabbitTemplate.convertAndSend("parking.exchange", "session.ended", event);
    }
}