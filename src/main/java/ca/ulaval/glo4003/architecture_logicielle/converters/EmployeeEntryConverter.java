package ca.ulaval.glo4003.architecture_logicielle.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import ca.ulaval.glo4003.architecture_logicielle.model.Employee;
import ca.ulaval.glo4003.architecture_logicielle.web.viewmodels.EmployeeViewModel;

@Component
public class EmployeeEntryConverter {

	public List<EmployeeViewModel> convertEmployees(List<Employee> employees) {
		List<EmployeeViewModel> viewModels = new ArrayList<EmployeeViewModel>();

		for (Employee employee : employees) {
			EmployeeViewModel viewModel = convertEmployee(employee);
			viewModels.add(viewModel);
		}
		return viewModels;
	}
	
	public EmployeeViewModel convertEmployee(Employee entry) {
		EmployeeViewModel viewModel = new EmployeeViewModel();
		viewModel.lastName = entry.getLastName().toUpperCase();
		viewModel.firstName = entry.getFirstName();
		viewModel.email = entry.getEmail();
		viewModel.tasks = entry.getTasks();
		return viewModel;
	}
}
