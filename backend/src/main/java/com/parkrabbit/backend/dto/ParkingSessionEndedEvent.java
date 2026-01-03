package com.parkrabbit.backend.dto;


import java.time.LocalDateTime;


public record ParkingSessionEndedEvent(
        Long userId,
        Long sessionId,
        LocalDateTime endedAt
) {
    @Override
    public Long userId() {
        return userId;
    }

    @Override
    public Long sessionId() {
        return sessionId;
    }

    @Override
    public LocalDateTime endedAt() {
        return endedAt;
    }
}