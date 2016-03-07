package com.vijay.predix.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {

	private int Id = 0;
	private String firstName = "";
	private String lastName = "";
	private String fullName = "";
	
	public int getId() {
		return this.Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
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
	
	public List<Employee> GetAll() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee  = null;
		
		employee = new Employee();
		employee.Id = 1;
		employee.firstName = "Vijay";
		employee.lastName = "Donthireddy";
		employees.add(employee);
		
		employee = new Employee();
		employee.Id = 2;
		employee.firstName = "John";
		employee.lastName = "Brown";
		employees.add(employee);
		
		employee = new Employee();
		employee.Id = 3;
		employee.firstName = "Kelly";
		employee.lastName = "Yellow";
		employees.add(employee);
		
		employee = new Employee();
		employee.Id = 4;
		employee.firstName = "Mike";
		employee.lastName = "Red";
		employees.add(employee);

		return employees;
	}
	
	
}
