package ca.ulaval.glo4003.architecture_logicielle.model;

public class CompanyFactory extends AbstractFactory
{

	@Override
	public UserEntry createUser()
	{
		return new CompanyEntry();
	}

	@Override
	public TaskEntry createtask()
	{
		return new TaskEntry();
	}

}
