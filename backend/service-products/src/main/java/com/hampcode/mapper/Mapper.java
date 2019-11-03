package com.hampcode.mapper;

import org.springframework.stereotype.Component;

import com.hampcode.controller.viewmodel.ProductViewModel;
import com.hampcode.model.entity.Product;

@Component
public class Mapper {

	public ProductViewModel convertToProductViewModel(Product entity) {
		ProductViewModel viewModel = new ProductViewModel();
		viewModel.setId(entity.getId());
		viewModel.setName(entity.getName());
		viewModel.setDescription(entity.getDescription());
		viewModel.setPrice(entity.getPrice());

		return viewModel;
	}

	public Product convertToProductViewModelEntity(ProductViewModel viewModel) {
		Product entity = new Product(viewModel.getId(), viewModel.getName(),viewModel.getDescription(),viewModel.getPrice());

		return entity;
	}
	
	
}
