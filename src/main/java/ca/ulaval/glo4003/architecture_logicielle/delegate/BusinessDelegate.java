package ca.ulaval.glo4003.architecture_logicielle.delegate;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.ulaval.glo4003.architecture_logicielle.dao.EmployeeRepositoryImp;
import ca.ulaval.glo4003.architecture_logicielle.model.Employee;

//@Configuration
public class BusinessDelegate {
	
	//@Bean
	public void addEmployee(Employee employee) throws Exception{
		new EmployeeRepositoryImp().addEmployee(employee);
	}
	
	//@Bean
	public List<Employee> getAllEmployees(){
		return new EmployeeRepositoryImp().getAllEmployees();
	}


}
