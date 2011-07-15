package com.supinfo.supcommerce.model;

import java.io.Serializable;

public class CartItem implements Serializable {
	
	private final Product product;
	private Integer quantity;
	
	
	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void increaseQuantity() {
		quantity++;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
