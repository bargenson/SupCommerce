package com.supinfo.supcommerce.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.supinfo.supcommerce.model.Category;
import com.supinfo.supcommerce.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	EntityManager em;
	
	@Override
	public void addCategory(Category category) {
		em.getTransaction().begin();
		try {
			em.persist(category);
			em.getTransaction().commit();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public Category getCategoryById(Long id) {
		return em.find(Category.class, id);
	}

	@Override
	public List<Category> getAllCategories() {
		CriteriaQuery<Category> criteriaQuery = em.getCriteriaBuilder().createQuery(Category.class);
		criteriaQuery.from(Category.class);
		return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void updateCategory(Category category) {
		em.getTransaction().begin();
		try {
			em.merge(category);
			em.getTransaction().commit();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public void removeCategory(Category category) {
		em.getTransaction().begin();
		try {
			em.remove(category);
			em.getTransaction().commit();
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

}
