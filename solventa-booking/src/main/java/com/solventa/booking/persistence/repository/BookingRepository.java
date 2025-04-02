package com.solventa.booking.persistence.repository;

import com.solventa.booking.persistence.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {
}
