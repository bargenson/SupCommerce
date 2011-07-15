package com.supinfo.supcommerce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.service.CategoryService;
import com.supinfo.supcommerce.service.ServiceFactory;

@WebListener
public class Bootstrap implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);
	
	@Override
	public void contextInitialized(ServletContextEvent e) {
		CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();

		LOGGER.info("Boostrap - Populate database.");
		
		List<Category> categories = createBasicCategories();
		for (Category category : categories) {
			categoryService.addCategory(category);
		}
	}

	private List<Category> createBasicCategories() {
		List<Category> categories = new ArrayList<Category>();
		
		categories.add(new Category("Books"));
		categories.add(new Category("Music"));
		categories.add(new Category("DVD"));
		categories.add(new Category("Video Games"));
		categories.add(new Category("Computing"));
		categories.add(new Category("Electronics"));
		
		return categories;
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) { }

}
