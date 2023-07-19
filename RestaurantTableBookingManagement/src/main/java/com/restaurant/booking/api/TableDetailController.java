package com.restaurant.booking.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.booking.bean.TableDetailsModel;
import com.restaurant.booking.service.TableDetailService;

@RestController
@RequestMapping(value = "/api/table")
public class TableDetailController {

	@Autowired
	private TableDetailService tableDetailService;

	@PostMapping
	public ResponseEntity<TableDetailsModel> create(@RequestBody TableDetailsModel detailsBean) {
		return new ResponseEntity<TableDetailsModel>(tableDetailService.create(detailsBean), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TableDetailsModel> getById(@PathVariable Long id) {
		return new ResponseEntity<TableDetailsModel>(tableDetailService.getById(id), HttpStatus.OK);
	}

}
