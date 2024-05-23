package com.inventory.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.service.dto.InventoryResponse;
import com.inventory.service.dto.InventoryStockInfoRequest;
import com.inventory.service.model.Inventory;
import com.inventory.service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	public List<InventoryResponse> isInStock(List<String> productName) {
		log.debug("Checking the given product is in stock or not...");
		return inventoryRepository
				.findByProductnameIn(productName).stream().map(inventory -> InventoryResponse.builder()
						.isInStock(inventory.getQuantity() > 0).productName(inventory.getProductname()).build())
				.toList();		
	}

	public void addStock(InventoryStockInfoRequest inventoryStockInfoRequest) {
		log.debug("Inventory Stock : {}", inventoryStockInfoRequest);
		Inventory inventory = new Inventory();
		inventory.setProductname(inventoryStockInfoRequest.getProductname());
		inventory.setQuantity(inventoryStockInfoRequest.getQuantity());
		inventoryRepository.save(inventory);
		log.debug("Saved successfully");
	}

}
