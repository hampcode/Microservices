package com.hampcode.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hampcode.client.ProductClientRest;
import com.hampcode.model.entity.Item;

import com.hampcode.model.repository.ItemRepository;
import com.hampcode.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ProductClientRest productClienteRest;
	
	@Autowired
	private ItemRepository itemRepository;

	@Transactional
	@Override
	public Item create(Item entity) {
		Double total= entity.getProduct().getPrice() * entity.getQuantity().doubleValue();
		entity.setTotal(total);
		return itemRepository.save(entity);
	}

	@Override
	public List<Item> getAll() {
		List<Item> items = new ArrayList<>();
		itemRepository.findAll().iterator().forEachRemaining(items::add);
		return items;
	}

	@Override
	public Optional<Item> getOne(Long id) {
		return itemRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		itemRepository.deleteById(id);
	}
	
	/*@Override
	public List<Item> getAll() {
		return productClienteRest.getProducts()
				.stream()
				.map(product-> new Item(product,1))
				.collect(Collectors.toList());
	}

	@Override
	public Item getOne(Long id) {
		return null;
	}*/

	@Override
	public Item shoppingCart(Long productId, Integer quantity) {
		return new Item(productClienteRest.getProductById(productId),quantity);
	}

}
