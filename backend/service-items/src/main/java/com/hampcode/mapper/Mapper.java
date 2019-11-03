package com.hampcode.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hampcode.client.ProductClientRest;
import com.hampcode.controller.viewmodel.OrderViewModel;
import com.hampcode.model.entity.Item;
import com.hampcode.model.entity.Product;

@Component
public class Mapper {
	
	@Autowired
	private ProductClientRest productClienteRest;

	public OrderViewModel convertToOrderViewModel(Item entity) {
		OrderViewModel viewModel = new OrderViewModel();
		viewModel.setId(entity.getId());
		viewModel.setQuantity(entity.getQuantity());
		viewModel.setProductId(entity.getProduct().getId());
		

		return viewModel;
	}

	/*public Item convertToOrderViewModelEntity(OrderViewModel viewModel) {
		Product product = this.productClienteRest.getProductById(viewModel.getProductId());
		Item entity = new Item(product,viewModel.getQuantity());

		return entity;
	}*/
	
	
}
