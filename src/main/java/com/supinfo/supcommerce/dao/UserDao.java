package com.supinfo.supcommerce.dao;

import com.supinfo.supcommerce.model.User;

public interface UserDao {

	User authenticate(String username, String password);
	User register(User user);
	User findUserByUsername(String username);
	
}
