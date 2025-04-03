package com.solventa.booking.service.interfaces;

import com.solventa.booking.presentation.dto.BookingDTO;
import com.solventa.booking.presentation.dto.DeviceDTO;
import com.solventa.booking.presentation.dto.UserDTO;
import com.solventa.booking.service.http.response.UserResponseHttp;

import java.util.List;

public interface IBookingService {
    List<UserResponseHttp> getAll();
    UserResponseHttp getById(Long id);
    BookingDTO save(BookingDTO dto);
    BookingDTO update(Long id, BookingDTO dto);

}
