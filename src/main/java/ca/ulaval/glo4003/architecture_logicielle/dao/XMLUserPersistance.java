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


public class XMLUserPersistance
{
	public static XMLUserPersistance instance = null;
	private Document xmlFile;
	
	private XMLUserPersistance(){
		
	}
	
	public static synchronized XMLUserPersistance getInstance(){
		if(instance == null){
			instance = new XMLUserPersistance();
		}
		
		return instance;
	}
	
	public ArrayList<ArrayList<String>> getAllUsers() {
		ArrayList<ArrayList<String>> userList = new ArrayList<ArrayList<String>>();
		List<String> taskList = new ArrayList<String>();
		
		parseXml();
		
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("user");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String elementName = getStringValue(element, "name");
				String elementEmail = getStringValue(element, "email");
				String elementRole = getStringValue(element, "role");
				String elementHashedPassword = getStringValue(element, "hashedPassword");
				String elementCompany = getStringValue(element, "company");
				String elementDepartment = getStringValue(element, "department");
				String elementRateHour = getStringValue(element, "rateHour");
				ArrayList<String> listElement = new ArrayList<String>(); 
				listElement.add(0, elementName);
				listElement.add(1, elementEmail);
				listElement.add(2, elementRole);
				listElement.add(3, elementHashedPassword);
				listElement.add(4, elementCompany);
				listElement.add(5, elementDepartment);
				listElement.add(6, elementRateHour);

				
				taskList = getTaskListValue(element, "tasks");
				if(taskList.size() > 0){
					for(int j=0; j<taskList.size(); j++){
						listElement.add(j+7, taskList.get(j));
					}
				}
				
				
				userList.add(listElement);
			}
		}
		
		return userList;
	}
	
	
	public void addUser(ArrayList<String> user) {
		parseXml();
		
		Element rootElement = xmlFile.getDocumentElement();
		Element newUser = getUserElement(user);
		rootElement.appendChild(newUser);
		
		saveXml();
	}
	
	public void deleteUser(String email) {
		parseXml();
		
		if (email != null){
		
			Element userElement = getUserElementByEmail(email);
			xmlFile.getDocumentElement().removeChild(userElement);
		
			saveXml();
		}
	}
	
	public void updateUser(ArrayList<String> user) {
		parseXml();
		
		if (user.size() != 0){
		
		Element oldUserElement = getUserElementByEmail(user.get(1));
		Element newUserElement = getUserElement(user);
		xmlFile.getDocumentElement().replaceChild(newUserElement, oldUserElement);
		
		saveXml();
		}
	}
	
	public void addTaskToUser(ArrayList<String> task, ArrayList<String> user) {
		parseXml();

		Element userElement = getUserElementByEmail(user.get(1));
		
		NodeList nodeList = userElement.getElementsByTagName("tasks");
		
		if (nodeList == null || nodeList.getLength() == 0) {
			Element taskElement = xmlFile.createElement("tasks");
			userElement.appendChild(taskElement);
			
			nodeList = userElement.getElementsByTagName("tasks");
		}
		
		if (containsTask((Element) nodeList.item(0), task.get(0)) == false) {
			Element tasksElement = (Element) nodeList.item(0);
			Element newTaskElement = xmlFile.createElement("taskId");
			newTaskElement.setTextContent(task.get(0));
			tasksElement.appendChild(newTaskElement);
		}
		
		saveXml();
	}
	
	public void setTasksToUser(ArrayList<ArrayList<String>> tasks, ArrayList<String> user) {
		parseXml();
		
		// Remove all tasks
		Element userElement = getUserElementByEmail(user.get(1));
		NodeList tasksNodeList = userElement.getElementsByTagName("tasks");
		if (tasksNodeList != null && tasksNodeList.getLength() > 0) {
			Element tasksElement = (Element) tasksNodeList.item(0);
			userElement.removeChild(tasksElement);
		}
		
		// Add the new task list
		if (tasks == null || tasks.size() == 0) {
			saveXml();
			return;
		}
		
		Element taskElement = xmlFile.createElement("tasks");
		userElement.appendChild(taskElement);
		
		NodeList nodeList = userElement.getElementsByTagName("tasks");
		Element newTasksElement = (Element) nodeList.item(0);
		
		for (ArrayList<String> task : tasks) {
			Element newTaskElement = xmlFile.createElement("taskId");
			newTaskElement.setTextContent(task.get(0));
			newTasksElement.appendChild(newTaskElement);
		}
		
		saveXml();
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	private synchronized void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			xmlFile = docBuilder.parse(new File("database/users.xml"));
		} catch (ParserConfigurationException parseE) {
			System.out.println(parseE);
		} catch (SAXException saxE) {
			System.out.println(saxE);
		} catch (IOException ioE) {
			System.out.println(ioE);
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
	
	private synchronized void saveXml() {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domsource = new DOMSource(xmlFile);
			StreamResult result = new StreamResult(new File("database/users.xml"));
			transformer.transform(domsource, result);
		} catch (TransformerException transException) {
			System.out.println(transException);
		}
	}
	
	private Element getUserElement(ArrayList<String> user) {
		Element userElement = xmlFile.createElement("user");
		
		Element name = xmlFile.createElement("name");
		name.setTextContent(user.get(0));
		userElement.appendChild(name);
		
		Element email = xmlFile.createElement("email");
		email.setTextContent(user.get(1));
		userElement.appendChild(email);
		
		Element role = xmlFile.createElement("role");
		role.setTextContent(user.get(2));
		userElement.appendChild(role);
		
		Element hashedPassword = xmlFile.createElement("hashedPassword");
		hashedPassword.setTextContent(user.get(3));
		userElement.appendChild(hashedPassword);
		
		Element company = xmlFile.createElement("company");
		company.setTextContent(user.get(4));
		userElement.appendChild(company);
		
		Element department = xmlFile.createElement("department");
		department.setTextContent(user.get(5));
		userElement.appendChild(department);
		
		Element rateHour = xmlFile.createElement("rateHour");
		rateHour.setTextContent(user.get(6));
		userElement.appendChild(rateHour);
		
		if (user.size() > 7) {
			Element tasks = xmlFile.createElement("tasks");
			for (int i = 7; i < user.size(); i++) {
				Element taskId = xmlFile.createElement("taskId");
				taskId.setTextContent(user.get(i));
				tasks.appendChild(taskId);
			}
			userElement.appendChild(tasks);
		}
		
		return userElement;
	}
	
	private Element getUserElementByEmail(String email) {
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("user");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element userElement = (Element) nodeList.item(i);
				Element emailElement = (Element) userElement.getElementsByTagName("email").item(0);
				String emailString = emailElement.getFirstChild().getNodeValue();
				if (emailString.equals(email))
					return userElement;
			}
		}
		
		return null;
	}
	
	private boolean containsTask(Element tasksElement, String id) {
		NodeList nodeList = tasksElement.getElementsByTagName("taskId");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				String taskId = tagElement.getFirstChild().getNodeValue();
				if (taskId == id) {
					return true;
				}
			}
		}
		return false;
	}
/////////////////////////////////////////a verifier/////////////////////////////////////////////////////////////////////	
	private List<String> getTaskListValue(Element element, String tag) {
		
		List<String> taskList = new ArrayList<String>();
		
		NodeList nodeList = element.getElementsByTagName("taskId");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				String taskId = tagElement.getFirstChild().getNodeValue();

				taskList.add(taskId);
			}
		}
		
		return taskList;
	}
}
