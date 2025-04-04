package com.solventa.booking.presentation.controller;

import com.solventa.booking.presentation.dto.ErrorResponseDTO;
import com.solventa.booking.service.exception.BookingNotFoundException;
import com.solventa.booking.service.exception.BookingTimeoutException;
import com.solventa.booking.service.exception.ServerErrorException;
import com.solventa.booking.util.ErrorCatalog;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GeneralControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookingNotFoundException.class)
    public ErrorResponseDTO handleBookingNotFoundException(BookingNotFoundException ex) {
        return ErrorResponseDTO.builder()
                .code(ErrorCatalog.BOOKING_NOT_FOUND.getCode())
                .status(HttpStatus.NOT_FOUND)
                .message(ErrorCatalog.BOOKING_NOT_FOUND.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(BookingTimeoutException.class)
    public ErrorResponseDTO handleBookingTimeOutException(BookingTimeoutException ex) {
        return ErrorResponseDTO.builder()
                .code(ErrorCatalog.BOOKING_CONNECT_TIME_OUT.getCode())
                .status(HttpStatus.REQUEST_TIMEOUT)
                .message(ErrorCatalog.BOOKING_CONNECT_TIME_OUT.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerErrorException.class)
    public ErrorResponseDTO handleBookingServerException(ServerErrorException ex) {
        return ErrorResponseDTO.builder()
                .code(ErrorCatalog.BOOKING_SERVER_ERROR.getCode())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ErrorCatalog.BOOKING_SERVER_ERROR.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
