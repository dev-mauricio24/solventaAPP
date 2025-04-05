package com.solventa.device.presentation.controller;

import com.solventa.device.presentation.dto.DeviceDTO;
import com.solventa.device.service.interfaces.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solventa/device")
@RequiredArgsConstructor
public class DeviceController {

    private final IDeviceService service;

    @GetMapping
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody DeviceDTO dto) {

        return ResponseEntity.status(201).body(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DeviceDTO dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
