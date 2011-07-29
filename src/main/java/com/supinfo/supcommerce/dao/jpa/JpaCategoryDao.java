package com.supinfo.supcommerce.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.model.Category;

public class JpaCategoryDao implements CategoryDao {

	private EntityManagerFactory emf;
	
	public JpaCategoryDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public void addCategory(Category category) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(category);
			em.getTransaction().commit();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	@Override
	public Category findCategoryById(Long id) {		
		Category result;
		
		EntityManager em = emf.createEntityManager();
		try { 
			result = em.find(Category.class, id);
		} catch (NoResultException e) {
			result = null;
		} finally {
			em.close(); 
		}
		
		return result;
	}
	
	@Override
	public Category getCategoryByIdWithProducts(Long id) {
		EntityManager em = emf.createEntityManager();
		try { 
			Query query = em.createQuery("SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.id = :id");
			query.setParameter("id", id);
			return (Category) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally { 
			em.close(); 
		}
	}

	@Override
	public List<Category> getAllCategories() {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaQuery<Category> criteriaQuery = em.getCriteriaBuilder().createQuery(Category.class);
			criteriaQuery.from(Category.class);
			return em.createQuery(criteriaQuery).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public void updateCategory(Category category) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(category);
			em.getTransaction().commit();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

	@Override
	public void removeCategory(Category category) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.remove(em.merge(category));
			em.getTransaction().commit();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}

}
