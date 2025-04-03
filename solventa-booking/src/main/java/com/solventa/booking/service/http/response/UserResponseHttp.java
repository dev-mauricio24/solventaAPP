package com.solventa.booking.service.http.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseHttp {
    private String name;
    private String status;
}
