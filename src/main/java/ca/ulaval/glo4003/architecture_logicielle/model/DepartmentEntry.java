package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.LinkedList;
import java.util.List;

public class DepartmentEntry {
	private String departmentName;
	private List<EmployeeEntry> employees;
	private List<EmployeeEntry> deptManagers;
	private List<WeekEntry> weekEntries;

	public DepartmentEntry() {
		employees = new LinkedList<EmployeeEntry>();
		deptManagers = new LinkedList<EmployeeEntry>();
	}

	public DepartmentEntry(String name) {
		departmentName = name;
		employees = new LinkedList<EmployeeEntry>();
		deptManagers = new LinkedList<EmployeeEntry>();
	}

	public void addDepartmentManager(EmployeeEntry manager) {
		if (!deptManagers.contains(manager) && manager.getRole() == RoleUser.MANAGER) {
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
		if (!employees.contains(employee)) {
			employee.setDepartment(departmentName);
			employees.add(employee);
		}
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

	public List<EmployeeEntry> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntry> employees) {
		this.employees = employees;
	}

	public List<EmployeeEntry> getDeptManagers() {
		return deptManagers;
	}

	public void setDeptManagers(List<EmployeeEntry> managers) {
		this.deptManagers = managers;
	}

	public List<WeekEntry> getSubmittedWeekEntries() {
		List<WeekEntry> submitted = new LinkedList<>();
		for (WeekEntry entry : weekEntries) {
			if (entry.getState() == StateWeekEntry.SUBMITTED) {
				submitted.add(entry);
			}
		}
		return submitted;
	}

	public List<WeekEntry> getApprovedWeekEntries() {
		List<WeekEntry> approved = new LinkedList<>();
		for (WeekEntry entry : weekEntries) {
			if (entry.getState() == StateWeekEntry.APPROVED) {
				approved.add(entry);
			}
		}
		return approved;
	}

	public List<WeekEntry> getRefusedWeekEntries() {
		List<WeekEntry> refused = new LinkedList<>();
		for (WeekEntry entry : weekEntries) {
			if (entry.getState() == StateWeekEntry.REFUSED) {
				refused.add(entry);
			}
		}
		return refused;
	}

	public List<WeekEntry> getInProgressWeekEntries() {
		List<WeekEntry> inProgress = new LinkedList<>();
		for (WeekEntry entry : weekEntries) {
			if (entry.getState() == StateWeekEntry.INPROGRESS) {
				inProgress.add(entry);
			}
		}
		return inProgress;
	}
}
