package com.solventa.booking.presentation.controller;

import com.solventa.booking.presentation.dto.BookingDTO;
import com.solventa.booking.service.exception.ServerErrorException;
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
    	
    	return ResponseEntity.status(201).body(service.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
    	
       return ResponseEntity.ok(service.getById(id));
    }
    
//    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BookingDTO dto) {
    	
    	return ResponseEntity.ok(service.update(id, dto));
 
    }
}
