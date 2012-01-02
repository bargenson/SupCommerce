package com.supinfo.supcommerce.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.supcommerce.dao.PictureDao;
import com.supinfo.supcommerce.exception.UnknownProductException;

public class JpaPictureDao implements PictureDao {

	private EntityManagerFactory emf;

	public JpaPictureDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public byte[] findPictureByProductId(Long productId) {		
		EntityManager em = emf.createEntityManager();
		try { 
			Query query = em.createQuery("SELECT p.picture FROM Product p WHERE p.id = :id");
			query = query.setParameter("id", productId);
			return (byte[]) query.getSingleResult();
		} catch (NoResultException e) {
			throw new UnknownProductException(productId, e);
		} finally { 
			em.close(); 
		}
	}

}
