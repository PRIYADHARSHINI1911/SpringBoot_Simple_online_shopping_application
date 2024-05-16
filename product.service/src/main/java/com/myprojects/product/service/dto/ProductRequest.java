package com.myprojects.product.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest {

	private String productname;
	private String productCategory;
	private String productDescription;
	private int productPrice;
}
