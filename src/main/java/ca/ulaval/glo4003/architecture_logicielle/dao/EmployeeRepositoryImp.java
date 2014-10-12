package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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

import ca.ulaval.glo4003.architecture_logicielle.model.Employee;
import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;

public class EmployeeRepositoryImp implements EmployeeRepository {

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> lstEmployees = new ArrayList<Employee>();
		try {
			
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(new File("database/employees.xml"));
				doc.getDocumentElement().normalize();
				
				Element docElement = doc.getDocumentElement();
				NodeList nodeList = docElement.getElementsByTagName("employee");
				
				if (nodeList != null && nodeList.getLength() > 0) {
					for (int i = 0; i < nodeList.getLength(); i++) {
						Element element = (Element) nodeList.item(i);
						Employee employee = getEmployee(element);
						lstEmployees.add(employee);
					}
				}
				
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lstEmployees;
	}

	@Override
	public Employee getEmployeByEmail(String email) {
		Employee employeeEmail = new Employee();
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("database/employees.xml"));
			doc.getDocumentElement().normalize();
			
			Element docElement = doc.getDocumentElement();
			NodeList nodeList = docElement.getElementsByTagName("employee");
			
			if (nodeList != null && nodeList.getLength() > 0) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					Employee employee = getEmployee(element);
					if(employee.getEmail().toString().compareTo(email) == 0){
						employeeEmail = employee;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return employeeEmail;
	}

	@Override
	public void addEmployee(Employee employe) throws Exception{
		String email = employe.getEmail();
		Employee employeeEmail = new Employee();
		
		//Validation if employee is in the file XML
		employeeEmail = getEmployeByEmail(email);
		
		if(employeeEmail != null){
			//TODO 
			
			 throw new Exception("The Employee is already exist in the file");
		}
		else{
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("database/employees.xml"));
			doc.getDocumentElement().normalize();
			
			Node noeudRacine = doc.getDocumentElement();
			//adition d'�tiquette
			Element nouvelEmploye = doc.createElement("employee");
			//�tiquettes filles
			
			Element nouvelNom = doc.createElement("nom");
			nouvelNom.setTextContent(employe.getLastName());
			
			Element nouvelPrenom = doc.createElement("prenom");
			nouvelPrenom.setTextContent(employe.getFirstName());
			
			Element nouvelEmail = doc.createElement("email");
			nouvelEmail.setTextContent(employe.getEmail());
			
			Element nouvelAddress = doc.createElement("address");
			nouvelAddress.setTextContent(employe.getAddress());
			
		
			nouvelEmploye.appendChild(nouvelNom);
			nouvelEmploye.appendChild(nouvelPrenom);
			nouvelEmploye.appendChild(nouvelEmail);
			nouvelEmploye.appendChild(nouvelAddress);
			
			noeudRacine.appendChild(nouvelEmploye);
			
			//Transformation des donn�es en XML
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("database/employees.xml"));
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

	@Override
	public void updateEmployee(Employee employe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployee(Element element) {
		Employee employee = new Employee();
		ToolsXML toolsxml = new ToolsXML();
		
		employee.setLastName(toolsxml.getStringValue(element, "nom"));
		employee.setFirstName(toolsxml.getStringValue(element, "prenom"));
		employee.setAddress(toolsxml.getStringValue(element, "address"));
		employee.setEmail(toolsxml.getStringValue(element, "email"));
		
		return employee;
	}

}
