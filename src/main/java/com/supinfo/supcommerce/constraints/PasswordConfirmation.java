package com.supinfo.supcommerce.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConfirmationValidator.class)
@Documented
public @interface PasswordConfirmation {
	
	String message() default "Password and confirmation password are invalid.";
	
	String password();
	String confirmation();
		
	String encrypted();
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	
}
