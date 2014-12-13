package ca.ulaval.glo4003.architecture_logicielle.model;

public class AdminFactory extends AbstractFactory
{

	@Override
	public UserEntry createUser()
	{
		return new AdminEntry();
	}

	@Override
	public TaskEntry createtask()
	{
		return new TaskEntry();
	}

}
