package com.solventa.booking.service.interfaces;

import com.solventa.booking.presentation.dto.BookingDTO;

import java.util.List;

public interface IBookingService {
    List<BookingDTO> getAll();
    BookingDTO getById(Long id);
    BookingDTO save(BookingDTO dto);
    BookingDTO update(Long id, BookingDTO dto);
}
