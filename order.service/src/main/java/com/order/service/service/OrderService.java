package com.order.service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.service.dto.OrderLineItemsDTO;
import com.order.service.dto.OrderRequest;
import com.order.service.model.Order;
import com.order.service.model.OrderLineItems;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	com.order.service.repository.OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDTO()
				.stream()
				.map(orderLineItemsDTO -> mapToDTO(orderLineItemsDTO))
				.toList();
		order.setOrderLineItemsList(orderLineItemsList);
		orderRepository.save(order);
		
	}

	private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
		orderLineItems.setProductname(orderLineItemsDTO.getProductname());
		orderLineItems.setPrice(orderLineItemsDTO.getPrice());
		return orderLineItems;
	}

}
