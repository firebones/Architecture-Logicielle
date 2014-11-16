package ca.ulaval.glo4003.architecture_logicielle.model;

import java.util.ArrayList;

public interface DepartmentRepository {
	public ArrayList<DepartmentEntry> getAllDepartment();
	public DepartmentEntry getDepartmentByName(String name);
	public void addDepartment(DepartmentEntry department);
	public void deleteDepartment(DepartmentEntry department);
	public void addEmployeeToDepartment(String departmentName, String employeeEmail);
	public void removeEmployeeFromDepartment(String departmentName, String employeeEmail);
	public void addManagerToDepartment(String departmentName, String managerEmail);
	public void removeManagerFromDepartment(String departmentName, String managerEmail);
	public void updateDepartment(DepartmentEntry department);
}
