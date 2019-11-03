package com.hampcode.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name="total",precision=8, scale=2)
	private Double total;

	public Item() {

	}

	public Item(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	
	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotal() {
		//return product.getPrice() * quantity.doubleValue();
		return total;
	}
}
