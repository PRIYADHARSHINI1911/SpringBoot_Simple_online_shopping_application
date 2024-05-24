package com.order.service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.service.dto.InventoryResponse;
import com.order.service.dto.OrderLineItemsDTO;
import com.order.service.dto.OrderRequest;
import com.order.service.model.Order;
import com.order.service.model.OrderLineItems;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

	@Autowired
	WebClient webClient;

	public String inventoryUrl = "http://localhost:8082/v1/inventory";

	@Autowired
	com.order.service.repository.OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<String> productNameList = orderRequest.getOrderLineItemsDTO().stream()
				.map(orderLineItem -> orderLineItem.getProductname()).toList();
        log.debug("productNameList , {}" , productNameList);
		List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDTO().stream()
				.map(orderLineItemsDTO -> mapToDTO(orderLineItemsDTO)).toList();
		order.setOrderLineItemsList(orderLineItemsList);

		InventoryResponse[] inventoryResponseList = webClient.get()
				.uri(inventoryUrl, uriBuilder -> uriBuilder.queryParam("productName", productNameList).build())
				.retrieve().bodyToMono(InventoryResponse[].class).block();
		
		log.debug(inventoryResponseList.toString());
		boolean allProductsAvailable = Arrays.stream(inventoryResponseList)
				.allMatch(inventoryResponse -> inventoryResponse.isInStock());
        log.debug("allProductsAvailable : ",allProductsAvailable);
		if (allProductsAvailable) {
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException("Product is not in stock, Please try again later");
		}
	}

	private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
		log.debug("Mapping the products..");
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
		orderLineItems.setProductname(orderLineItemsDTO.getProductname());
		orderLineItems.setPrice(orderLineItemsDTO.getPrice());
		return orderLineItems;
	}

}
