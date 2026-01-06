package com.parkrabbit.backend.dto;

import java.time.LocalDateTime;

public class ParkingSessionStartedEvent {

    private Long userId;
    private Long reservationId;
    private Long parkingLotId;
    private Long slotId;
    private String parkingLotName;
    private String parkingLotAddress;
    private LocalDateTime startedAt;
    private LocalDateTime endsAt;

    public ParkingSessionStartedEvent(
            Long userId,
            Long reservationId,
            Long parkingLotId,
            Long slotId,
            String parkingLotName,
            String parkingLotAddress,
            LocalDateTime startedAt,
            LocalDateTime endsAt
    ) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.parkingLotId = parkingLotId;
        this.slotId = slotId;
        this.parkingLotName = parkingLotName;
        this.parkingLotAddress = parkingLotAddress;
        this.startedAt = startedAt;
        this.endsAt = endsAt;
    }

    public Long getUserId() { return userId; }
    public Long getReservationId() { return reservationId; }
    public Long getParkingLotId() { return parkingLotId; }
    public Long getSlotId() { return slotId; }
    public String getParkingLotName() { return parkingLotName; }
    public String getParkingLotAddress() { return parkingLotAddress; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public LocalDateTime getEndsAt() { return endsAt; }
}
