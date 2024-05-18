package com.order.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.dto.OrderRequest;
import com.order.service.service.OrderService;

@RestController
@RequestMapping("v1/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	/**
	 * Save the product
	 * @param orderRequest
	 * @return
	 */
	@PostMapping
	public String postProduct(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
		return "Order placed successfully...";
	}
	
	
}
