import ca.ulaval.glo4003.architecture_logicielle.dao.GestionFichierXML;
import ca.ulaval.glo4003.architecture_logicielle.model.Employe;




public class TestXML {

	public static void main(String[] args) {
		/*
		System.out.println("Information Employe \n");
		
		GestionXML objEmplXml = new GestionXML();
		ArrayList<Employe> lstEmployes = objEmplXml.obtenirEmploye();
		
		for(int i=0; i < lstEmployes.size(); i++){
			System.out.println("id:"+lstEmployes.get(i).getId());
			System.out.println("nom:"+lstEmployes.get(i).getNom());
			System.out.println("prenom:"+lstEmployes.get(i).getPrenom());
			System.out.println("email:"+lstEmployes.get(i).getEmail());
			System.out.println("address:"+lstEmployes.get(i).getAddress());
			System.out.println("\n");
		}
		*/

		//Adition employe
		Employe employe = new Employe();
		GestionFichierXML objEmplXml = new GestionFichierXML();
		
		
		String id = "243";
		String nom = "jackson";
		String prenom = "Michael";
		String email = "michael@ulaval.ca";
		String address = "rue street";
		
		employe.setId(id);
		employe.setNom(nom);
		employe.setPrenom(prenom);
		employe.setEmail(email);
		employe.setAddress(address);
		
		
		objEmplXml.aditionerEmploye(employe);		
		
	}
	

}
