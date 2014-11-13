package ca.ulaval.glo4003.architecture_logicielle.model;

public enum RoleUser
{

	EMPLOYEE("STATE_APPROVED"),
	MANAGER("STATE_SUBMITTED"), 
	COMPANY("STATE_INPROGRESS"), 
	ADMIN("STATE_REFUSED");

	private final String roleString;

	private RoleUser(String roleString)
	{
		this.roleString = roleString;
	}

	public String getRoleUserEntry()
	{
		return roleString;
	}
}
