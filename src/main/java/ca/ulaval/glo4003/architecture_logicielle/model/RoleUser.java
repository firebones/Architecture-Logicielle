package ca.ulaval.glo4003.architecture_logicielle.model;

public enum RoleUser
{

	EMPLOYEE("ROLE_EMPLOYEE"),
	MANAGER("ROLE_MANAGER"), 
	COMPANY("ROLE_COMPANY"), 
	ADMIN("ROLE_ADMIN");

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
