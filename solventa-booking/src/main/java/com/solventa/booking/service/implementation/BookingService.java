package com.solventa.booking.service.implementation;

import com.solventa.booking.persistence.entity.BookingEntity;
import com.solventa.booking.persistence.repository.BookingRepository;
import com.solventa.booking.presentation.dto.BookingDTO;
import com.solventa.booking.presentation.dto.DeviceDTO;
import com.solventa.booking.presentation.dto.UserDTO;
import com.solventa.booking.service.client.DeviceClient;
import com.solventa.booking.service.client.UserClient;
import com.solventa.booking.service.http.response.UserResponseHttp;
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
    private final UserClient userClient;
    private final DeviceClient deviceClient;

    @Override
    public List<UserResponseHttp> getAll() {
        try {
            List<BookingEntity> bookings = (List<BookingEntity>) repository.findAll();

            return bookings.stream().map(booking -> {
                UserDTO user = userClient.getById(booking.getUserId());
                DeviceDTO device = deviceClient.getById(booking.getDeviceId());

                return UserResponseHttp.builder()
                        .startDate(String.valueOf(booking.getStartDate()))
                        .endDate(String.valueOf(booking.getEndDate()))
                        .status(booking.getStatus())
                        .user(user)
                        .device(device)
                        .build();
            }).collect(Collectors.toList());

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
    public UserResponseHttp getById(Long id) {
        try {
            BookingEntity booking = repository.findById(id).orElseThrow();
            UserDTO user = userClient.getById(booking.getUserId());
            DeviceDTO device = deviceClient.getById(booking.getDeviceId());

            return UserResponseHttp.builder()
                    .startDate(String.valueOf(booking.getStartDate()))
                    .endDate(String.valueOf(booking.getEndDate()))
                    .status(booking.getStatus())
                    .user(user)
                    .device(device)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookingDTO save(BookingDTO dto) {
        try {
            if(!validateFields(dto)) throw new Exception();

            if(dto.getStartDate() == null || dto.getEndDate() == null) {
                dto.calculateDates();
            }

            BookingEntity bookingSaved = repository.save(
                    BookingEntity.builder()
                            .startDate(dto.getStartDate())
                            .endDate(dto.getEndDate())
                            .userId(dto.getUserId())
                            .deviceId(dto.getDeviceId())
                            .duration(dto.getDuration())
                            .status("PENDING")
                            .checkSum(generateCheckSum(dto.getUserId(), dto.getDeviceId()))
                            .build()
            );

            return BookingDTO.convertToDto(bookingSaved);

        } catch (QueryTimeoutException e) {
            log.error("Error: La consulta tardó más de 30 segundos en responder", e);

        }catch (Exception e) {
            String error = "Error inesperado al obtener la lista de usuarios";
            log.error(error, e);
            throw new RuntimeException(error, e);
        }
        return null;
    }

    @Override
    public BookingDTO update(Long id, BookingDTO dto) {
        try {
            UserDTO user = userClient.getById(id);

            if(validateUserNotExists(user)) {
                throw new Exception();
            }

        } catch (QueryTimeoutException e) {
            log.error("Error: La consulta tardó más de 30 segundos en responder", e);

        }catch (Exception e) {
            String error = "Error inesperado al obtener la lista de usuarios";
            log.error(error, e);
            throw new RuntimeException(error, e);
        }


        return null;
    }

    /*@Override
    public UserDTO getUserIsExist(Long id) {
        // Consultar el microservicio de usuarios
        UserDTO user = userClient.getById(id);
        return user;
    }

    @Override
    public DeviceDTO getDeviceIsExist(Long id) {
        return null;
    }*/

    private String generateCheckSum(Long userId, Long deviceId) {
        return userId + "-" + deviceId + "-" + System.currentTimeMillis();
    }

    private boolean validateUserNotExists(UserDTO user) {
        return user != null && !"ACTIVE".equals(user.getStatus());
    }

    private boolean validateUserNotExists(DeviceDTO device) {
        return device != null && !"ACTIVE".equals(device.getTechnicalStatus());
    }

    private boolean validateFields(BookingDTO dto) {

        try {
            UserDTO user = userClient.getById(dto.getUserId());
            DeviceDTO device = deviceClient.getById(dto.getDeviceId());

            if(validateUserNotExists(user) || validateUserNotExists(device)) {
                throw new Exception("\"Parámetros inválidos: usuario o dispositivo no válido");
            }
            return true;
        } catch (QueryTimeoutException e) {
            log.error("Error: La consulta tardó demasiado tiempo en responder", e);
            throw new RuntimeException("El servicio no está disponible en este momento. Intente más tarde.", e);
        } catch (Exception e) {
            log.error("Error al validar los campos de la reserva", e);
            throw new RuntimeException("Ocurrió un error al validar los campos de la reserva", e);
        }
    }
}
