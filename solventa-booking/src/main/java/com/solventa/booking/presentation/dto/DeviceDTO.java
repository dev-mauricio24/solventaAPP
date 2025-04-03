package com.solventa.booking.presentation.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {
    private Long id;
    private String maker;
    private String serial;
    private String type;
    private String technicalStatus;
    private String description;
}
