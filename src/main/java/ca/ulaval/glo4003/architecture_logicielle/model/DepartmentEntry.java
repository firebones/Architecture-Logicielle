package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.LinkedList;
import java.util.List;

public class DepartmentEntry {
	private String departmentName;
	private List<EmployeeEntry> employees;
	private List<DeptManagerEntry> deptManagers;

	public DepartmentEntry(String name) {
		departmentName = name;
		employees = new LinkedList<EmployeeEntry>();
		deptManagers = new LinkedList<DeptManagerEntry>();
	}

	public void addDepartmentManager(DeptManagerEntry manager) {
		deptManagers.add(manager);
	}

	public void removeDepartmentManager(DeptManagerEntry manager) {
		if (deptManagers.contains(manager)) {
			deptManagers.remove(manager);
		}
	}

	public boolean isManagerInDepartment(DeptManagerEntry manager) {
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
