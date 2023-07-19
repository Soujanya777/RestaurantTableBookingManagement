package com.restaurant.booking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.booking.bean.RestaurantModel;
import com.restaurant.booking.bean.TableDetailsModel;
import com.restaurant.booking.entity.RestaurantEntity;
import com.restaurant.booking.entity.TableDetailsEntity;
import com.restaurant.booking.repository.RestaurantRepository;
import com.restaurant.booking.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public RestaurantModel createRestaurant(RestaurantModel restaurantModel) {
		if (restaurantModel != null) {
			RestaurantEntity entity = new RestaurantEntity();
			BeanUtils.copyProperties(restaurantModel, entity);
			restaurantRepository.save(entity);
			restaurantModel.setId(entity.getId());
		}
		return restaurantModel;
	}

	@Override
	public RestaurantModel getRestaurantById(Long id) {
		Optional<RestaurantEntity> optional = restaurantRepository.findById(id);
		RestaurantModel bean = null;
		if (optional.isPresent()) {
			RestaurantEntity restaurantEntity = optional.get();
			bean = entityToModel(restaurantEntity);
		}
		return bean;
	}

	@Override
	public List<RestaurantModel> getAllRestaurants() {
		List<RestaurantEntity> restaurantEntitiesList = restaurantRepository.findAll();
		List<RestaurantModel> restaurantModels = new ArrayList<>();
		for (RestaurantEntity restaurant : restaurantEntitiesList) {
			RestaurantModel restaurantModel = new RestaurantModel();
			restaurantModel = entityToModel(restaurant);
			restaurantModels.add(restaurantModel);
		}
		return restaurantModels;
	}

	private RestaurantModel entityToModel(RestaurantEntity entity) {
		RestaurantModel restaurantModel = new RestaurantModel();
		restaurantModel.setCloseTime(entity.getCloseTime());
		restaurantModel.setOpenTime(entity.getOpenTime());
		restaurantModel.setName(entity.getName());
		restaurantModel.setId(entity.getId());
		restaurantModel.setTables(new ArrayList<>());
		for (TableDetailsEntity tableEntity : entity.getTables()) {
			TableDetailsModel tableDetailsModel = new TableDetailsModel();
			tableDetailsModel.setCapacity(tableEntity.getCapacity());
			tableDetailsModel.setId(tableEntity.getId());
			tableDetailsModel.setRestaurantId(tableEntity.getRestaurantId().getId());
			restaurantModel.getTables().add(tableDetailsModel);
		}
		return restaurantModel;

	}

}
