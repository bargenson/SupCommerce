package com.supinfo.supcommerce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.supinfo.supcommerce.constraints.PasswordConfirmation;

@Entity
@DiscriminatorColumn
@PasswordConfirmation(password="password", confirmation="passwordConfirmation", encrypted="encryptedPassword")
public abstract class User implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	
	@NotEmpty @NotBlank
	@Column(unique=true)
	private String username;
	
	@Size(min=1)
	private String encryptedPassword;
	
	@Transient @Length(min=6)
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	@NotEmpty @NotBlank
	private String firstName;
	
	@NotEmpty @NotBlank
	private String lastName;
	
	@Past @NotNull
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Email @NotEmpty
	private String email;
	
	
	public User() { }

	public User(String username, String password, String passwordConfirmation, 
			String firstName, String lastName, Date dateOfBirth, String email) {
		
		this.username = username;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
}
