package com.supinfo.supcommerce.dao.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.dao.DaoFactory;

public class JpaDaoFactory extends DaoFactory {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("supcommerce");
	
	private JpaCategoryDao categoryService;
	private JpaProductDao productService;
	
	@Override
	public CategoryDao getCategoryDao() {
		if(categoryService == null) {
			categoryService = new JpaCategoryDao(emf);
		}
		return categoryService;
	}

	@Override
	public ProductDao getProductDao() {
		if(productService == null) {
			productService = new JpaProductDao(emf);
		}
		return productService;
	}

}
