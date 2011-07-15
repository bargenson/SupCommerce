package com.supinfo.supcommerce.dao;

import com.supinfo.supcommerce.dao.jpa.JpaDaoFactory;

public abstract class DaoFactory {
		
	public static DaoFactory getDaoFactory() {
		return new JpaDaoFactory();
	}
	
	public abstract CategoryDao getCategoryDao();
	public abstract ProductDao getProductDao();
	public abstract UserDao getUserDao();

}
