package com.supinfo.supcommerce.dao.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.supcommerce.dao.UserDao;
import com.supinfo.supcommerce.exception.BadCredentialsException;
import com.supinfo.supcommerce.exception.UnknownUserException;
import com.supinfo.supcommerce.model.User;

public class JpaUserDao extends JpaAbstractDao<User, Long> implements UserDao {

	private EntityManagerFactory emf;
	
	public JpaUserDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public User authenticate(String username, String password) {
		try {
			User user = findUserByUsername(username);
			if(user.getEncryptedPassword().equals(encryptPassword(password))) {
				return user;
			}
		} catch (NoResultException e) { /* Do nothing, an exception will be thrown */ }
		
		throw new BadCredentialsException(username, password);
	}
	
	private String encryptPassword(String password) {
		try {
			return new String(MessageDigest.getInstance("SHA-1").digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public User register(User user) {
		return persist(user);
	}

	@Override
	public User findUserByUsername(String username) {		
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
			query.setParameter("username", username);
			return (User) query.getSingleResult();
		} catch (NoResultException e) { 
			throw new UnknownUserException(username, e);
		} finally {
			em.close();
		}
	}
	
}
