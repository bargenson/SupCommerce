package com.supinfo.supcommerce.constraints;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.management.ReflectionException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.util.ReflectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordConfirmationValidator.class); 
	
	private String passwordFieldName;
    private String confirmationFieldName;
    private String encryptedFieldName;
    
	@Override
	public void initialize(PasswordConfirmation annotation) {
		passwordFieldName = annotation.password();
		confirmationFieldName = annotation.confirmation();
		encryptedFieldName = annotation.encrypted();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		final Field passwordField = 
				ReflectionHelper.getDeclaredField(findClassInHierarchyByFieldName(value.getClass(), passwordFieldName), passwordFieldName);
		final String passwordValue = (String) ReflectionHelper.getValue(passwordField, value);
		
		final Field confirmationField = 
				ReflectionHelper.getDeclaredField(findClassInHierarchyByFieldName(value.getClass(), confirmationFieldName), confirmationFieldName);
		final String confirmationValue = (String) ReflectionHelper.getValue(confirmationField, value);
        
		final Field encryptedField = 
				ReflectionHelper.getDeclaredField(findClassInHierarchyByFieldName(value.getClass(), encryptedFieldName), encryptedFieldName);
        final String encryptedValue = (String) ReflectionHelper.getValue(encryptedField, value);
                
    	try {    		
    		if(encryptedValue == null || passwordValue != null) {
    			if(passwordValue != null && passwordValue.equals(confirmationValue)) {
    				try {
    					encryptedField.set(value, encryptPassword(passwordValue));
    				} catch (Exception e) {
    					throw new ReflectionException(e, "Impossible to generate encrypted password.");
    				}
    				return true;
    			}
    			return false;
    		}
			return true;
		} catch (ReflectionException e) {
			LOGGER.error(e.getMessage(), e);
		}
        
        return false;
	}

	private String encryptPassword(String password) {
		String result = null;
		try {
			result = new String(MessageDigest.getInstance("SHA-1").digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}
	
	private Class<?> findClassInHierarchyByFieldName(Class<?> clazz, String fieldName) {
		if(clazz == null) return null;
		if(ReflectionHelper.containsDeclaredField(clazz, fieldName)) {
			return clazz;
		}
		return findClassInHierarchyByFieldName(clazz.getSuperclass(), fieldName);
	}

}
