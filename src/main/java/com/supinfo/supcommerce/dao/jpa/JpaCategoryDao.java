package com.supinfo.supcommerce.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.exception.UnknownCategoryException;
import com.supinfo.supcommerce.model.Category;

public class JpaCategoryDao extends JpaAbstractDao<Category, Long> implements CategoryDao {
	
	public JpaCategoryDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public void addCategory(Category category) {
		persist(category);
	}

	@Override
	public Category findCategoryById(Long categoryId) {
		try {
			return findById(categoryId);
		} catch (NoResultException e) {
			throw new UnknownCategoryException(categoryId, e);
		}
	}
	
	@Override
	public Category getCategoryByIdWithProducts(Long categoryId) {
		EntityManager em = getEntityManager();
		try { 
			Query query = em.createQuery("SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.id = :id");
			query.setParameter("id", categoryId);
			return (Category) query.getSingleResult();
		} catch (NoResultException e) {
			throw new UnknownCategoryException(categoryId, e);
		} finally { 
			em.close(); 
		}
	}

	@Override
	public List<Category> getAllCategories() {
		return findAll();
	}

	@Override
	public void updateCategory(Category category) {
		update(category);
	}

	@Override
	public void removeCategory(Category category) {
		remove(category);
	}

}
