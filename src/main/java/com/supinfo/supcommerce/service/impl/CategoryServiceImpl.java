package com.supinfo.supcommerce.service.impl;

import java.util.List;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = DaoFactory.getDaoFactory().getCategoryDao();
	
	@Override
	public void addCategory(Category category) {
		categoryDao.addCategory(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryDao.getCategoryById(id);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public void removeCategory(Category category) {
		categoryDao.removeCategory(category);
	}

}
