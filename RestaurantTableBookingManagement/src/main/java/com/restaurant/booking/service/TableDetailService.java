package com.restaurant.booking.service;

import com.restaurant.booking.bean.TableDetailsModel;

public interface TableDetailService {

	public TableDetailsModel create(TableDetailsModel tableDetailsBean);

	public TableDetailsModel getById(Long id);

}
