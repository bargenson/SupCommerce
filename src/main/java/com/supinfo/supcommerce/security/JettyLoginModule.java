package com.supinfo.supcommerce.security;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.security.Credential;
import org.eclipse.jetty.plus.jaas.spi.AbstractLoginModule;
import org.eclipse.jetty.plus.jaas.spi.UserInfo;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Admin;
import com.supinfo.supcommerce.model.Customer;
import com.supinfo.supcommerce.model.User;

public class JettyLoginModule extends AbstractLoginModule {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(LoginModule.class);
	
	public static final String ADMIN_ROLE = "ADMIN";
	public static final String CUSTOMER_ROLE = "CUSTOMER";
	
	@Override
	public UserInfo getUserInfo(String username) throws Exception {
		User user = DaoFactory.getDaoFactory().getUserDao().findUserByUsername(username);
		return user != null ? convertUserToUserInfo(user) : null;
	}

	private UserInfo convertUserToUserInfo(User user) {
		Credential credential = Credential.getCredential(user.getEncryptedPassword());
		List<String> roleNames = new ArrayList<String>();
		
		if(user instanceof Admin) roleNames.add(ADMIN_ROLE);
		else if(user instanceof Customer) roleNames.add(CUSTOMER_ROLE);
		else throw new IllegalArgumentException("User is not admin or customer: " + user);
		
		UserInfo result = new UserInfo(
				user.getUsername(),
				credential,
				roleNames
		);
		
		return result;
	}
	
//	private static class CustomUserInfo extends UserInfo {
//
//		public CustomUserInfo(String userName, Credential credential, List<String> roleNames) {
//			super(userName, credential, roleNames);
//		}
//		
//		@Override
//		public boolean checkCredential(Object suppliedCredential) {
//			if(suppliedCredential instanceof String) {
//				String encryptedCredential = encryptPassword((String) suppliedCredential);
//				return super.checkCredential(new Password(encryptedCredential));
//			}
//			return false;
//		}
//		
//		private String encryptPassword(String password) {
//			String result = null;
//			try {
//				result = new String(MessageDigest.getInstance("SHA-1").digest(password.getBytes()));
//			} catch (NoSuchAlgorithmException e) {
//				LOGGER.error(e.getMessage(), e);
//			}
//			return result;
//		}
//		
//	}

}
