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
		
		ArrayList<ArrayList<String>> departmentlist = xmlDepartmentPersistance.getAllDepartement();
		UserRepository userRepositoty = new UserRepositoryImpl();
		
		for(ArrayList<String> tabdepartment: departmentlist){
			
			DepartmentEntry department = new DepartmentEntry();
			department.setDepartmentName(tabdepartment.get(0));
			
			if(tabdepartment.size() > 2){
				int j=2;
				do{
					UserEntry user =  userRepositoty.getUserByEmail(tabdepartment.get(j));
					if(user.getRole().getRoleUserEntry() == "MANAGER")
						department.addDepartmentManager((EmployeeEntry) user);
					if(user.getRole().getRoleUserEntry() == "EMPLOYEE")
						department.addEmployee((EmployeeEntry) user);
					j++;
				}while(j<tabdepartment.size());
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
			
			xmlDepartmentPersistance.deleteDepartement(department.getDepartmentName());	
		}
	}
	
/*	@Override
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
		
	}*/
	
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
		if ( listEmployee.size() > 0) {
			int i=0;
			do{
				String email = department.getEmployees().get(i).getEmail();
				departmentElement.add(j, email);
				j++;
				i++;
			}while(i<listEmployee.size());
			
		}
		
		departmentElement.add(j, "listManager");
		listManager = department.getDeptManagers();
		
		if ( listManager.size() > 0) {
			int i=0;
			do{
				String email = department.getDeptManagers().get(i).getEmail();
				departmentElement.add(j, email);
				j++;
				i++;
			}while(i<listEmployee.size());
		}
		departmentElement.add(j, "fin");
		
		return departmentElement;
	}
}
