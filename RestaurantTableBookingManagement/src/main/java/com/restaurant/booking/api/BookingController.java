package com.restaurant.booking.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.booking.bean.BookingModel;
import com.restaurant.booking.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping(value = "/book")
	public ResponseEntity<BookingModel> bookTable(@RequestParam Long userId, 
	        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate bookingDate,
			@RequestParam Long restaurantId,
			@RequestParam int totalPersons) {
		return new ResponseEntity<BookingModel>(bookingService.bookTable(userId, restaurantId, totalPersons, bookingDate),
				HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<List<BookingModel>> name(@PathVariable Long id) {
		return new ResponseEntity<List<BookingModel>>(bookingService.getAllBookingsForUser(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void cancel(@PathVariable Long id) {
		bookingService.cancelBooking(id);
	}

}
