package com.restaurant.booking.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.booking.bean.UserModel;
import com.restaurant.booking.entity.UserEntity;
import com.restaurant.booking.repository.UserRepository;
import com.restaurant.booking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserModel createUser(UserModel userBean) {
		if (userBean != null) {
			UserEntity userEntity = new UserEntity();
			BeanUtils.copyProperties(userBean, userEntity);
			userRepository.save(userEntity);
			userBean.setId(userEntity.getId());
		}
		return userBean;
	}

	@Override
	public UserModel getUser(String email, String password) {
		Optional<UserEntity> userEntityOptional = userRepository.findByEmailAndPassword(email, password);
		UserEntity userEntity = userEntityOptional
				.orElseThrow(() -> new NoSuchElementException("User with email and password not found"));
		return new UserModel(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(),
				userEntity.getFullName(), userEntity.getMobile());
	}

}
