package com.restaurant.booking.service.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.booking.bean.BookingModel;
import com.restaurant.booking.bean.TableDetailsModel;
import com.restaurant.booking.bean.UserModel;
import com.restaurant.booking.entity.BookingEntity;
import com.restaurant.booking.entity.RestaurantEntity;
import com.restaurant.booking.entity.TableDetailsEntity;
import com.restaurant.booking.entity.UserEntity;
import com.restaurant.booking.repository.BookingRepository;
import com.restaurant.booking.repository.RestaurantRepository;
import com.restaurant.booking.repository.UserRepository;
import com.restaurant.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public BookingModel bookTable(Long userId, Long restaurantId, int totalPersons, LocalDate bookingDate) {
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User with ID " + userId + " not found"));

		RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new NoSuchElementException("Restaurant with ID " + restaurantId + " not found"));

		List<TableDetailsEntity> tables = restaurantEntity.getTables().stream()
				.sorted(Comparator.comparing(TableDetailsEntity::getCapacity)).collect(Collectors.toList());

		TableDetailsEntity selectedTable = null;
		for (TableDetailsEntity table : tables) {
			if (table.getCapacity() >= totalPersons) {
				boolean isTableAvailable = bookingRepository.findByTableAndBookingDate(table, bookingDate).isEmpty();
				if (isTableAvailable) {
					selectedTable = table;
					break;
				}
			}
		}

		if (selectedTable == null) {
			throw new RuntimeException(
					"No table available for " + totalPersons + " persons at the restaurant on " + bookingDate);
		}

		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setUser(userEntity);
		bookingEntity.setTable(selectedTable);
		bookingEntity.setBookingDate(bookingDate);
		bookingEntity = bookingRepository.save(bookingEntity);
		return mapBookingEntityToBookingModel(bookingEntity);
	}

	@Override
	public List<BookingModel> getAllBookingsForUser(Long userId) {
		List<BookingEntity> bookingEntities = bookingRepository.findAllByUserId(userId);
		return bookingEntities.stream().map(this::mapBookingEntityToBookingModel).collect(Collectors.toList());
	}

	@Override
	public void cancelBooking(Long bookingId) {
		if (bookingId != null) {
			bookingRepository.deleteById(bookingId);
		}

	}

	private BookingModel mapBookingEntityToBookingModel(BookingEntity bookingEntity) {
		BookingModel bookingModel = new BookingModel();
		bookingModel.setId(bookingEntity.getId());
		bookingModel.setUser(mapUserEntityToUserModel(bookingEntity.getUser()));
		bookingModel.setTable(mapTableDetailsEntityToTableDetailsModel(bookingEntity.getTable()));
		bookingModel.setBookingDate(bookingEntity.getBookingDate());
		return bookingModel;
	}

	private UserModel mapUserEntityToUserModel(UserEntity userEntity) {
		UserModel userModel = new UserModel();
		userModel.setId(userEntity.getId());
		userModel.setEmail(userEntity.getEmail());
		userModel.setPassword(userEntity.getPassword());
		userModel.setFullName(userEntity.getFullName());
		userModel.setMobile(userEntity.getMobile());
		return userModel;
	}

	private TableDetailsModel mapTableDetailsEntityToTableDetailsModel(TableDetailsEntity tableDetailsEntity) {
		TableDetailsModel tableDetailsModel = new TableDetailsModel();
		tableDetailsModel.setId(tableDetailsEntity.getId());
		tableDetailsModel.setCapacity(tableDetailsEntity.getCapacity());
		tableDetailsModel.setRestaurantId(tableDetailsEntity.getRestaurantId().getId());
		return tableDetailsModel;
	}

}
