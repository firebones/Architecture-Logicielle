package ca.ulaval.glo4003.architecture_logicielle.web.viewmodels;

import java.util.List;
import java.util.ArrayList;


public class EmployeeViewModel {

		
	public String email;
	public String name;
	public String roleUser;
	public String hashedPassword;
	public String rateHour; 
	public String companyName;
	public String departmentName;
	public List<String> tasks = new ArrayList<String>();
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTasks() {
		return tasks;
	}
	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}
	public String getRateHour() {
		return rateHour;
	}
	public void setRateHour(String rateHour) {
		this.rateHour = rateHour;
	}
	public String getRoleUser() {
		return roleUser;
	}
	public void setRoleUser(String role) {
		this.roleUser = role;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getCompany() {
		return companyName;
	}

	public void setCompany(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartment() {
		return departmentName;
	}

	public void setDepartment(String departmentName) {
		this.departmentName = departmentName;
	}
}
