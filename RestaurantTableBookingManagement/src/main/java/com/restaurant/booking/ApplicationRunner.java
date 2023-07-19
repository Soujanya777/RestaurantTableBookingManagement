package com.restaurant.booking;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.restaurant.booking.bean.RestaurantModel;
import com.restaurant.booking.bean.TableDetailsModel;
import com.restaurant.booking.bean.UserModel;
import com.restaurant.booking.service.RestaurantService;
import com.restaurant.booking.service.TableDetailService;
import com.restaurant.booking.service.UserService;

@Component
public class ApplicationRunner implements CommandLineRunner {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private UserService userService;

	@Autowired
	private TableDetailService tableDetailService;

	@Override
	public void run(String... args) throws Exception {
		RestaurantModel model = new RestaurantModel();
		model.setName("MacD");
		model.setOpenTime(LocalTime.of(10, 0));
		model.setCloseTime(LocalTime.now().plusHours(10));
		model = restaurantService.createRestaurant(model);

		RestaurantModel model2 = new RestaurantModel();
		model2.setName("KFC");
		model2.setOpenTime(LocalTime.of(10, 0));
		model2.setCloseTime(LocalTime.now().plusHours(10));
		model2 = restaurantService.createRestaurant(model2);

		UserModel userModel = new UserModel();
		userModel.setEmail("test@gmail.com");
		userModel.setFullName("Guest User 1");
		userModel.setPassword("123@abc");
		userService.createUser(userModel);

		UserModel userModel1 = new UserModel();
		userModel1.setEmail("test1@gmail.com");
		userModel1.setFullName("Guest User 2");
		userModel1.setPassword("123@abc");
		userService.createUser(userModel1);

		UserModel userModel2 = new UserModel();
		userModel2.setEmail("test2@gmail.com");
		userModel2.setFullName("Guest User 3");
		userModel2.setPassword("123@abc");
		userService.createUser(userModel2);

		TableDetailsModel tableDetailsModel = new TableDetailsModel();
		tableDetailsModel.setCapacity(5);
		tableDetailsModel.setRestaurantId(model.getId());
		tableDetailService.create(tableDetailsModel);

		TableDetailsModel tableDetailsModel1 = new TableDetailsModel();
		tableDetailsModel1.setCapacity(8);
		tableDetailsModel1.setRestaurantId(model.getId());
		tableDetailService.create(tableDetailsModel1);

		TableDetailsModel tableDetailsModel2 = new TableDetailsModel();
		tableDetailsModel2.setCapacity(6);
		tableDetailsModel2.setRestaurantId(model.getId());
		tableDetailService.create(tableDetailsModel2);

		TableDetailsModel tableDetailsModel3 = new TableDetailsModel();
		tableDetailsModel3.setCapacity(10);
		tableDetailsModel3.setRestaurantId(model.getId());
		tableDetailService.create(tableDetailsModel3);
		
		
		TableDetailsModel tableDetailsModel4 = new TableDetailsModel();
		tableDetailsModel4.setCapacity(5);
		tableDetailsModel4.setRestaurantId(model2.getId());
		tableDetailService.create(tableDetailsModel4);

		TableDetailsModel tableDetailsModel5 = new TableDetailsModel();
		tableDetailsModel5.setCapacity(8);
		tableDetailsModel5.setRestaurantId(model2.getId());
		tableDetailService.create(tableDetailsModel5);

		TableDetailsModel tableDetailsModel6 = new TableDetailsModel();
		tableDetailsModel6.setCapacity(6);
		tableDetailsModel6.setRestaurantId(model2.getId());
		tableDetailService.create(tableDetailsModel6);

		TableDetailsModel tableDetailsModel7 = new TableDetailsModel();
		tableDetailsModel7.setCapacity(10);
		tableDetailsModel7.setRestaurantId(model2.getId());
		tableDetailService.create(tableDetailsModel7);
	}

}
