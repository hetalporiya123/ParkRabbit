package com.parkrabbit.backend.dto;

public class ParkingSessionEndingSoonEvent {

    private Long userId;
    private Long reservationId;
    private String message;

    public ParkingSessionEndingSoonEvent(
            Long userId,
            Long reservationId,
            String message
    ) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.message = message;
    }

    public Long getUserId() { return userId; }
    public Long getReservationId() { return reservationId; }
    public String getMessage() { return message; }
}
