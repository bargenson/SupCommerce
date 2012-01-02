package com.supinfo.supcommerce.exception;

public class BadCredentialsException extends RuntimeException {
	
	public BadCredentialsException(String username, String password) {
		super("Bad credentials: " + username + " / " + password);
	}

}
