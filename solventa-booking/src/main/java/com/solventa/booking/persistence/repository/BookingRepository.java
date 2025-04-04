package com.solventa.booking.persistence.repository;

import com.solventa.booking.persistence.entity.BookingEntity;
import com.solventa.booking.util.constants.BookingStatusEnum;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {
	
	 boolean existsByUserIdAndDeviceIdAndStatusIn(Long userId, Long deviceId, List<BookingStatusEnum> statuses);
}
