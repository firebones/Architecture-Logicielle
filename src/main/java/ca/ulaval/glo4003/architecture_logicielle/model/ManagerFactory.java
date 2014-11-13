package ca.ulaval.glo4003.architecture_logicielle.model;

import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry.Role;

public class ManagerFactory extends AbstractFactory
{

	@Override
	public UserEntry createUser()
	{
		return new EmployeeEntry(Role.MANAGER);
	}

	@Override
	public TaskEntry createtask()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
