package com.solventa.booking.service.http.response;

import com.solventa.booking.presentation.dto.DeviceDTO;
import com.solventa.booking.presentation.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseHttp {
    private String startDate;
    private String endDate;
    private String status;
    private UserDTO user;
    private DeviceDTO device;

}
