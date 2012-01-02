package com.supinfo.supcommerce.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

public abstract class JpaAbstractDao<E, K> {
	
	private final EntityManagerFactory emf;
	private final Class<E> entityClass;
	
	protected JpaAbstractDao(EntityManagerFactory emf) {
		this.emf = emf;
		this.entityClass = getEntityClass();
	}
	
	@SuppressWarnings("unchecked")
	private Class<E> getEntityClass() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<E>) genericSuperclass.getActualTypeArguments()[0];
	}
	
	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	protected void rollbackIfStillActive(EntityTransaction transaction) {
		if(transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	protected E persist(E entity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(entity);
			em.getTransaction().commit();
		} finally {
			rollbackIfStillActive(em.getTransaction());
			em.close();
		}
		 return entity;
	}
	
	protected void remove(E entity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		try {
			em.remove(em.merge(entity));
			em.getTransaction().commit();
		} finally {
			rollbackIfStillActive(em.getTransaction());
			em.close();
		}
	}
	
	protected void update(E entity) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(entity);
			em.getTransaction().commit();
		} finally {
			rollbackIfStillActive(em.getTransaction());
			em.close();
		}
	}
	
	protected E findById(K id) {
		EntityManager em = getEntityManager();
		try { 
			return em.find(entityClass, id);
		} finally {
			em.close(); 
		}
	}
	
	protected List<E> findAll() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery<E> criteriaQuery = em.getCriteriaBuilder().createQuery(entityClass);
			criteriaQuery.from(entityClass);
			return em.createQuery(criteriaQuery).getResultList();
		} finally {
			em.close();
		}
	}

}
