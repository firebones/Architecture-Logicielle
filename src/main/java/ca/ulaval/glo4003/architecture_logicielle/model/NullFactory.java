package ca.ulaval.glo4003.architecture_logicielle.model;

public class NullFactory extends AbstractFactory
{

	@Override
	public UserEntry createUser()
	{
		return null;
	}

	@Override
	public TaskEntry createtask()
	{
		return null;
	}

}
