package com.supinfo.supcommerce.dao;

import java.util.List;

import com.supinfo.supcommerce.model.Category;

public interface CategoryDao {
	
	void addCategory(Category category);
	Category getCategoryById(Long id);
	List<Category> getAllCategories();
	void updateCategory(Category category);
	void removeCategory(Category category);
	Category getCategoryByIdWithProducts(Long id);

}
