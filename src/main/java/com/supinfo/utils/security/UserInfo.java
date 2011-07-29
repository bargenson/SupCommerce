package com.supinfo.utils.security;

import java.util.List;

public interface UserInfo {
	
	String getUsername();

	boolean checkPassword(char[] suppliedPassword);
    
	char[] getPassword();

	List<String> getRoleNames();

}
