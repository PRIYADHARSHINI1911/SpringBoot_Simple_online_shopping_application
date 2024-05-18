package com.inventory.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.service.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	boolean existsByProductnameIgnoreCase(String productname);

}
