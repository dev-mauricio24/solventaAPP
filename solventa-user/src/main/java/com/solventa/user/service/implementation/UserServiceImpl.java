package com.solventa.user.service.implementation;

import com.solventa.user.persistence.entity.UserEntity;
import com.solventa.user.persistence.repository.UserRepository;
import com.solventa.user.presentation.dto.UserDTO;
import com.solventa.user.service.exception.ServerErrorException;
import com.solventa.user.service.exception.UserNotFoundException;
import com.solventa.user.service.exception.UserTimeOutException;
import com.solventa.user.service.interfaces.IUserService;
import com.solventa.user.util.constant.UserConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

	private final UserRepository repository;

	@Override
	public List<UserDTO> getAll() {
		try {

			List<UserEntity> entityList = (List<UserEntity>) repository.findAll();

			return entityList.stream().map(UserDTO::convertToDTO).collect(Collectors.toList());

		} catch (QueryTimeoutException e) {
			log.error(UserConstants.TIME_OUT_ERROR, e);
			throw new UserTimeOutException();
		} catch (Exception e) {
			log.error(UserConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}

	@Override
	public UserDTO getById(Long id) {
		try {
			
			UserEntity entity = repository.findById(id).orElseThrow(UserNotFoundException::new);
			return UserDTO.convertToDTO(entity);

		} catch (UserNotFoundException e) {
			log.error(UserConstants.USER_NOT_FOUND, e);
			throw e;
		} catch (QueryTimeoutException e) {
			log.error(UserConstants.TIME_OUT_ERROR, e);
			throw new UserTimeOutException();
		} catch (Exception e) {
			log.error(UserConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}

	@Transactional
	@Override
	public UserDTO save(UserDTO dto) {
		try {
			UserEntity savedUser = repository.save(UserDTO.converToEntity(dto));
			return UserDTO.convertToDTO(savedUser);

		} catch (QueryTimeoutException e) {
			log.error(UserConstants.TIME_OUT_ERROR, e);
			throw new UserTimeOutException();

		} catch (Exception e) {
			log.error(UserConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}

	@Transactional
	@Override
	public UserDTO update(Long id, UserDTO dto) {

		try {
			UserEntity userEntity = repository.findById(id).orElseThrow(UserNotFoundException::new);

			userEntity.setName(dto.getName());
			userEntity.setLastName(dto.getLastName());
			userEntity.setEmail(dto.getEmail());
			userEntity.setRole(dto.getRole());
			userEntity.setStatus(dto.getStatus());

			UserEntity updatedUser = repository.save(userEntity);

			return UserDTO.convertToDTO(updatedUser);

		} catch (UserNotFoundException e) {
			log.error(UserConstants.USER_NOT_FOUND, e);
			throw e;

		} catch (QueryTimeoutException e) {
			log.error(UserConstants.TIME_OUT_ERROR, e);
			throw new UserTimeOutException();

		} catch (Exception e) {
			log.error(UserConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		try {	
			if (!repository.existsById(id)) {
				log.error(UserConstants.USER_NOT_FOUND);
				throw new UserNotFoundException();
			}

			repository.deleteById(id);

		} catch (QueryTimeoutException e) {
			log.error(UserConstants.TIME_OUT_ERROR, e);
			throw new UserTimeOutException();

		} catch (Exception e) {
			log.error(UserConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}
}
