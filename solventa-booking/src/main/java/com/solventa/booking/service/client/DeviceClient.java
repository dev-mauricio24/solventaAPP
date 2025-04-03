package com.solventa.booking.service.client;

import com.solventa.booking.presentation.dto.DeviceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "solventa-device", url = "http://localhost:8002/api/solventa/device")
public interface DeviceClient {

    @GetMapping("/{id}")
    DeviceDTO getById(@PathVariable Long id);
}
