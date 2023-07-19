package com.restaurant.booking.service;

import com.restaurant.booking.bean.UserModel;

public interface UserService {
	
	UserModel createUser(UserModel userBean);

	UserModel getUser(String email, String password);

}
