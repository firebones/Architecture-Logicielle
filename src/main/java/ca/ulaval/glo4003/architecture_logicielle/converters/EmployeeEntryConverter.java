package ca.ulaval.glo4003.architecture_logicielle.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;

@Component
public class EmployeeEntryConverter {

	public List<EmployeeViewModel> convertEmployees(List<EmployeeEntry> employees) {
		List<EmployeeViewModel> viewModels = new ArrayList<EmployeeViewModel>();

		for (EmployeeEntry employee : employees) {
			EmployeeViewModel viewModel = convertEmployee(employee);
			viewModels.add(viewModel);
		}
		return viewModels;
	}
	
	public EmployeeViewModel convertEmployee(EmployeeEntry entry) {
		EmployeeViewModel viewModel = new EmployeeViewModel();
		viewModel.name = entry.getName();
		viewModel.email = entry.getEmail();
		viewModel.tasks = entry.getTasksString();
		return viewModel;
	}
	
	public EmployeeEntry convertEmployee(EmployeeViewModel entry) {
		EmployeeEntry employee = new EmployeeEntry();
		employee.setName(entry.getName());
		employee.setEmail(entry.getEmail());
		employee.setHashedPassword("default");
		return employee;
	}
}
