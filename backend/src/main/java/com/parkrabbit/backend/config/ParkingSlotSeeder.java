package com.parkrabbit.backend.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.parkrabbit.backend.entity.ParkingLot;
import com.parkrabbit.backend.entity.ParkingSlot;
import com.parkrabbit.backend.repository.ParkingLotRepository;
import com.parkrabbit.backend.repository.ParkingSlotRepository;

@Profile("dev") // IMPORTANT: only run in dev
@Order(2)
@Component
public class ParkingSlotSeeder implements CommandLineRunner {

    private final ParkingLotRepository parkingLotRepository;
    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingSlotSeeder(
        ParkingLotRepository parkingLotRepository,
        ParkingSlotRepository parkingSlotRepository
    ) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Override
    public void run(String... args) {

        // Condition: If slots already exist, do nothing
        if (parkingSlotRepository.count() > 0) {
            return;
        }

        List<ParkingSlot> slotsToSave = new ArrayList<>();

        List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        for (ParkingLot lot : parkingLots) {

            for (int i = 1; i <= lot.getTotalSlots(); i++) {

                ParkingSlot slot = new ParkingSlot();
                slot.setParkingLot(lot);
                slot.setSlotNumber(i);
                // status will be set to FREE via @PrePersist
                slotsToSave.add(slot);
            }
        }

        parkingSlotRepository.saveAll(slotsToSave);
    }
}
