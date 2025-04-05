package com.solventa.device.presentation.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.solventa.device.presentation.dto.ErrorResponseDTO;
import com.solventa.device.service.exception.DeviceNotFoundException;
import com.solventa.device.service.exception.DeviceTimeOutException;
import com.solventa.device.service.exception.ServerErrorException;
import com.solventa.device.utils.DeviceUtils;
import com.solventa.device.utils.enums.ErrorCatalog;

@RestControllerAdvice
public class GeneralControllerAdvice {
	
	String formattedTimeStamp = DeviceUtils.formatLocalDateTime(LocalDateTime.now());
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DeviceNotFoundException.class)
	public ErrorResponseDTO handleDeviceNotFoundException(DeviceNotFoundException ex) {
		return ErrorResponseDTO.builder()
				.code(ErrorCatalog.DEVICE_NOT_FOUND.getCode())
				.status(HttpStatus.NOT_FOUND)
				.message(ErrorCatalog.DEVICE_NOT_FOUND.getMessage())
				.timeStamp(formattedTimeStamp)
				.build();
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ServerErrorException.class)
	public ErrorResponseDTO handleDeviceServerException(ServerErrorException ex) {
		return ErrorResponseDTO.builder()
				.code(ErrorCatalog.DEVICE_SERVER_ERROR.getCode())
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.message(ErrorCatalog.DEVICE_SERVER_ERROR.getMessage())
				.timeStamp(formattedTimeStamp)
				.build();
	}
	
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	@ExceptionHandler(DeviceTimeOutException.class)
	public ErrorResponseDTO handleDeviceTimeOutException(DeviceTimeOutException ex) {
		return ErrorResponseDTO.builder()
				.code(ErrorCatalog.DEVICE_CONNECT_TIME_OUT.getCode())
				.status(HttpStatus.REQUEST_TIMEOUT)
				.message(ErrorCatalog.DEVICE_CONNECT_TIME_OUT.getMessage())
				.timeStamp(formattedTimeStamp)
				.build();
	}
}
