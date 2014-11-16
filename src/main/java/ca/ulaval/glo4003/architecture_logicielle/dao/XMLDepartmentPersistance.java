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
		ArrayList<ArrayList<String>> departmentList = new ArrayList<ArrayList<String>>();
		
		parseXml();
		
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("department");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				ArrayList<String> departmentStrings = new ArrayList<String>();
				
				Element departmentElement = (Element) nodeList.item(i);
				String elementName = getStringValue(departmentElement, "name");
				departmentStrings.add(0, elementName);
				departmentStrings.add(1, "");

				NodeList nodeListEmployees = departmentElement.getElementsByTagName("employees");
				departmentStrings.add("listEmployee");
				if (nodeListEmployees != null && nodeListEmployees.getLength() > 0) {
					List<String> employeeList = getEmployeeListValue((Element) nodeListEmployees.item(0), "employee");
					if(employeeList.size() > 0){
						for(int j=0; j<employeeList.size(); j++){
							departmentStrings.add(employeeList.get(j));
						}
					}
				}
				
				NodeList nodeListManagers = departmentElement.getElementsByTagName("managers");
				departmentStrings.add("listManager");
				if (nodeListManagers != null && nodeListManagers.getLength() > 0) {
					List<String> managerList = getEmployeeListValue((Element) nodeListManagers.item(0), "manager");
					if(managerList.size() > 0){
						for(int j=0; j<managerList.size(); j++){
							departmentStrings.add(managerList.get(j));
						}
					}
				}
				
				departmentStrings.add("fin");

				departmentList.add(departmentStrings);
			}
		}
		
		return departmentList;
	}

	public void addDepartment(ArrayList<String> departmentelement)
	{
		parseXml();
		
		Element rootElement = xmlFile.getDocumentElement();
		Element newDepartment = getDepartmentElement(departmentelement);
		rootElement.appendChild(newDepartment);
		
		saveXml();
		
	}
	
	public void deleteDepartment(String departmentName)
	{
		parseXml();
		
		if (departmentName != null){
		
			Element departmentElement = getDepartmentElementByName(departmentName);
			xmlFile.getDocumentElement().removeChild(departmentElement);
		
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
		Element departmentElement = xmlFile.createElement("department");
		
		Element name = xmlFile.createElement("name");
		name.setTextContent(department.get(0));
		departmentElement.appendChild(name);
			
		int i=3;
		if (department.get(2) == "listEmployee" && department.get(i) != "listManager") {
			Element employees = xmlFile.createElement("employees");
			do{
					Element employee = xmlFile.createElement("employee");
					employee.setTextContent(department.get(i));
					employees.appendChild(employee);
					i++;
			}while(department.get(i)!= "listManager");
			departmentElement.appendChild(employees);
		}
		
		if (department.get(i) == "listManager" && department.get(i+1) != "fin") {
			Element managers = xmlFile.createElement("managers");
			i++;
			do{
					Element manager = xmlFile.createElement("manager");
					manager.setTextContent(department.get(i));
					managers.appendChild(manager);
					i++;
			}while(department.get(i)!= "fin");
			departmentElement.appendChild(managers);
		}
		
		return departmentElement;
	}
	
	private Element getDepartmentElementByName(String departmentName) {
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("department");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element departmentElement = (Element) nodeList.item(i);
				Element emailElement = (Element) departmentElement.getElementsByTagName("name").item(0);
				String emailString = emailElement.getFirstChild().getNodeValue();
				if (emailString.equals(departmentName))
					return departmentElement;
			}
		}
		
		return null;
	}

	

}
