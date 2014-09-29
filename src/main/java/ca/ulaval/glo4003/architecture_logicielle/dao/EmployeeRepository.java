package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.List;

import ca.ulaval.glo4003.architecture_logicielle.model.Employe;


public interface EmployeeRepository {
	public List<Employe> getAllUEmployee();
	public Employe getEmployeByEmail(String email);
	public void addEmployee(Employe employe);
	public void updateEmployee(Employe employe);
	public void deleteEmploye(String email);
	
}
