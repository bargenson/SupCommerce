package com.supinfo.supcommerce.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.supinfo.supcommerce.model.Category;

public class CategoryServiceImplTest {
	
	private CategoryServiceImpl categoryService = new CategoryServiceImpl();
	
	private Category bookCategory = new Category(null, "Book");
	
	
	@Before
	public void setUp() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		categoryService.em = em;
		
		em.getTransaction().begin();
		em.persist(bookCategory);
		em.getTransaction().commit();
	}
	
	@After
	public void setDown() {
		categoryService.em.getEntityManagerFactory().close();
		categoryService.em.close();
	}
	
	@Test
	public void shouldPersistAndRetrieveCategory() {
		Category category = new Category(null, "HIFI");
		
		categoryService.addCategory(category);
		
		assertNotNull(category.getId());
		
		Category retrievedCategory = categoryService.getCategoryById(category.getId());
		
		assertEquals(category, retrievedCategory);
	}
	
	@Test
	public void shouldRemoveCategory() {
		Category retrievedCategory = categoryService.getCategoryById(bookCategory.getId());
		
		assertNotNull(retrievedCategory);
		
		categoryService.removeCategory(bookCategory);
		
		retrievedCategory = categoryService.getCategoryById(bookCategory.getId());
		
		assertNull(retrievedCategory);
	}
	
	@Test
	public void shouldUpdateCategory() {
		bookCategory.setName("HIFI");
		
		categoryService.updateCategory(bookCategory);
		
		Category retrievedCategory = categoryService.getCategoryById(bookCategory.getId());
		
		assertNotNull(retrievedCategory);
		assertEquals(bookCategory.getName(), retrievedCategory.getName());
	}
	
	@Test
	public void shouldPersistAndRetrieveAllCategories() {		
		List<Category> retrievedCategories = categoryService.getAllCategories();
		
		assertNotNull(retrievedCategories);
		assertEquals(1, retrievedCategories.size());
		assertEquals(bookCategory, retrievedCategories.get(0));
		
		Category dvdCategory = new Category(null, "DVD");
		
		categoryService.addCategory(dvdCategory);
		
		retrievedCategories = categoryService.getAllCategories();
		
		assertEquals(2, retrievedCategories.size());
		assertTrue(retrievedCategories.contains(dvdCategory));
		assertTrue(retrievedCategories.contains(bookCategory));
	}

}
