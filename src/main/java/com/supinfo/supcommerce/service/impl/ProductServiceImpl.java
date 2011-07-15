package com.supinfo.supcommerce.service.impl;

import java.util.List;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.model.Product;
import com.supinfo.supcommerce.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao = DaoFactory.getDaoFactory().getProductDao();

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	@Override
	public Product getProductById(Long id) {
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

	@Override
	public void removeProduct(Product product) {
		productDao.removeProduct(product);
	}

}
