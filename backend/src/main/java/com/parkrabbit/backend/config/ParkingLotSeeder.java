package com.parkrabbit.backend.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkrabbit.backend.entity.ParkingLot;
import com.parkrabbit.backend.repository.ParkingLotRepository;

@Component
@Profile("dev")
@org.springframework.core.annotation.Order(1)
public class ParkingLotSeeder implements CommandLineRunner {

    private final ParkingLotRepository parkingLotRepository;
    private final ObjectMapper objectMapper;

    public ParkingLotSeeder(
        ParkingLotRepository parkingLotRepository,
        ObjectMapper objectMapper
    ) {
        this.parkingLotRepository = parkingLotRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {


        if (parkingLotRepository.count() > 0) {
            return;
        }

        InputStream inputStream =
            new ClassPathResource("data/parkingLots.json").getInputStream();

        List<ParkingLot> lots =
            objectMapper.readValue(inputStream, new TypeReference<>() {});

        parkingLotRepository.saveAll(lots);

    }
}
