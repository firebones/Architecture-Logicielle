package ca.ulaval.glo4003.architecture_logicielle.delegate;

import ca.ulaval.glo4003.architecture_logicielle.dao.EmployeeRepositoryImp;
import ca.ulaval.glo4003.architecture_logicielle.model.Employee;

public class BusinessDelegate {
	
	public void addEmployee(Employee employe) throws Exception{
		new EmployeeRepositoryImp().addEmployee(employe);
	}

}
