package com.hampcode.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.controller.viewmodel.ProductViewModel;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.Mapper;
import com.hampcode.model.entity.Product;
import com.hampcode.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private Environment env;

	@Autowired
	private ProductService productService;

	@Autowired
    private Mapper mapper;

	

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.getAll().stream().map(product -> {
			product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			return product;
		}).collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		 Optional<Product> product = productService.getOne(id);
		    product.get().setPort(Integer.parseInt(env.getProperty("local.server.port")));
	        if (!product.isPresent()) {
	           new ResourceNotFoundException("Id " + id + " is not existed");
	        }

	        return ResponseEntity.ok(product.get());
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody ProductViewModel productViewModel) {
		
		Product product = this.mapper.convertToProductViewModelEntity(productViewModel);
		productService.create(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,  @RequestBody ProductViewModel productViewModel) {
        if (!productService.getOne(id).isPresent()) {
        	new ResourceNotFoundException("Product not found with id " + id);
        }

        Product product = this.mapper.convertToProductViewModelEntity(productViewModel);
        return ResponseEntity.ok(productService.create(product));
    }


	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		return productService.getOne(id).map(product -> {
			productService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

	}
	
	

}
