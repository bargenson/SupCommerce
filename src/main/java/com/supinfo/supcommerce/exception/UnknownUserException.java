package com.supinfo.supcommerce.exception;

public class UnknownUserException extends RuntimeException {
	
	public UnknownUserException(String username) {
        this(username, null);
    }

    public UnknownUserException(String username, Throwable cause) {
        super("Unknown User with username: " + username + ".", cause);
    }
    
    public UnknownUserException(Long userId) {
    	this(userId, null);
    }
    
    public UnknownUserException(Long userId, Throwable cause) {
    	super("Unknown User with id: " + userId + ".", cause);
    }

}
