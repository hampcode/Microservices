package com.hampcode.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hampcode.client.ProductClientRest;
import com.hampcode.model.entity.Item;
import com.hampcode.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ProductClientRest productClienteRest;
	
	@Override
	public List<Item> getAll() {
		return productClienteRest.getProducts()
				.stream()
				.map(product-> new Item(product,1))
				.collect(Collectors.toList());
	}

	@Override
	public Item getOne(Long id) {
		return null;
	}

	@Override
	public Item shoppingCart(Long productId, Integer quantity) {
		return new Item(productClienteRest.getProductById(productId),quantity);
	}

}
