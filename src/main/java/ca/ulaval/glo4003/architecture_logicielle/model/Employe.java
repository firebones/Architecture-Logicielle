package ca.ulaval.glo4003.architecture_logicielle.model;

public class Employe {
	private String  nom, prenom, email, address;

	public Employe( String nom, String prenom, String email, String address) {
		super();
		
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.address = address;
	}
	
	public Employe(){}

	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
