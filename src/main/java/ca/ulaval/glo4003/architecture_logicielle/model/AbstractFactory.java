package ca.ulaval.glo4003.architecture_logicielle.model;

public abstract class AbstractFactory
{

	public static AbstractFactory createFactory(String role){
		AbstractFactory factory;	
		
		switch(role){
			case "EMPLOYEE": factory = new EmployeeFactory();
			break;
			case "COMPANY": factory = new CompanyFactory();
			break;
			case "ADMIN": factory = new AdminFactory();
			break;
			case "MANAGER": factory = new ManagerFactory();
			break;
			default: factory = new NullFactory();
		}
		
		return factory;
	}
	
	public abstract UserEntry createUser();
	public abstract TaskEntry createtask();
}
