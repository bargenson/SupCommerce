package com.supinfo.supcommerce.service.impl;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Before;

import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.model.Product;

public class ProductServiceImplTest {

	private ProductServiceImpl productService = new ProductServiceImpl();
	private Product eeePad;
	
	@Before
	public void setUp() {
		EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
		
		productService.em = em;
		
		Category category = new Category(null, "HighTech");
		
		em.getTransaction().begin();
		em.persist(category);
		em.getTransaction().commit();
		
		eeePad = new Product(null, "eeePad", "An eeePad", BigDecimal.ONE, category);
		
		em.getTransaction().begin();
		em.persist(eeePad);
		em.getTransaction().commit();
	}

}
