package com.supinfo.supcommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class ShoppingCart implements Serializable {
	
	private final Customer customer;
	private final List<CartItem> items;
	
	public ShoppingCart(Customer customer) {
		this.customer = customer;
		this.items = new ArrayList<CartItem>();
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public List<CartItem> getItems() {
		return Collections.unmodifiableList(items);
	}
	
	public void setCartItemQuantity(int index, int quantity) {
		if(quantity > 0) {
			items.get(index).setQuantity(quantity);
		} else {
			items.remove(index);
		}
	}
	
	public void addProduct(final Product product) {
		try {
			CartItem cartItem = Iterables.find(items, matchToProduct(product));
			cartItem.increaseQuantity();
		} catch (NoSuchElementException e) {
			items.add(new CartItem(product));
		}
	}
	
	public void removeProduct(final Product product) {
		Iterables.removeIf(items, matchToProduct(product));
	}
	
	public void emptyCart() {
		items.clear();
	}
	
	private static Predicate<CartItem> matchToProduct(final Product product) {
		return new Predicate<CartItem>() {
			@Override
			public boolean apply(CartItem cartItem) {
				return cartItem.getProduct().equals(product);
			}
		};
	}
	
	public static class CartItem implements Serializable {
		
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

}
