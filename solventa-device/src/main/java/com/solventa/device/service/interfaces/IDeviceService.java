package com.solventa.device.service.interfaces;

import com.solventa.device.persistence.entity.DeviceEntity;
import com.solventa.device.presentation.dto.DeviceDTO;

import java.util.List;

public interface IDeviceService {

    List<DeviceDTO> getAll();
    DeviceDTO getById(Long id);
    DeviceDTO save(DeviceDTO deviceDto);
    DeviceDTO update(Long id, DeviceDTO deviceDto);
    void delete(Long id);

}
