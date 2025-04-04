package com.solventa.booking.util;

import com.solventa.booking.util.constants.BookingConstants;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    BOOKING_NOT_FOUND("ERR_BOOKING_001", BookingConstants.BOOKING_NOT_FOUND),
    BOOKING_CONNECT_TIME_OUT("ERR_BOOKING_002", BookingConstants.TIME_OUT_ERROR),
    BOOKING_SERVER_ERROR("ERR_BOOKING_003", BookingConstants.SERVER_ERROR);

    private final String code;
    private final String message;


    private ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
