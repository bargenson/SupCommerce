package com.supinfo.supcommerce.service;

import com.supinfo.supcommerce.service.impl.CategoryServiceImpl;
import com.supinfo.supcommerce.service.impl.ProductServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	
	public static ServiceFactory getInstance() {
		if(instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}
	
	private CategoryService categoryService;
	private ProductService productService;
	
	private ServiceFactory() {	}
	
	public CategoryService getCategoryService() {
		if(categoryService == null) {
			categoryService = new CategoryServiceImpl();
		}
		return categoryService;
	}
	
	public ProductService getProductService() {
		if(productService == null) {
			productService = new ProductServiceImpl();
		}
		return productService;
	}

}
