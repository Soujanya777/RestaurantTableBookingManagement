package com.restaurant.booking.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.booking.bean.UserModel;
import com.restaurant.booking.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create")
	public ResponseEntity<UserModel> create(@RequestBody UserModel userBean) {
		UserModel createUser = userService.createUser(userBean);
		return new ResponseEntity<UserModel>(createUser, HttpStatus.CREATED);
	}

	@GetMapping(value = "/login")
	public ResponseEntity<UserModel> getUserByEmail(@RequestParam String email, @RequestParam String password) {
		UserModel userByUsername = userService.getUser(email, password);
		return new ResponseEntity<UserModel>(userByUsername, HttpStatus.OK);
	}
}
