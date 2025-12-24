package com.parkrabbit.backend.mapper;

import org.springframework.stereotype.Component;

import com.parkrabbit.backend.dto.ParkingLotCreateRequest;
import com.parkrabbit.backend.dto.ParkingLotResponse;
import com.parkrabbit.backend.entity.ParkingLot;

@Component
public class ParkingLotMapper {

    public ParkingLot toEntity(ParkingLotCreateRequest dto) {
        ParkingLot entity = new ParkingLot();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setHourlyRate(dto.getHourlyRate());
        entity.setTotalSlots(dto.getTotalSlots());
        return entity;
    }

    public ParkingLotResponse toResponse(ParkingLot entity) {
        ParkingLotResponse response = new ParkingLotResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setAddress(entity.getAddress());
        response.setLatitude(entity.getLatitude());
        response.setLongitude(entity.getLongitude());
        response.setHourlyRate(entity.getHourlyRate());
        response.setTotalSlots(entity.getTotalSlots());
        return response;
    }
}
