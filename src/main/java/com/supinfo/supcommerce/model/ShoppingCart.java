package com.supinfo.supcommerce.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	public void addProduct(final Product product) {
		CartItem cartItem = Iterables.find(items, matchToProduct(product));
		if(cartItem == null) {
			items.add(new CartItem(product));
		} else {
			cartItem.increaseQuantity();
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

}
