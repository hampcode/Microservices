package com.hampcode.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.controller.viewmodel.OrderViewModel;
import com.hampcode.mapper.Mapper;
import com.hampcode.model.entity.Item;
import com.hampcode.model.entity.Product;
import com.hampcode.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
    private Mapper mapper;

	@GetMapping
	public ResponseEntity<List<Item>> getItems() {
		List<Item> items = itemService.getAll();
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	/*@GetMapping
	public List<OrderViewModel> getItems() {
		List<Item> items = this.itemService.getAll();

		// map from entity to view model
		List<OrderViewModel> ordersViewModel = items.stream().map(item -> this.mapper.convertToOrderViewModel(item))
				.collect(Collectors.toList());

		return ordersViewModel;
	}*/
	
	@HystrixCommand(fallbackMethod="methodAlternative")
	//@GetMapping("/product/{productId}/quantity/{quantity}")
	@GetMapping("/product/{productId}")
	//public ResponseEntity<Item> addProductCart(@PathVariable Long productId, @PathVariable Integer quantity) {
	public ResponseEntity<Item> addProductCart(@PathVariable Long productId){
		//Item item = itemService.shoppingCart(productId, quantity);
		Item item = itemService.shoppingCart(productId, 1 );	
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}

	/*@PostMapping
	public ResponseEntity<Item> createItem(@RequestBody OrderViewModel orderViewModel) {
		
		Item item = this.mapper.convertToOrderViewModelEntity(orderViewModel);
		itemService.create(item);
		return new ResponseEntity<Item>(item, HttpStatus.CREATED);
	}*/
	
	@PostMapping
	public ResponseEntity<Item> createItem(@RequestBody Item item) {
		
		itemService.create(item);
		return new ResponseEntity<Item>(item, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Item> methodAlternative(Long productId, Integer quantity) {
		Item item=new Item();
		Product product=new Product();
		
		item.setQuantity(quantity);
		product.setId(productId);
		product.setName("Camara Sony");
		product.setDescription("Camara Sony");
		product.setPrice(500.00);
		
		item.setProduct(product);
		
		return new ResponseEntity<Item>(item, HttpStatus.OK);
		
	}
	
}
