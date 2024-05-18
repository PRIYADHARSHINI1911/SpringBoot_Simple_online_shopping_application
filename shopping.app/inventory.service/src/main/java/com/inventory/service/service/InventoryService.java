package com.inventory.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.service.dto.InventoryStockInfoRequest;
import com.inventory.service.model.Inventory;
import com.inventory.service.repository.InventoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {
	
	@Autowired
	InventoryRepository inventoryRepository;
	
	public boolean isInStock(String productName) {
		return inventoryRepository.existsByProductnameIgnoreCase(productName);	
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
