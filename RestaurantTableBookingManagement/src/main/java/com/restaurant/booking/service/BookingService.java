package com.restaurant.booking.service;

import java.time.LocalDate;
import java.util.List;

import com.restaurant.booking.bean.BookingModel;

public interface BookingService {

	public BookingModel bookTable(Long userId, Long restaurantId, int totalPersons, LocalDate bookingDate);

	public void cancelBooking(Long bookingId);

	public List<BookingModel> getAllBookingsForUser(Long userId);

}
