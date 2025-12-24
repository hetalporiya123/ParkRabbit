package com.parkrabbit.backend.entity;
import java.time.LocalDateTime;
import com.parkrabbit.backend.entity.enums.ParkingSlotStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name= "parking_slot")
@Getter
@Setter
public class ParkingSlot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    // Many slots belong to one parkinglot
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "parking_lot_id", nullable = false)
    private ParkingLot parkingLot;

    @Column(nullable = false)
    private int slotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingSlotStatus status;

    @Column(nullable= false, updatable= false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = ParkingSlotStatus.FREE;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
    
}
