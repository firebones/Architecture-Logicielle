package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.LinkedList;
import java.util.List;

public class Employee {
	private String  lastName, firstName, email, address;
	private List<String> tasks = new LinkedList<String>();

	public Employee( String lastName, String firstName, String email, String address) {
		super();
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address = address;
	}
	
	public Employee(){}

	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<String> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}
}
