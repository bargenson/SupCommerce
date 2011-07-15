package com.supinfo.supcommerce.dao;

import java.util.List;

import com.supinfo.supcommerce.model.Product;

public interface ProductDao {
	
	void addProduct(Product product);
	Product getProductById(Long id);
	List<Product> getAllProducts();
	void updateProduct(Product product);
	void removeProduct(Product product);

}
