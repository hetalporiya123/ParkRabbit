package com.parkrabbit.backend.messaging;

import com.parkrabbit.backend.config.RabbitMQConfig;
import com.parkrabbit.backend.dto.ReservationExpiredEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservationExpiredProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReservationExpiredProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendExpiredReservationEvent(ReservationExpiredEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.EXPIRED_ROUTING_KEY,
                event
        );

        System.out.println(
                "📤 Published ReservationExpiredEvent for reservationId=" +
                        event.getReservationId()
        );
    }
}
