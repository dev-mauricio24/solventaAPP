package com.solventa.device.persistence.repository;

import com.solventa.device.persistence.entity.DeviceEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {
}
