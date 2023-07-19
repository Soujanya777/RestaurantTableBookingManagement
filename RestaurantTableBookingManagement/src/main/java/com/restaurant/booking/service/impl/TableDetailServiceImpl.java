package com.restaurant.booking.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.booking.bean.TableDetailsModel;
import com.restaurant.booking.entity.RestaurantEntity;
import com.restaurant.booking.entity.TableDetailsEntity;
import com.restaurant.booking.repository.RestaurantRepository;
import com.restaurant.booking.repository.TableDetailsRepository;
import com.restaurant.booking.service.TableDetailService;

@Service
public class TableDetailServiceImpl implements TableDetailService {

	@Autowired
	private TableDetailsRepository tableDetailsRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public TableDetailsModel create(TableDetailsModel tableDetailsBean) {
		Optional<RestaurantEntity> findById = restaurantRepository.findById(tableDetailsBean.getRestaurantId());
		if (tableDetailsBean != null && findById.isPresent()) {
			TableDetailsEntity tableDetailsEntity = new TableDetailsEntity();
			tableDetailsEntity.setCapacity(tableDetailsBean.getCapacity());
			RestaurantEntity restaurantEntity = findById.get();
			tableDetailsEntity.setRestaurantId(restaurantEntity);
			tableDetailsRepository.save(tableDetailsEntity);
			tableDetailsBean.setId(tableDetailsEntity.getId());
		} else {
			throw new NoSuchElementException("Restaurant not found with the given id");
		}
		return tableDetailsBean;
	}

	@Override
	public TableDetailsModel getById(Long id) {
		Optional<TableDetailsEntity> optional = tableDetailsRepository.findById(id);
		TableDetailsModel tableDetailsModel = null;
		if (optional.isPresent()) {
			TableDetailsEntity tableEntity = optional.get();
			tableDetailsModel = new TableDetailsModel();
			tableDetailsModel.setId(tableEntity.getId());
			tableDetailsModel.setCapacity(tableEntity.getCapacity());
			tableDetailsModel.setRestaurantId(tableEntity.getRestaurantId().getId());
		}
		return tableDetailsModel;
	}

}
