package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.LinkedList;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry.Role;

public class DepartmentEntry {
	private String departmentName;
	private List<EmployeeEntry> employees;
	private List<EmployeeEntry> deptManagers;

	public DepartmentEntry(String name) {
		departmentName = name;
		employees = new LinkedList<EmployeeEntry>();
		deptManagers = new LinkedList<EmployeeEntry>();
	}

	public void addDepartmentManager(EmployeeEntry manager) {
		if (manager.getRole() == Role.MANAGER) {
			deptManagers.add(manager);
		}
	}

	public void removeDepartmentManager(EmployeeEntry manager) {
		if (deptManagers.contains(manager)) {
			deptManagers.remove(manager);
		}
	}

	public boolean isManagerInDepartment(EmployeeEntry manager) {
		return deptManagers.contains(manager);
	}

	public void addEmployee(EmployeeEntry employee) {
		employee.setDepartment(this);
		employees.add(employee);
	}

	public void removeEmployee(EmployeeEntry employee) {
		if (employees.contains(employee)) {
			employees.remove(employee);
		}
	}

	public boolean isEmployeeInDepartment(EmployeeEntry employee) {
		return employees.contains(employee);
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String name) {
		departmentName = name;
	}
}
