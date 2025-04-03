package com.solventa.booking.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "BOOKING")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    private int duration;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "device_id")
    private Long deviceId;
    private String status;
    private String checkSum;
}
