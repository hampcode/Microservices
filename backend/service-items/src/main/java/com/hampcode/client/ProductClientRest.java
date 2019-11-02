package com.hampcode.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hampcode.model.entity.Product;

//@FeignClient(name="service-products",url="localhost:8001")
@FeignClient(name="service-products")
public interface ProductClientRest {
	
	@GetMapping("/products")
	public List<Product> getProducts();
	
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable Long id);
}
