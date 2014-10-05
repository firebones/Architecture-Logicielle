import ca.ulaval.glo4003.architecture_logicielle.dao.ToolsXML;
import ca.ulaval.glo4003.architecture_logicielle.model.Employee;




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
		Employee employe = new Employee();
		ToolsXML objEmplXml = new ToolsXML();
		
		
		String id = "243";
		String nom = "jackson";
		String prenom = "Michael";
		String email = "michael@ulaval.ca";
		String address = "rue street";
		
		
		employe.setLastName(nom);
		employe.setFirstName(prenom);
		employe.setEmail(email);
		employe.setAddress(address);
		
		
	//	objEmplXml.aditionerEmploye(employe);		
		
	}
	

}
