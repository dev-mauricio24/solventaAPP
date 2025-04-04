package com.solventa.booking.service.implementation;

import com.solventa.booking.persistence.entity.BookingEntity;
import com.solventa.booking.persistence.repository.BookingRepository;
import com.solventa.booking.presentation.dto.BookingDTO;
import com.solventa.booking.presentation.dto.DeviceDTO;
import com.solventa.booking.presentation.dto.UserDTO;
import com.solventa.booking.service.client.DeviceClient;
import com.solventa.booking.service.client.UserClient;
import com.solventa.booking.service.exception.BookingNotFoundException;
import com.solventa.booking.service.exception.BookingTimeoutException;
import com.solventa.booking.service.exception.ServerErrorException;
import com.solventa.booking.service.http.response.UserResponseHttp;
import com.solventa.booking.service.interfaces.IBookingService;
import com.solventa.booking.util.constants.BookingConstants;
import com.solventa.booking.util.constants.BookingStatusEnum;

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

				return UserResponseHttp.builder().startDate(String.valueOf(booking.getStartDate()))
						.endDate(String.valueOf(booking.getEndDate())).status(booking.getStatus()).user(user)
						.device(device).build();
			}).collect(Collectors.toList());

		} catch (QueryTimeoutException e) {
			log.error(BookingConstants.TIME_OUT_ERROR, e);
			throw new BookingTimeoutException(e.getMessage());
		} catch (Exception e) {
			log.error(BookingConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}

	@Override
	public UserResponseHttp getById(Long id) {
		try {
			BookingEntity booking = repository.findById(id).orElseThrow(BookingNotFoundException::new);
			UserDTO user = userClient.getById(booking.getUserId());
			DeviceDTO device = deviceClient.getById(booking.getDeviceId());

			return UserResponseHttp.builder().startDate(String.valueOf(booking.getStartDate()))
					.endDate(String.valueOf(booking.getEndDate())).status(booking.getStatus()).user(user).device(device)
					.build();

		} catch (QueryTimeoutException e) {
			log.error(BookingConstants.TIME_OUT_ERROR, e);
			throw new BookingTimeoutException(e.getMessage());

		} catch (Exception e) {
			log.error(BookingConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}
	}

	@Override
	public BookingDTO save(BookingDTO dto) {
		try {

			validateFields(dto);

			if (dto.getStartDate() == null || dto.getEndDate() == null) {
				dto.calculateDates();
			}

			if (repository.existsByUserIdAndDeviceIdAndStatusIn(dto.getUserId(), dto.getDeviceId(),
					List.of(BookingStatusEnum.PENDING, BookingStatusEnum.APPROVED))) {
				throw new IllegalArgumentException(BookingConstants.BOOKING_EXISTING_ERROR);
			}

			BookingEntity bookingSaved = repository
					.save(BookingEntity.builder().startDate(dto.getStartDate()).endDate(dto.getEndDate())
							.userId(dto.getUserId()).deviceId(dto.getDeviceId()).duration(dto.getDuration())
							.checkSum(generateCheckSum(dto.getUserId(), dto.getDeviceId())).build());

			return BookingDTO.convertToDto(bookingSaved);

		} catch (QueryTimeoutException e) {
			log.error(BookingConstants.TIME_OUT_ERROR, e);
			throw new BookingTimeoutException(e.getMessage());
		} catch (Exception e) {
			log.error(BookingConstants.BOOKING_EXISTING_ERROR, e);
			throw new ServerErrorException();
		}

	}

	@Override
	public BookingDTO update(Long id, BookingDTO dto) {

		try {
			BookingEntity booking = repository.findById(id).orElseThrow(BookingNotFoundException::new);

			validateFields(dto);

			//booking.setStartDate(dto.getStartDate());
			//booking.setEndDate(dto.getEndDate());
			booking.setUserId(dto.getUserId());
			booking.setDeviceId(dto.getDeviceId());
			booking.setDuration(dto.getDuration());
			booking.setStatus(dto.getStatus().name());

			BookingEntity updatedBooking = repository.save(booking);

			return BookingDTO.convertToDto(updatedBooking);

		} catch (QueryTimeoutException e) {
			log.error(BookingConstants.TIME_OUT_ERROR, e);
			throw new BookingTimeoutException(e.getMessage());
		} catch (BookingNotFoundException e) {
			log.error(BookingConstants.BOOKING_NOT_FOUND, e);
			throw e;
		} catch (Exception e) {
			log.error(BookingConstants.SERVER_ERROR, e);
			throw new ServerErrorException();
		}

	}

	private String generateCheckSum(Long userId, Long deviceId) {
		String data = userId + "-" + deviceId + "-" + System.currentTimeMillis();
		return Integer.toHexString(data.hashCode());
	}

	private boolean validateUserNotExists(UserDTO user) {
		return user != null && !BookingConstants.ACTIVE_STATUS.equals(user.getStatus());
	}

	private boolean validateDeviceNotExists(DeviceDTO device) {
		return device != null && !BookingConstants.ACTIVE_STATUS.equals(device.getTechnicalStatus());
	}

	private void validateFields(BookingDTO dto) {

		try {
			UserDTO user = userClient.getById(dto.getUserId());
			DeviceDTO device = deviceClient.getById(dto.getDeviceId());

			if (validateUserNotExists(user)) {
				throw new IllegalArgumentException(BookingConstants.INVALID_USER);
			}

			if (validateDeviceNotExists(device)) {
				throw new IllegalArgumentException(BookingConstants.INVALID_DEVICE);
			}
		} catch (QueryTimeoutException e) {
			log.error(BookingConstants.TIME_OUT_ERROR, e);
			throw new RuntimeException(BookingConstants.SERVER_ERROR, e);
		} catch (Exception e) {
			log.error(BookingConstants.BOOKING_ERROR_FIELDS, e);
			throw new RuntimeException(BookingConstants.GENERAL_ERROR, e);
		}
	}
}
