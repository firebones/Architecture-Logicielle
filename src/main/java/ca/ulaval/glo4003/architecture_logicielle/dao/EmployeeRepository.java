package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.util.List;

import org.w3c.dom.Element;

import ca.ulaval.glo4003.architecture_logicielle.model.Employee;


public interface EmployeeRepository {
	public List<Employee> getAllEmployees();
	public Employee getEmployeByEmail(String email);
	public Employee getEmployee(Element element);
	public void addEmployee(Employee employe)throws Exception;
	public void updateEmployee(Employee employe);
	public void deleteEmployee(String email);
	
}
