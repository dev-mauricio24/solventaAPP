package com.solventa.booking.presentation.dto;

import com.solventa.booking.persistence.entity.BookingEntity;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private int duration;
    private Long userId;
    private Long deviceId;
    private String status;
    private String checkSum;

    public BookingDTO(Long userId, Long deviceId, int days) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.duration = days;
        calculateDates(); // Calcula fechas automáticamente al crear la instancia
    }

    public void calculateDates() {
        this.startDate = new Date(); // Fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.startDate);
        calendar.add(Calendar.DAY_OF_MONTH, this.duration);
        this.endDate = calendar.getTime(); // Calcula fecha de finalización
    }

    public void setDays(int days) {
        this.duration = days;
        calculateDates(); // Si se cambia `days`, recalcular fechas
    }

    public static BookingEntity convertToEntity(BookingDTO dto) {
        return BookingEntity.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .userId(dto.getUserId())
                .deviceId(dto.getDeviceId())
                .status(dto.getStatus())
                .checkSum(dto.getCheckSum())
                .build();
    }

    public static BookingDTO convertToDto(BookingEntity entity) {
        return BookingDTO.builder()
                .id(entity.getId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .userId(entity.getUserId())
                .deviceId(entity.getDeviceId())
                .status(entity.getStatus())
                .checkSum(entity.getCheckSum())
                .build();
    }

}
