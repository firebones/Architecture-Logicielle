package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.DepartmentEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.DepartmentRepository;

public class DepartmentRepositoryImpl implements DepartmentRepository
{
	XMLDepartmentPersistance xmlDepartmentPersistance;
	
	public DepartmentRepositoryImpl(){
		xmlDepartmentPersistance = XMLDepartmentPersistance.getInstance();
	}
	
	@Override
	public ArrayList<DepartmentEntry> getAllDepartment() {
		return null;
	}
	
	@Override
	public DepartmentEntry getDepartmentByName(String name) {
		return null;
	}
	
	@Override
	public void addDepartment(DepartmentEntry department) {
		
	}
	
	@Override
	public void deleteDepartment(String name) {
		
	}
	
	@Override
	public void addEmployeeToDepartment(String departmentName, String employeeName) {
		
	}
	
	@Override
	public void removeEmployeeFromDepartment(String departmentName, String employeeName) {
		
	}
	
	@Override
	public void addManagerToDepartment(String departmentName, String managerName) {
		
	}
	
	@Override
	public void removeManagerFromDepartment(String departmentName, String managerName) {
		
	}
	
	@Override
	public void updateDepartment(String departmentName, DepartmentEntry department) {
		
	}

}
