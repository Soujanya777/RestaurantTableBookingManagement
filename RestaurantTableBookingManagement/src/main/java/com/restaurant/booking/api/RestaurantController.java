package com.restaurant.booking.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.booking.bean.RestaurantModel;
import com.restaurant.booking.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping()
	public ResponseEntity<RestaurantModel> create(@RequestBody RestaurantModel restaurantBean) {
		RestaurantModel createRestaurant = restaurantService.createRestaurant(restaurantBean);
		return new ResponseEntity<RestaurantModel>(createRestaurant, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RestaurantModel> getById(@PathVariable Long id) {
		return new ResponseEntity<RestaurantModel>(restaurantService.getRestaurantById(id), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<RestaurantModel>> getAllRestaurants() {
		List<RestaurantModel> allRestaurants = restaurantService.getAllRestaurants();
		return new ResponseEntity<List<RestaurantModel>>(allRestaurants, HttpStatus.OK);
	}

}
