package com.training.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.repository.Orders;
import com.training.repository.OrdersRepository;

@RestController
@ConfigurationProperties(prefix = "newConfig")

public class OrdersAPI {

	@Autowired
	private OrdersRepository orderRepo;

	@RequestMapping(value = "/api/order", method = RequestMethod.POST)
	public ResponseEntity<Orders> add(@RequestBody Orders manufacturer) {
		orderRepo.save(manufacturer);
		return new ResponseEntity<Orders>(manufacturer, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/order/{id}", method = RequestMethod.GET)
	public ResponseEntity<Orders> find(@PathVariable(value = "id") Integer id) {
		Orders manufacturer = orderRepo.findOne(id);
		return new ResponseEntity<Orders>(manufacturer, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/order/manufacturer/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Orders>> findByManufacturerId(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<List<Orders>>(orderRepo.findByManufactuerId(id), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/api/order", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Orders>> findAll() {
		return new ResponseEntity<List<Orders>>(orderRepo.findAll(), HttpStatus.OK);
	}
	 
	/*
	@RequestMapping(value = "/api/order", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Orders>> findAll() {
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(new Orders(1000, "Dummy Name", new Date(), false));
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}*/

	@RequestMapping(value = "/api/order/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
		orderRepo.delete(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/api/order/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable(value = "id") Integer id, @RequestBody Orders manufac) {
		Orders man = orderRepo.findOne(id);
		if (man != null) {
			man.setOrderDate(manufac.getOrderDate());
			man.setName(manufac.getName());
			man.setIsActive(manufac.getIsActive());
			man.setManufacturerId(manufac.getManufacturerId());
			orderRepo.save(man);
		} else {
			return new ResponseEntity<String>("Manufacturer not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Updated", HttpStatus.OK);
	}

}
