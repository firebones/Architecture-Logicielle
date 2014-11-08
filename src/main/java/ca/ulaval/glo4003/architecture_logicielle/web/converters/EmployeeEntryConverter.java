package ca.ulaval.glo4003.architecture_logicielle.web.converters;

import java.util.ArrayList;
//import java.util.Collection;
//import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
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
		viewModel.tasks = entry.getTasksString();
		return viewModel;
	}
	
	public EmployeeEntry toEmployee(EmployeeViewModel entry) {
		EmployeeEntry employee = new EmployeeEntry();
		employee.setName(entry.getName());
		employee.setEmail(entry.getEmail());
		employee.setHashedPassword("default");
		return employee;
	}
}
