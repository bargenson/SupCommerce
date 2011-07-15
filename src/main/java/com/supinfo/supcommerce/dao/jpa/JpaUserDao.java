package com.supinfo.supcommerce.dao.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.supcommerce.dao.UserDao;
import com.supinfo.supcommerce.model.User;

public class JpaUserDao implements UserDao {

	private EntityManagerFactory emf;
	
	public JpaUserDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public User authenticate(String username, String password) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
			User user = (User) query.setParameter("username", username).getSingleResult();
			if(user.getEncryptedPassword().equals(encryptPassword(password))) {
				return user;
			}
		} catch (NoResultException e) { 
			// Do nothing
		} finally {
			em.close();
		}
		return null;
	}
	
	private String encryptPassword(String password) {
		String result = null;
		try {
			result = new String(MessageDigest.getInstance("SHA-1").digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User register(User user) {
		User result = null;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			result = user;
		} finally {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			em.close();
		}
		return result;
	}
	
}
