package com.inventory.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.dto.InventoryResponse;
import com.inventory.service.dto.InventoryStockInfoRequest;
import com.inventory.service.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/inventory")
@Slf4j
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam("productName") List<String> productNames ) {
		log.debug("Checking {} is in stock..", productNames);
		return inventoryService.isInStock(productNames);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String addStock( @RequestBody InventoryStockInfoRequest inventoryStockInfoRequest ) {
	   log.debug("Adding the stock..");
	   inventoryService.addStock(inventoryStockInfoRequest);
	   return "Stock added successfully...";
	}
}
