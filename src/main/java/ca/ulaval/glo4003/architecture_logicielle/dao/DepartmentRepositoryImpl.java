package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.DepartmentEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.DepartmentRepository;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserRepository;

public class DepartmentRepositoryImpl implements DepartmentRepository
{
	XMLDepartmentPersistance xmlDepartmentPersistance;
	
	public DepartmentRepositoryImpl(){
		xmlDepartmentPersistance = XMLDepartmentPersistance.getInstance();
	}
	
	@Override
	public ArrayList<DepartmentEntry> getAllDepartment() {
		
		ArrayList<DepartmentEntry> departements = new ArrayList<DepartmentEntry>();
		
		ArrayList<ArrayList<String>> departmentList = xmlDepartmentPersistance.getAllDepartement();
		UserRepository userRepositoty = new UserRepositoryImpl();
		
		for(ArrayList<String> tabDepartment: departmentList){
			
			DepartmentEntry department = new DepartmentEntry();
			department.setDepartmentName(tabDepartment.get(0));
			
			int i = 2;
			if(tabDepartment.get(i) == "listEmployee") {
				i++;
				while (tabDepartment.get(i) != "listManager" && tabDepartment.get(i) != "fin") {
					UserEntry user =  userRepositoty.getUserByEmail(tabDepartment.get(i));
					department.addEmployee((EmployeeEntry) user);
					i++;
				}
			}
			if(tabDepartment.get(i) == "listManager") {
				i++;
				while (tabDepartment.get(i) != "fin") {
					UserEntry user =  userRepositoty.getUserByEmail(tabDepartment.get(i));
					department.addDepartmentManager((EmployeeEntry) user);
					i++;
				}
			}
			
			departements.add(department);
		}

		return departements;
	}
	
	@Override
	public DepartmentEntry getDepartmentByName(String name) {
		
		ArrayList<DepartmentEntry> departments = getAllDepartment();
		for (int i = 0; i < departments.size(); i++) {
			if (departments.get(i).getDepartmentName().equals(name)) {
				return departments.get(i);
			}
		}
		return null;
	}
	
	@Override
	public void addDepartment(DepartmentEntry department) {
	
		if (getDepartmentByName(department.getDepartmentName()) == null){
			
			ArrayList<String> departmentelement = getDepartmentString(department);
			xmlDepartmentPersistance.addDepartment(departmentelement);
		}
	}
	

	@Override
	public void deleteDepartment(DepartmentEntry department) {
		
		if (getDepartmentByName(department.getDepartmentName()) != null){
			
			xmlDepartmentPersistance.deleteDepartment(department.getDepartmentName());	
		}
	}
	
	@Override
	public void addEmployeeToDepartment(String departmentName, String employeeEmail) {
		DepartmentEntry department = getDepartmentByName(departmentName);
		UserRepository userRepository = new UserRepositoryImpl();
		EmployeeEntry user = (EmployeeEntry) userRepository.getUserByEmail(employeeEmail);
		
		if (department != null && user != null && !department.isEmployeeInDepartment(user)) {
			department.addEmployee(user);
			xmlDepartmentPersistance.updateDepartment(getDepartmentString(department));	
		}
	}
	
	@Override
	public void removeEmployeeFromDepartment(String departmentName, String employeeEmail) {
		DepartmentEntry department = getDepartmentByName(departmentName);
		UserRepository userRepository = new UserRepositoryImpl();
		EmployeeEntry user = (EmployeeEntry) userRepository.getUserByEmail(employeeEmail);
		
		if (department != null && user != null && department.isEmployeeInDepartment(user)) {
			department.removeEmployee(user);
			xmlDepartmentPersistance.updateDepartment(getDepartmentString(department));	
		}
	}
	
	@Override
	public void addManagerToDepartment(String departmentName, String managerEmail) {
		DepartmentEntry department = getDepartmentByName(departmentName);
		UserRepository userRepository = new UserRepositoryImpl();
		EmployeeEntry user = (EmployeeEntry) userRepository.getUserByEmail(managerEmail);
		
		if (department != null && user != null && !department.isManagerInDepartment(user)) {
			department.addDepartmentManager(user);
			xmlDepartmentPersistance.updateDepartment(getDepartmentString(department));	
		}
	}
	
	@Override
	public void removeManagerFromDepartment(String departmentName, String managerEmail) {
		DepartmentEntry department = getDepartmentByName(departmentName);
		UserRepository userRepository = new UserRepositoryImpl();
		EmployeeEntry user = (EmployeeEntry) userRepository.getUserByEmail(managerEmail);
		
		if (department != null && user != null && department.isManagerInDepartment(user)) {
			department.removeDepartmentManager(user);
			xmlDepartmentPersistance.updateDepartment(getDepartmentString(department));	
		}
	}
	
	@Override
	public void updateDepartment(DepartmentEntry department){
		if (getDepartmentByName(department.getDepartmentName()) != null){
			
			ArrayList<String> departmentElement = getDepartmentString(department);
			xmlDepartmentPersistance.updateDepartment(departmentElement);
		}
	}

	private ArrayList<String> getDepartmentString(DepartmentEntry department)
	{
		ArrayList<String> departmentElement = new ArrayList<String>();
		List<EmployeeEntry> listEmployee;
		List<EmployeeEntry> listManager;
		
		departmentElement.add(0, department.getDepartmentName());
		departmentElement.add(1, "");
		departmentElement.add(2, "listEmployee");
		listEmployee = department.getEmployees();
		
		int j=3;
		if (listEmployee.size() > 0) {
			for (int i = 0; i < listEmployee.size(); i++) {
				String email = department.getEmployees().get(i).getEmail();
				departmentElement.add(j, email);
				j++;
			}
		}
		
		departmentElement.add(j, "listManager");
		listManager = department.getDeptManagers();
		j++;
		
		if (listManager.size() > 0) {
			for (int i = 0; i < listManager.size(); i++) {
				String email = department.getDeptManagers().get(i).getEmail();
				departmentElement.add(j, email);
				j++;
			}
		}
		departmentElement.add(j, "fin");
		
		return departmentElement;
	}
}
