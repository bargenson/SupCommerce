package com.supinfo.supcommerce.exception;

public class UnknownCategoryException extends RuntimeException {
	
	public UnknownCategoryException(Long categoryId) {
        this(categoryId, null);
    }

    public UnknownCategoryException(Long categoryId, Throwable cause) {
        super("Unknown category with id: " + categoryId + ".", cause);
    }

}
