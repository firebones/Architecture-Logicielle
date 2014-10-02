package ca.ulaval.glo4003.architecture_logicielle.delegate;

import ca.ulaval.glo4003.architecture_logicielle.dao.EmployeeRepositoryImp;
import ca.ulaval.glo4003.architecture_logicielle.model.Employe;

public class BusinessDelegate {
	
	public void addEmployee(Employe employe){
		new EmployeeRepositoryImp().addEmployee(employe);
	}

}
