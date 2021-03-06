package edu.mum.coffee.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty(message = "FistName must be not empty")
	private String firstName;
	@NotEmpty(message = "lastName must be not empty")
	private String lastName;
	
	@NotEmpty(message= "Email must be not empty")
	@Email
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	private Address address;
	
	@NotEmpty(message="PhoneNumber must be not empty")
	@Pattern(regexp="^\\(?([0-9]{3})\\)? ([0-9]{3})\\-([0-9]{4})", 
	message = "Phone Number must match regex (xxx) xxx-xxxx")
	private String phone;
	private boolean enable;

	public long getId() {
		return id;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
