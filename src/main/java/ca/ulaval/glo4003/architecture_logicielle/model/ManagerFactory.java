package ca.ulaval.glo4003.architecture_logicielle.model;

public class ManagerFactory extends AbstractFactory
{

	@Override
	public UserEntry createUser()
	{
		return new EmployeeEntry(RoleUser.MANAGER);
	}

	@Override
	public TaskEntry createtask()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
