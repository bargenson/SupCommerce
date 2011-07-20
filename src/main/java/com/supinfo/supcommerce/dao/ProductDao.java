package com.supinfo.supcommerce.dao;

import java.util.List;

import com.supinfo.supcommerce.model.Product;

public interface ProductDao {
	
	Product addProduct(Product product);
	Product findProductById(Long id);
	List<Product> getAllProducts();
	void updateProduct(Product product);
	void removeProduct(Product product);

}
