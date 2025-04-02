package com.solventa.device.service.implementation;

import com.solventa.device.persistence.entity.DeviceEntity;
import com.solventa.device.persistence.repository.DeviceRepository;
import com.solventa.device.presentation.dto.DeviceDTO;
import com.solventa.device.service.interfaces.IDeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceServiceImpl implements IDeviceService {

    private final DeviceRepository repository;


    @Override
    public List<DeviceDTO> getAll() {
        log.info("Inicia la solicitud a base de datos para obtener los equipos disponibles");
        try {
            List<DeviceEntity> entityList = (List<DeviceEntity>) repository.findAll();

            log.info("Resultado :: " +  entityList.toString());

            return entityList.stream()
                    .map(DeviceDTO::convertToDto)
                    .toList();

        } catch (QueryTimeoutException e) {
            log.error("Conexión con base de datos excedida :: ", e);
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Ocurrió un error inesperado:: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public DeviceDTO getById(Long id) {

        try {
            DeviceEntity entity = repository.findById(id).orElseThrow();
            return DeviceDTO.convertToDto(entity);

        } catch (QueryTimeoutException e) {
            log.error("Conexión con base de datos excedida :: ", e);
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Ocurrió un error inesperado:: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public DeviceDTO save(DeviceDTO dto) {

        try {
            DeviceEntity entity = repository.save(DeviceDTO.converToEntity(dto));
            return DeviceDTO.convertToDto(entity);

        } catch (QueryTimeoutException e) {
            log.error("Conexión con base de datos excedida :: ", e);
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Ocurrió un error inesperado:: ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public DeviceDTO update(Long id, DeviceDTO dto) {
        try {
            DeviceEntity entity = repository.findById(id).orElseThrow();
            entity.setMaker(dto.getMaker());
            entity.setSerial(dto.getSerial());
            entity.setType(dto.getType());
            entity.setTechnicalStatus(dto.getTechnicalStatus());
            entity.setDescription(dto.getDescription());

            DeviceEntity updateEntity = repository.save(entity);

            return DeviceDTO.convertToDto(updateEntity);

        } catch (QueryTimeoutException e) {
            log.error("Conexión con base de datos excedida :: ", e);
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Ocurrió un error inesperado:: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try{
            if (!repository.existsById(id)) {
                log.error("Error: device not found");
                throw new Exception();
            }
            repository.deleteById(id);

        } catch (QueryTimeoutException e) {
            log.error("Conexión con base de datos excedida :: ", e);
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Ocurrió un error inesperado:: ", e);
            throw new RuntimeException(e);
        }
    }
}
