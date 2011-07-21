package com.supinfo.supcommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.model.Product;

@WebListener
public class Bootstrap implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);
	
	@Override
	public void contextInitialized(ServletContextEvent e) {
		CategoryDao categoryService = DaoFactory.getDaoFactory().getCategoryDao();
		ProductDao productDao = DaoFactory.getDaoFactory().getProductDao();

		LOGGER.info("Boostrap - Populate database.");
		
		List<Category> categories = createBasicCategories();
		for (Category category : categories) {
			categoryService.addCategory(category);
		}
		
		List<Product> products = createBasicProducts(categories);
		for (Product product : products) {
			productDao.addProduct(product);
		}
	}

	private List<Product> createBasicProducts(List<Category> categories) {
		List<Product> products = new ArrayList<Product>();
		
		products.add(
			new Product(
				"Design Patterns", 
				"Presents a catalog of simple solutions to commonly occurring design problems. " +
					"These 23 patterns allow designers to create more reusable designs without having to " +
					"rediscover the design solutions themselves. The authors begin by describing what " +
					"patterns are, and how they can help you design object-oriented software.", 
				new BigDecimal("27.99"),
				categories.get(0),
				null
			)
		);
		products.add(
			new Product(
				"Monty Python And The Holy Grail", 
				"Classic comedy from the Monty Python team in their second feature. " +
					"King Arthur and his trusty knights fearlessly (on the most part) travel the " +
					"length and breadth of the country in search of the mythical Holy Grail. " +
					"On their way they have to deal with the sarcastic taunts of the French Knight, " +
					"the Knights who say'Ni', Tim the Enchanter and the Terror of the Cave of " +
					"Caerbannog amongst other things.", 
				new BigDecimal("43.90"),
				categories.get(0),
				null
			)
		);
		products.add(
			new Product(
				"Restful Web Services", 
				"This text teaches programmers how to create web services using the same standards " +
					"that make the Web so successful. It shows developers how to use the Representational " +
					"State Transfer web architecture (REST) to provide services over the web that are " +
					"fundamentally simple.", 
				new BigDecimal("52.31"),
				categories.get(0),
				null
			)
		);
		
		return products;
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
