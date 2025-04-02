package com.solventa.device.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="DEVICE")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maker;
    private String serial;
    private String type;
    @Column(name="technical_status")
    private String technicalStatus;
    private String description;
}
