package com.myprojects.product.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.product.service.dto.ProductRequest;
import com.myprojects.product.service.dto.ProductResponse;
import com.myprojects.product.service.model.Product;
import com.myprojects.product.service.repository.ProductRepository;

import lombok.extern.slf4j.XSlf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
	
	@Autowired
	private final ProductRepository productRepository;

	public void createProduct(ProductRequest productResponse) {
		log.debug("Saving Product....");
        Product product = Product.builder()
        		.productname(productResponse.getProductname())
        		.productCategory(productResponse.getProductCategory())
        		.productDescription(productResponse.getProductDescription())
        		.productPrice(productResponse.getProductPrice())
        		.build();
        productRepository.save(product);
        log.info("Product successfully saved : {}",product.getId() );

	}

	public List<ProductResponse> getProducts() {
		log.debug("Getting all products....");
		List<Product> products = productRepository.findAll();
		List<ProductResponse> productResponses = products.stream().map(product -> mapToProductResponse(product)).toList();
		return productResponses;
	}

	private ProductResponse mapToProductResponse(Product product) {
		ProductResponse productResponse = ProductResponse.builder()
				.productCategory(product.getProductCategory())
				.id(product.getId())
				.productname(product.getProductname())
				.productDescription(product.getProductDescription())
				.productPrice(product.getProductPrice())
				.build();
		return productResponse;
	}
}
