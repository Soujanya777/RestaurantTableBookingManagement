package com.restaurant.booking.bean;

import java.time.LocalDate;

public class BookingModel {

	private Long id;
	private UserModel user;
	private TableDetailsModel table;
	private LocalDate bookingDate;

	public BookingModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public TableDetailsModel getTable() {
		return table;
	}

	public void setTable(TableDetailsModel table) {
		this.table = table;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

}
