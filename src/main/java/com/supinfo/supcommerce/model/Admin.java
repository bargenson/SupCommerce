package com.supinfo.supcommerce.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

	public Admin() {
		
	}

	public Admin(String username, String password, String passwordConfirmation,
			String firstName, String lastName, Date dateOfBirth, String email) {
		
		super(username, password, passwordConfirmation, 
				firstName, lastName, dateOfBirth, email);
	}
	
	
	
}
