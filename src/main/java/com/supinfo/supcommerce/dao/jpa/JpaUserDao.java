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
		User result = null;
		
		EntityManager em = emf.createEntityManager();
		try {
			User user = findUserByUsername(username);
			if(user != null && user.getEncryptedPassword().equals(encryptPassword(password))) {
				result = user;
			}
		} catch (NoResultException e) { 
			// Do nothing, result is already null
		} finally {
			em.close();
		}
		
		return result;
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

	@Override
	public User findUserByUsername(String username) {
		User result;
		
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
			result = (User) query.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) { 
			result = null;
		} finally {
			em.close();
		}
		
		return result;
	}
	
}
