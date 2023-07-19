package com.restaurant.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.booking.entity.BookingEntity;
import com.restaurant.booking.entity.TableDetailsEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

	@Query("SELECT b FROM BookingEntity b WHERE b.user.id = :userId")
	List<BookingEntity> findAllByUserId(@Param("userId") Long userId);
	
	 List<BookingEntity> findByTableAndBookingDate(TableDetailsEntity table, LocalDate bookingDate);
}
