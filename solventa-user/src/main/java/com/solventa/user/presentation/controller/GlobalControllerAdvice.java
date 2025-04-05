package com.solventa.user.presentation.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.solventa.user.presentation.dto.ErrorResponseDTO;
import com.solventa.user.service.exception.ServerErrorException;
import com.solventa.user.service.exception.UserNotFoundException;
import com.solventa.user.service.exception.UserTimeOutException;
import com.solventa.user.util.ErrorCatalog;


@RestControllerAdvice
public class GlobalControllerAdvice {
	
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ExceptionHandler(UserNotFoundException.class)
	    public ErrorResponseDTO handleUserNotFoundException(UserNotFoundException ex) {
	        return ErrorResponseDTO.builder()
	                .code(ErrorCatalog.USER_NOT_FOUND.getCode())
	                .status(HttpStatus.NOT_FOUND)
	                .message(ErrorCatalog.USER_NOT_FOUND.getMessage())
	                .timeStamp(LocalDateTime.now())
	                .build();
	    }
	 
	 @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	    @ExceptionHandler(UserTimeOutException.class)
	    public ErrorResponseDTO handleUserTimeOutException(UserTimeOutException ex) {
	        return ErrorResponseDTO.builder()
	                .code(ErrorCatalog.USER_CONNECT_TIME_OUT.getCode())
	                .status(HttpStatus.REQUEST_TIMEOUT)
	                .message(ErrorCatalog.USER_CONNECT_TIME_OUT.getMessage())
	                .timeStamp(LocalDateTime.now())
	                .build();
	    }
	 
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    @ExceptionHandler(ServerErrorException.class)
	    public ErrorResponseDTO handleUserServerException(ServerErrorException ex) {
	        return ErrorResponseDTO.builder()
	                .code(ErrorCatalog.USER_SERVER_ERROR.getCode())
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .message(ErrorCatalog.USER_SERVER_ERROR.getMessage())
	                .timeStamp(LocalDateTime.now())
	                .build();
	    }
}
