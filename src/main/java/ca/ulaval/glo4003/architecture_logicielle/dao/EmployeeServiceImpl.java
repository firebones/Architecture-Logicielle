package ca.ulaval.glo4003.architecture_logicielle.dao;

import ca.ulaval.glo4003.architecture_logicielle.model.Employe;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;

	@Override
	public void addEmployee(Employe employe) {
		employeeRepository.addEmployee(employe);
		
	}

}
