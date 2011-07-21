package com.supinfo.supcommerce.dao.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.PictureDao;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.UserDao;

public class JpaDaoFactory extends DaoFactory {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("supcommerce");
	
	private JpaCategoryDao categoryDao;
	private JpaProductDao productDao;
	private JpaUserDao userDao;
	private JpaPictureDao pictureDao;
	
	@Override
	public CategoryDao getCategoryDao() {
		if(categoryDao == null) {
			categoryDao = new JpaCategoryDao(emf);
		}
		return categoryDao;
	}

	@Override
	public ProductDao getProductDao() {
		if(productDao == null) {
			productDao = new JpaProductDao(emf);
		}
		return productDao;
	}

	@Override
	public UserDao getUserDao() {
		if(userDao == null) {
			userDao = new JpaUserDao(emf);
		}
		return userDao;
	}

	@Override
	public PictureDao getPictureDao() {
		if(pictureDao == null) {
			pictureDao = new JpaPictureDao(emf);
		}
		return pictureDao;
	}

}
