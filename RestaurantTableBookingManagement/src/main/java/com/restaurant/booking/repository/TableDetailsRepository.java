package com.restaurant.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurant.booking.entity.TableDetailsEntity;

@Repository
public interface TableDetailsRepository extends JpaRepository<TableDetailsEntity, Long> {

	@Query(value = "SELECT * FROM table_details WHERE restaurant_id = ?1", nativeQuery = true)
	List<TableDetailsEntity> findByRestaurantId(Long id);

}
