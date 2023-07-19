package com.restaurant.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.booking.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}
