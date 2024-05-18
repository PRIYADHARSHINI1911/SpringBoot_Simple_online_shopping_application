package com.inventory.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.dto.InventoryStockInfoRequest;
import com.inventory.service.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/inventory")
@Slf4j
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;

	@GetMapping("/{product-name}")
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock(@PathVariable("product-name") String productName ) {
		log.debug("Checking {} is in stock..", productName);
		return inventoryService.isInStock(productName);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String addStock( @RequestBody InventoryStockInfoRequest inventoryStockInfoRequest ) {
	   log.debug("Adding the stock..");
	   inventoryService.addStock(inventoryStockInfoRequest);
	   return "Stock added successfully...";
	}
}
