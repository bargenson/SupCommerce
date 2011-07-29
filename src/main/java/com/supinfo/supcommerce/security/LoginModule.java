package com.supinfo.supcommerce.security;

import java.util.ArrayList;
import java.util.List;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.model.Admin;
import com.supinfo.supcommerce.model.Customer;
import com.supinfo.supcommerce.model.User;
import com.supinfo.utils.security.AbstractLoginModule;
import com.supinfo.utils.security.GenericUserInfo;
import com.supinfo.utils.security.UserInfo;

public class LoginModule extends AbstractLoginModule {
	
	public static final String ADMIN_ROLE = "ADMIN";
	public static final String CUSTOMER_ROLE = "CUSTOMER";
	
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
		
		UserInfo result = new GenericUserInfo(
				user.getUsername(),
				user.getEncryptedPassword().toCharArray(),
				roleNames
		);
		
		return result;
	}
	
}
