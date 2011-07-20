package com.supinfo.supcommerce.model;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("customer")
public class Customer extends User {
	
	@OneToMany(mappedBy="customer")
	private List<DeliveryAddress> deliveryAddresses;
	
	
	public Customer() {
		
	}

	public Customer(String username, String password, String passwordConfirmation, 
			String firstName, String lastName, Date dateOfBirth, String email) {
		
		super(username, password, passwordConfirmation, firstName, 
				lastName, dateOfBirth, email);
	}

	public List<DeliveryAddress> getDeliveryAddresses() {
		return deliveryAddresses;
	}

	public void setDeliveryAddresses(List<DeliveryAddress> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

}
