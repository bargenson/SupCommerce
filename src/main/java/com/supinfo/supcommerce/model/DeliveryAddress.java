package com.supinfo.supcommerce.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
public class DeliveryAddress implements Serializable {

	@Id @GeneratedValue
	private Long id;

	@NotEmpty @NotBlank
	private String firstName;
	
	@NotEmpty @NotBlank
	private String lastName;
	
	@NotNull @Range(min=1)
	private Integer streetNumber;
	
	@NotEmpty @NotBlank
	private String streetName;
	
	@NotNull @Range(min=10000, max=99999)
	private Integer zipCode;
	
	@NotEmpty @NotBlank
	private String city;
	
	@ManyToOne @JoinColumn
	private Customer customer;
	
	
	public DeliveryAddress() {
		
	}

	public DeliveryAddress(String firstName, String lastName,
			Integer streetNumber, String streetName, Integer zipCode,
			String city, Customer customer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.zipCode = zipCode;
		this.city = city;
		this.customer = customer;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
