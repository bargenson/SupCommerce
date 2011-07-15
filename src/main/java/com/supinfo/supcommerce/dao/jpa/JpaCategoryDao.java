package com.supinfo.supcommerce.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	public Category getCategoryById(Long id) {
		EntityManager em = emf.createEntityManager();
		try { return em.find(Category.class, id); } 
		finally { em.close(); }
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
