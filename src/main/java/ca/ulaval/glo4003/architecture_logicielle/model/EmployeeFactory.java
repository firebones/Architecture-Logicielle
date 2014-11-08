package ca.ulaval.glo4003.architecture_logicielle.model;

public class EmployeeFactory extends AbstractFactory
{

	@Override
	public UserEntry createUser()
	{
		return new EmployeeEntry();
	}

	@Override
	public TaskEntry createtask()
	{
		return new TaskEntry();
	}

}
