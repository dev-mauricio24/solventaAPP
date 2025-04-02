package com.solventa.device.presentation.dto;

import com.solventa.device.persistence.entity.DeviceEntity;
import jakarta.persistence.Column;
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

    public static DeviceEntity converToEntity(DeviceDTO dto) {
        return DeviceEntity.builder()
//                .id(dto.getId())
                .maker(dto.getMaker())
                .serial(dto.getSerial())
                .type(dto.getType())
                .technicalStatus(dto.getTechnicalStatus())
                .description(dto.getDescription())
                .build();
    }

    public static DeviceDTO convertToDto(DeviceEntity entity) {
        return DeviceDTO.builder()
                .id(entity.getId())
                .maker(entity.getMaker())
                .serial(entity.getSerial())
                .type(entity.getType())
                .technicalStatus(entity.getTechnicalStatus())
                .description(entity.getDescription())
                .build();
    }
}
