package ca.ulaval.glo4003.architecture_logicielle.web.converters;

import java.util.ArrayList;
//import java.util.Collection;
//import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.RoleUser;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;

@Component
public class EmployeeEntryConverter {

	public List<EmployeeViewModel> toEmployees(List<EmployeeEntry> employees) {
		List<EmployeeViewModel> viewModels = new ArrayList<EmployeeViewModel>();

		for (EmployeeEntry employee : employees) {
			EmployeeViewModel viewModel = toEmployeeViewModel(employee);
			viewModels.add(viewModel);
		}
		return viewModels;
	}
	
	public EmployeeViewModel toEmployeeViewModel(EmployeeEntry entry) {
		EmployeeViewModel viewModel = new EmployeeViewModel();
		viewModel.name = entry.getName();
		viewModel.email = entry.getEmail();
		viewModel.hashedPassword = entry.getHashedPassword();
		viewModel.rateHour = entry.getRateHour().toString();
		viewModel.roleUser = entry.getRole().toString();
		viewModel.companyName = entry.getCompany();
		viewModel.departmentName = entry.getDepartment();
		viewModel.tasks = entry.getTasksString();
		
		return viewModel;
	}
	
	public EmployeeEntry toEmployee(EmployeeViewModel entry) {
		EmployeeEntry employee = new EmployeeEntry();
		employee.setName(entry.getName());
		employee.setEmail(entry.getEmail());
		employee.setHashedPassword("default");
//		employee.setRateHour(Double.parseDouble(entry.getRateHour()));
//		employee.setCompany(entry.getCompany());
//		employee.setDepartment(entry.getDepartment());
		return employee;
	}
	
	public EmployeeEntry toExistEmployee(EmployeeViewModel entry) {
		EmployeeEntry employee = new EmployeeEntry();
		employee.setName(entry.getName());
		employee.setEmail(entry.getEmail());
		employee.setHashedPassword(entry.getHashedPassword());
		employee.setRateHour(Double.parseDouble(entry.getRateHour()));
		employee.setCompany(entry.getCompany());
		employee.setDepartment(entry.getDepartment());
		if(entry.getRoleUser() == "EMPLOYEE")
			employee.setRoleUser(RoleUser.EMPLOYEE);
		if(entry.getRoleUser() == "MANAGER")
			employee.setRoleUser(RoleUser.MANAGER);
		return employee;
	}
}
