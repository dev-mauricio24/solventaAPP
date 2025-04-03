package com.solventa.booking.service.client;

import com.solventa.booking.presentation.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "solventa-user", url = "http://localhost:8001/api/solventa/user")
public interface UserClient {

    @GetMapping("/{id}")
    UserDTO getById(@PathVariable Long id);
}
