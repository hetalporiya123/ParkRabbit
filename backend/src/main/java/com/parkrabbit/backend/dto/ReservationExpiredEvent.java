package com.parkrabbit.backend.dto;

import java.time.LocalDateTime;

public class ReservationExpiredEvent {

    private Long reservationId;
    private Long userId;
    private Long parkingLotId;
    private Long slotId;
    private LocalDateTime expiredAt;
    private String parkingLotName;
    private String parkingLotAddress;

    public ReservationExpiredEvent(
            Long reservationId,
            Long userId,
            Long parkingLotId,
            Long slotId,
            LocalDateTime expiredAt,
            String parkingLotName,
            String parkingLotAddress
    ) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.parkingLotId = parkingLotId;
        this.slotId = slotId;
        this.expiredAt = expiredAt;
        this.parkingLotName = parkingLotName;
        this.parkingLotAddress = parkingLotAddress;
    }

    // getters only (recommended for events)
    public Long getReservationId() { return reservationId; }
    public Long getUserId() { return userId; }
    public Long getParkingLotId() { return parkingLotId; }
    public Long getSlotId() { return slotId; }
    public LocalDateTime getExpiredAt() { return expiredAt; }
    public String getParkingLotName() { return parkingLotName; }
    public String getParkingLotAddress() { return parkingLotAddress; }
}
