package ca.ulaval.glo4003.architecture_logicielle.model;

import java.security.Principal;

public abstract class UserEntry implements Principal {
	private String name;
	private String email;
	private String hashedPassword;
	protected String role;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}
	
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
	public String getRole() {
		return role;
	}

	public boolean isPasswordValid(String password) {
		return (hashedPassword.equals(password));
	}
}
