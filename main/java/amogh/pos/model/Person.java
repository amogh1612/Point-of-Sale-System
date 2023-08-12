package amogh.pos.model;

import java.io.Serializable;

public abstract class Person implements Serializable {

	//Person class, parent class to employee and customer class
	//Instance variables that are common to both employees and customers
	private int id;
	private String name;
	private String email;
	private String address;
	
	public Person (int id, String name, String email, String address) {
		setId(id);
		setName(name);
		setEmail(email);
		setAddress(address);
	}
	
	// Getters and Setters for address
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	// Getters and Setters for name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Getters and Setters for email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + "," + name + "," + email + "," + address;
	}
	
	
	
}
