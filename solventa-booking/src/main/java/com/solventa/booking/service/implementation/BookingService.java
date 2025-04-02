package com.solventa.booking.service.implementation;

import com.solventa.booking.persistence.entity.BookingEntity;
import com.solventa.booking.persistence.repository.BookingRepository;
import com.solventa.booking.presentation.dto.BookingDTO;
import com.solventa.booking.service.interfaces.IBookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService implements IBookingService {
    private final BookingRepository repository;

    @Override
    public List<BookingDTO> getAll() {
        try {
            List<BookingEntity> entityList = (List<BookingEntity>) repository.findAll();

            return entityList.stream()
                    .map(BookingDTO::convertToDto)
                    .collect(Collectors.toList());

        } catch (QueryTimeoutException e) {
            log.error("Error: La consulta tardó más de 30 segundos en responder", e);
            return Collections.emptyList();
        }catch (Exception e) {
            String error = "Error inesperado al obtener la lista de usuarios";
            log.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

    @Override
    public BookingDTO getById(Long id) {
        return null;
    }

    @Override
    public BookingDTO save(BookingDTO dto) {
        return null;
    }

    @Override
    public BookingDTO update(Long id, BookingDTO dto) {
        return null;
    }
}
