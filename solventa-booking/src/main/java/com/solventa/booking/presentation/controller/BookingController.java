package com.solventa.booking.presentation.controller;

import com.solventa.booking.presentation.dto.BookingDTO;
import com.solventa.booking.service.interfaces.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solventa/booking")
@RequiredArgsConstructor
public class BookingController {

    private final IBookingService service;

    @GetMapping
    public ResponseEntity<?> geAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BookingDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    /*@GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUserFromUserService(@PathVariable Long id) {

        return ResponseEntity.ok(service.getUserIsExist(id));
    }*/
}
