package com.vijay.predix.entity;

public class Employee {
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	private String firstName = "";
	private String lastName = "";
	private String fullName = "";
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}	
	
	public String setFullName(String fullName) {
		return this.fullName = fullName;
	}	
}
