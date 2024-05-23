package com.inventory.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.service.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{


	List<Inventory> findByProductnameIn(List<String> productname);

}
