package com.supinfo.supcommerce.exception;

public class UnknownProductException extends RuntimeException {
	
	public UnknownProductException(Long productId) {
        this(productId, null);
    }

    public UnknownProductException(Long productId, Throwable cause) {
        super("Unknown product with id: " + productId + ".", cause);
    }

}
