package com.solventa.device.service.implementation;

import com.solventa.device.persistence.entity.DeviceEntity;
import com.solventa.device.persistence.repository.DeviceRepository;
import com.solventa.device.presentation.dto.DeviceDTO;
import com.solventa.device.service.exception.DeviceNotFoundException;
import com.solventa.device.service.exception.DeviceTimeOutException;
import com.solventa.device.service.exception.ServerErrorException;
import com.solventa.device.service.interfaces.IDeviceService;
import com.solventa.device.utils.DeviceConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceServiceImpl implements IDeviceService {

	private final DeviceRepository repository;

	@Override
	public List<DeviceDTO> getAll() {
		try {
			List<DeviceEntity> entityList = (List<DeviceEntity>) repository.findAll();

			return entityList.stream().map(DeviceDTO::convertToDto).toList();

		} catch (QueryTimeoutException e) {
			log.error(DeviceConstants.TIME_OUT_ERROR, e);
			throw new DeviceTimeOutException();
			
		} catch (Exception e) {
			log.error(DeviceConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}

	@Override
	public DeviceDTO getById(Long id) {

		try {
			DeviceEntity entity = repository.findById(id).orElseThrow(DeviceNotFoundException::new);
			return DeviceDTO.convertToDto(entity);

		} catch (DeviceNotFoundException e) {
			log.error(DeviceConstants.DEVICE_NOT_FOUND, e);
			throw e;
			
		} catch (QueryTimeoutException e) {
			log.error(DeviceConstants.TIME_OUT_ERROR, e);
			throw new DeviceTimeOutException();
			
		} catch (Exception e) {
			log.error(DeviceConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}

	@Transactional
	@Override
	public DeviceDTO save(DeviceDTO dto) {

		try {
			DeviceEntity entity = repository.save(DeviceDTO.converToEntity(dto));
			return DeviceDTO.convertToDto(entity);

		} catch (QueryTimeoutException e) {
			log.error(DeviceConstants.TIME_OUT_ERROR, e);
			throw new DeviceTimeOutException();
			
		} catch (Exception e) {
			log.error(DeviceConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}

	}

	@Transactional
	@Override
	public DeviceDTO update(Long id, DeviceDTO dto) {
		try {
			DeviceEntity entity = repository.findById(id).orElseThrow(DeviceNotFoundException::new);
			entity.setMaker(dto.getMaker());
			entity.setSerial(dto.getSerial());
			entity.setType(dto.getType());
			entity.setTechnicalStatus(dto.getTechnicalStatus());
			entity.setDescription(dto.getDescription());

			DeviceEntity updateEntity = repository.save(entity);

			return DeviceDTO.convertToDto(updateEntity);

		} catch (DeviceNotFoundException e) {
			log.error(DeviceConstants.DEVICE_NOT_FOUND, e);
			throw e;
			
		} catch (QueryTimeoutException e) {
			log.error(DeviceConstants.TIME_OUT_ERROR, e);
			throw new DeviceTimeOutException();
			
		} catch (Exception e) {
			log.error(DeviceConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}

	@Override
	public void delete(Long id) {
		try {
			if (!repository.existsById(id)) {
				log.error(DeviceConstants.DEVICE_NOT_FOUND);
				throw new DeviceNotFoundException();
			}
			repository.deleteById(id);

		} catch (QueryTimeoutException e) {
			log.error(DeviceConstants.TIME_OUT_ERROR, e);
			throw new DeviceTimeOutException();
			
		} catch (Exception e) {
			log.error(DeviceConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}
}
