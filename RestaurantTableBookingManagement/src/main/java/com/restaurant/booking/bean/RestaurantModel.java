package com.restaurant.booking.bean;

import java.time.LocalTime;
import java.util.List;

public class RestaurantModel {

	private Long id;
	private String name;
	private LocalTime openTime;
	private LocalTime closeTime;
	private List<TableDetailsModel> tables;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(LocalTime openTime) {
		this.openTime = openTime;
	}

	public LocalTime getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(LocalTime closeTime) {
		this.closeTime = closeTime;
	}

	public List<TableDetailsModel> getTables() {
		return tables;
	}

	public void setTables(List<TableDetailsModel> tables) {
		this.tables = tables;
	}

}
