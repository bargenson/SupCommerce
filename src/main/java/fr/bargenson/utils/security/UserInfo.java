package fr.bargenson.utils.security;

import java.util.List;

public interface UserInfo {
	
	String getUsername();

	boolean checkPassword(char[] suppliedPassword);
    
	char[] getPassword();

	List<String> getRoleNames();

}
