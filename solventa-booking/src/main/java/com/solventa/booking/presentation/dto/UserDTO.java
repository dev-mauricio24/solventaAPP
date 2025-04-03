package com.solventa.booking.presentation.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private String name;
    private String lastName; // last_name
    private String email;
    private String password;
    private String role;
    private String status;
}
