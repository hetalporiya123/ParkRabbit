package com.parkrabbit.backend.controller;


import com.parkrabbit.backend.service.ParkingSessionService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/parking-sessions")
public class ParkingSessionController {


    private final ParkingSessionService service;


    public ParkingSessionController(ParkingSessionService service) {
        this.service = service;
    }


    @PostMapping("/start/{reservationId}")
    public void start(@PathVariable Long reservationId) {
        service.startSession(reservationId);
    }
}