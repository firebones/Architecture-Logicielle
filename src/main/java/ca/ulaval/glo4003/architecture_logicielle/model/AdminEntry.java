package ca.ulaval.glo4003.architecture_logicielle.model;

public class AdminEntry extends UserEntry {
	public AdminEntry() {
		this.role = Role.ADMIN;
	}
}
