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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDepartmentPersistance
{
	public static XMLDepartmentPersistance instance = null;
	private Document xmlFile;
	
	private XMLDepartmentPersistance(){
		
	}
	
	public static synchronized XMLDepartmentPersistance getInstance(){
		if(instance == null){
			instance = new XMLDepartmentPersistance();
		}
		
		return instance;
	}
	
	public ArrayList<ArrayList<String>> getAllDepartement()
	{
		ArrayList<ArrayList<String>> departementList = new ArrayList<ArrayList<String>>();
		List<String> employeeList = new ArrayList<String>();
		
		parseXml();
		
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("department");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String elementName = getStringValue(element, "name");
				String elementEmail = getStringValue(element, "companyEmail");
				ArrayList<String> listElement = new ArrayList<String>(); 
				listElement.add(0, elementName);
				listElement.add(1, elementEmail);

				
				employeeList = getEmployeeListValue(element, "employees");
				if(employeeList.size() > 0){
					for(int j=0; j<employeeList.size(); j++){
						listElement.add(j+2, employeeList.get(j));
					}
				}

				departementList.add(listElement);
			}
		}
		
		return departementList;
	}

	public void addDepartment(ArrayList<String> departmentelement)
	{
		parseXml();
		
		Element rootElement = xmlFile.getDocumentElement();
		Element newDepartement = getDepartmentElement(departmentelement);
		rootElement.appendChild(newDepartement);
		
		saveXml();
		
	}
	
	public void deleteDepartement(String departmentName)
	{
		parseXml();
		
		if (departmentName != null){
		
			Element userElement = getDepartmentElementByName(departmentName);
			xmlFile.getDocumentElement().removeChild(userElement);
		
			saveXml();
		}
		
	}
	
	public void updateDepartment(ArrayList<String> departmentElement)
	{
		parseXml();
		
		if (departmentElement.size() != 0){
		
		Element oldDepartmentElement = getDepartmentElementByName(departmentElement.get(0));
		Element newDepartmentElement = getDepartmentElement(departmentElement);
		xmlFile.getDocumentElement().replaceChild(newDepartmentElement, oldDepartmentElement);
		
		saveXml();
		}
	}
	
	private synchronized void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			xmlFile = docBuilder.parse(new File("database/departments.xml"));
		} catch (ParserConfigurationException parseE) {
			System.out.println(parseE);
		} catch (SAXException saxE) {
			System.out.println(saxE);
		} catch (IOException ioE) {
			System.out.println(ioE);
		}
	}
	
	private synchronized void saveXml() {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domsource = new DOMSource(xmlFile);
			StreamResult result = new StreamResult(new File("database/departments.xml"));
			transformer.transform(domsource, result);
		} catch (TransformerException transException) {
			System.out.println(transException);
		}
	}
	
	
	private String getStringValue(Element element, String tag) {
		String str = null;
		NodeList nodeList = element.getElementsByTagName(tag);
		
		if (nodeList != null && nodeList.getLength() > 0) {
			Element tagElement = (Element) nodeList.item(0);
			str = tagElement.getFirstChild().getNodeValue();
		}
		
		return str;
	}
	
	private List<String> getEmployeeListValue(Element element, String tag) {
		
		List<String> employeeList = new ArrayList<String>();
		
		NodeList nodeList = element.getElementsByTagName(tag);
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				String email = tagElement.getFirstChild().getNodeValue();

				employeeList.add(email);
			}
		}
		
		return employeeList;
	}

	private Element getDepartmentElement(ArrayList<String> department) {
		Element userElement = xmlFile.createElement("department");
		
		Element name = xmlFile.createElement("name");
		name.setTextContent(department.get(0));
		userElement.appendChild(name);
		
		Element companyEmail = xmlFile.createElement("companyEmail");
		companyEmail.setTextContent(department.get(1));
		userElement.appendChild(companyEmail);
			
		int i=3;
		if (department.get(2)== "listEmployee" && department.get(i)!= "listManager") {
			Element employees = xmlFile.createElement("employees");
			do{
					Element employee = xmlFile.createElement("employee");
					employee.setTextContent(department.get(i));
					employees.appendChild(employee);
					i++;
			}while(department.get(i)!= "listManager");
			userElement.appendChild(employees);
		}
		
		if (department.get(i)== "listManager") {
			Element managers = xmlFile.createElement("managers");
			do{
					Element manager = xmlFile.createElement("manager");
					manager.setTextContent(department.get(i));
					managers.appendChild(manager);
					i++;
			}while(department.get(i)!= "fin");
			userElement.appendChild(managers);
		}
		
		return userElement;
	}
	
	private Element getDepartmentElementByName(String departmentName) {
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("department");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element userElement = (Element) nodeList.item(i);
				Element emailElement = (Element) userElement.getElementsByTagName("name").item(0);
				String emailString = emailElement.getFirstChild().getNodeValue();
				if (emailString.equals(departmentName))
					return userElement;
			}
		}
		
		return null;
	}

	

}
