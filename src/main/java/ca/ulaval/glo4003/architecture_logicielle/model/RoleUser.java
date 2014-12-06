package ca.ulaval.glo4003.architecture_logicielle.model;

public enum RoleUser
{

	EMPLOYEE("Employé"),
	MANAGER("Gestionnaire"), 
	COMPANY("Entreprise"), 
	ADMIN("Administrateur");

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
