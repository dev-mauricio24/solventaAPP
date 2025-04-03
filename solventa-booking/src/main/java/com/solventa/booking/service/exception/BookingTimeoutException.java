package com.solventa.booking.service.exception;

import org.springframework.dao.QueryTimeoutException;

public class BookingTimeoutException extends RuntimeException {
    public BookingTimeoutException(String msg) {
        super(msg);
    }
}
