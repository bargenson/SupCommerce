package com.supinfo.supcommerce.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Admin;
import com.supinfo.supcommerce.model.Customer;
import com.supinfo.supcommerce.model.User;

import fr.bargenson.utils.security.AbstractLoginModule;
import fr.bargenson.utils.security.GenericUserInfo;
import fr.bargenson.utils.security.UserInfo;

public class LoginModule extends AbstractLoginModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginModule.class);
	
	public static final String ADMIN_ROLE 	 = "admin";
	public static final String CUSTOMER_ROLE = "customer";
	
	@Override
	public UserInfo getUserInfo(String username) throws Exception {
		User user = DaoFactory.getDaoFactory().getUserDao().findUserByUsername(username);
		return user != null ? convertUserToUserInfo(user) : null;
	}

	private UserInfo convertUserToUserInfo(User user) {
		List<String> roleNames = new ArrayList<String>();
		
		if(user instanceof Admin) roleNames.add(ADMIN_ROLE);
		else if(user instanceof Customer) roleNames.add(CUSTOMER_ROLE);
		else throw new IllegalArgumentException("User is not admin or customer: " + user);
		
		UserInfo result = new MyUserInfo(
				user.getUsername(),
				user.getEncryptedPassword().toCharArray(),
				roleNames
		);
		
		return result;
	}
	
	private static char[] encryptPassword(char[] password) {
		char[] result = null;
		try {
			result = new String(
					MessageDigest
						.getInstance("SHA-1")
						.digest(new String(password).getBytes())
			).toCharArray();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
	
	private static class MyUserInfo extends GenericUserInfo {

		public MyUserInfo(String username, char[] password,
				List<String> roleNames) {
			
			super(username, password, roleNames);
		}
		
		@Override
		public boolean checkPassword(char[] suppliedPassword) {
			return super.checkPassword(encryptPassword(suppliedPassword));
		}
		
	}
	
}
