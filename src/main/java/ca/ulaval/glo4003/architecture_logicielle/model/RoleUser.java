package ca.ulaval.glo4003.architecture_logicielle.model;

public enum RoleUser
{

	EMPLOYEE("EMPLOYEE"),
	MANAGER("MANAGER"), 
	COMPANY("COMPANY"), 
	ADMIN("ADMIN");

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
