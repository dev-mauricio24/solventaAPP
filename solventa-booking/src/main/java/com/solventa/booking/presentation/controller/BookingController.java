package com.solventa.booking.presentation.controller;

import com.solventa.booking.service.interfaces.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solventa/booking")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService service;

    @GetMapping
    public ResponseEntity<?> geAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
