package com.restaurant.booking.service;

import java.util.List;

import com.restaurant.booking.bean.RestaurantModel;

public interface RestaurantService {

	public RestaurantModel createRestaurant(RestaurantModel bean);

	public RestaurantModel getRestaurantById(Long id);

	public List<RestaurantModel> getAllRestaurants();

}
