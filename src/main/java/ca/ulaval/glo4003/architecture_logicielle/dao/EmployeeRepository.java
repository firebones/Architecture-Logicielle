package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.List;

import org.w3c.dom.Element;

import ca.ulaval.glo4003.architecture_logicielle.model.Employe;


public interface EmployeeRepository {
	public List<Employe> getAllEmployees();
	public Employe getEmployeByEmail(String email);
	public Employe getEmployee(Element element);
	public void addEmployee(Employe employe);
	public void updateEmployee(Employe employe);
	public void deleteEmployee(String email);
	
}
