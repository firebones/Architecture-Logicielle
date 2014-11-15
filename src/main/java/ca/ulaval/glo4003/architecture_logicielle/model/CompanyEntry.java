package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.LinkedList;
import java.util.List;

public class CompanyEntry extends UserEntry {

	private List<DepartmentEntry> departments;

	public CompanyEntry() {
		this.role = RoleUser.COMPANY;
		this.departments = new LinkedList<>();
	}
	
	public void addDepartment(DepartmentEntry department) {
		departments.add(department);
	}
	
	public void removeDepartment(DepartmentEntry department) {
		if (departments.contains(department)) {
			departments.remove(department);
		}
	}
	
	public List<DepartmentEntry> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentEntry> departments) {
		this.departments = departments;
	}
}
