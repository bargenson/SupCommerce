package com.supinfo.supcommerce.service;

import java.util.List;

import com.supinfo.supcommerce.model.Category;

public interface CategoryService {
	
	void addCategory(Category category);
	Category getCategoryById(Long id);
	List<Category> getAllCategories();
	void updateCategory(Category category);
	void removeCategory(Category category);

}
