package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ca.ulaval.glo4003.architecture_logicielle.model.Employe;

public class GestionFichierXML {
	
	private String obtenirNoeudValeur(String strTag, Element objet) {
		Node nValeur = (Node) objet.getElementsByTagName(strTag).item(0).getFirstChild();
		return nValeur.getNodeValue();
	}
	
	public ArrayList<Employe> obtenirEmploye() {

		ArrayList<Employe> lstEmploye = new ArrayList<Employe>();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("src/xmlsrc/xmlFile.xml"));

			doc.getDocumentElement().normalize();

			NodeList noeudEmploye = doc.getElementsByTagName("employe");

			for (int i = 0; i < noeudEmploye.getLength(); i++) {
				Node employe = noeudEmploye.item(i);
				if (employe.getNodeType() == Node.ELEMENT_NODE) {
					Element unElement = (Element) employe;

					Employe objEmploye = new Employe();
					
					objEmploye.setNom(obtenirNoeudValeur("nom", unElement));
					objEmploye.setPrenom(obtenirNoeudValeur("prenom", unElement));
					objEmploye.setEmail(obtenirNoeudValeur("email", unElement));
					objEmploye.setAddress(obtenirNoeudValeur("address", unElement));

					lstEmploye.add(objEmploye);
				}
			}
		} catch (ParserConfigurationException parseE) {
			System.out.println(parseE);
		} catch (SAXException saxE) {
			System.out.println(saxE);
		} catch (IOException ioE) {
			System.out.println(ioE);
		}
		return lstEmploye;
	}

	public void aditionerEmploye(Employe employe){
		
		try{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File("src/xmlsrc/xmlFile.xml"));
		
		doc.getDocumentElement().normalize();
		
		Node noeudRacine = doc.getDocumentElement();
		//adition d'étiquette
		Element nouvelEmploye = doc.createElement("employe");
		//étiquettes filles
		
		Element nouvelNom = doc.createElement("nom");
		nouvelNom.setTextContent(employe.getNom());
		
		Element nouvelPrenom = doc.createElement("prenom");
		nouvelPrenom.setTextContent(employe.getPrenom());
		
		Element nouvelEmail = doc.createElement("email");
		nouvelEmail.setTextContent(employe.getEmail());
		
		Element nouvelAddress = doc.createElement("address");
		nouvelAddress.setTextContent(employe.getAddress());
		
		
		nouvelEmploye.appendChild(nouvelNom);
		nouvelEmploye.appendChild(nouvelPrenom);
		nouvelEmploye.appendChild(nouvelEmail);
		nouvelEmploye.appendChild(nouvelAddress);
		
		noeudRacine.appendChild(nouvelEmploye);
		
		//Transformation des données en XML
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/xmlsrc/xmlFile.xml"));
		transformer.transform(source, result);
		
		 
		
		}catch(ParserConfigurationException parseE){
			System.out.println(parseE);
		}catch(SAXException saxE){
			System.out.println(saxE);
		}catch(IOException ioE){
			System.out.println(ioE);
		}catch(TransformerException trE){
			System.out.println(trE);
		}
	}
	

}
