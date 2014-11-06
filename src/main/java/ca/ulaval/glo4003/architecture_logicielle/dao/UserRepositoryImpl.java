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

import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.AdminEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.CompanyEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.EmployeeEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.DeptManagerEntry;

public class UserRepositoryImpl implements UserRepository {
	private Document doc;
	
	public ArrayList<UserEntry> getAllUsers() {
		ArrayList<UserEntry> userList = new ArrayList<UserEntry>();
		
		parseXml();
		
		Element docElement = doc.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("user");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				UserEntry user = getUser(element);
				userList.add(user);
			}
		}
		
		return userList;
	}
	
	public ArrayList<EmployeeEntry> getAllEmployees() {
		ArrayList<EmployeeEntry> userList = new ArrayList<EmployeeEntry>();
		
		parseXml();
		
		Element docElement = doc.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("user");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				UserEntry user = getUser(element);
				if (user.getRole() == "EMPLOYEE" || user.getRole() == "MANAGER")
					userList.add((EmployeeEntry) user);
			}
		}
		
		return userList;
	}
	
	public UserEntry getUserByEmail(String email) {
		ArrayList<UserEntry> users = getAllUsers();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getEmail().equals(email)) {
				return users.get(i);
			}
		}
		return null;
	}
	
	public void addUser(UserEntry user) {
		parseXml();
		
		if (getUserByEmail(user.getEmail()) == null){
		
		Element rootElement = doc.getDocumentElement();
		Element newUser = getUserElement(user);
		rootElement.appendChild(newUser);
		
		saveXml();
		}
	}
	
	public void updateUser(UserEntry user) {
		parseXml();
		
		if (getUserByEmail(user.getEmail()) != null){
		
		Element oldUserElement = getUserElementByEmail(user.getEmail());
		Element newUserElement = getUserElement(user);
		doc.getDocumentElement().replaceChild(newUserElement, oldUserElement);
		
		saveXml();
		}
	}
	
	public void deleteUser(UserEntry user) {
		parseXml();
		
		if (getUserByEmail(user.getEmail()) != null){
		
		Element userElement = getUserElementByEmail(user.getEmail());
		doc.getDocumentElement().removeChild(userElement);
		
		saveXml();
		}
	}
	
	public void addTaskToUser(TaskEntry task, UserEntry user) {
		parseXml();
		
		if (getUserByEmail(user.getEmail()) == null)
			return;

		Element userElement = getUserElementByEmail(user.getEmail());
		
		NodeList nodeList = userElement.getElementsByTagName("tasks");
		
		if (nodeList == null || nodeList.getLength() == 0) {
			Element taskElement = doc.createElement("tasks");
			userElement.appendChild(taskElement);
			
			nodeList = userElement.getElementsByTagName("tasks");
		}
		
		if (containsTask((Element) nodeList.item(0), task.getId()) == false) {
			Element tasksElement = (Element) nodeList.item(0);
			Element newTaskElement = doc.createElement("taskId");
			newTaskElement.setTextContent(Integer.toString(task.getId()));
			tasksElement.appendChild(newTaskElement);
		}
		
		saveXml();
	}
	
	public void setTasksToUser(List<TaskEntry> tasks, UserEntry user) {
		parseXml();
		
		if (getUserByEmail(user.getEmail()) == null)
			return;
		
		// Remove all tasks
		Element userElement = getUserElementByEmail(user.getEmail());
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
		
		Element taskElement = doc.createElement("tasks");
		userElement.appendChild(taskElement);
		
		NodeList nodeList = userElement.getElementsByTagName("tasks");
		Element newTasksElement = (Element) nodeList.item(0);
		
		for (TaskEntry task : tasks) {
			Element newTaskElement = doc.createElement("taskId");
			newTaskElement.setTextContent(Integer.toString(task.getId()));
			newTasksElement.appendChild(newTaskElement);
		}
		
		saveXml();
	}
	
	private void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(new File("database/users.xml"));
		} catch (ParserConfigurationException parseE) {
			System.out.println(parseE);
		} catch (SAXException saxE) {
			System.out.println(saxE);
		} catch (IOException ioE) {
			System.out.println(ioE);
		}
	}
	
	private void saveXml() {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domsource = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("database/users.xml"));
			transformer.transform(domsource, result);
		} catch (TransformerException transException) {
			System.out.println(transException);
		}
	}
	
	private UserEntry getUser(Element element) {
		UserEntry user;
		String role = getStringValue(element, "role");
		
		if (role.equals("ADMIN")) {
			user = new AdminEntry();
		}
		else if (role.equals("COMPANY")) {
			user = new CompanyEntry();
		}
		else if (role.equals("EMPLOYEE")) {
			user = new EmployeeEntry();
			((EmployeeEntry) user).updateTasks(getTaskListValue(element, "tasks"));
		}
		else {
			user = new DeptManagerEntry();
			((DeptManagerEntry) user).updateTasks(getTaskListValue(element, "tasks"));
		}
		user.setName(getStringValue(element, "name"));
		user.setEmail(getStringValue(element, "email"));
		user.setHashedPassword(getStringValue(element, "hashedPassword"));
		return user;
	}
	
	private Element getUserElement(UserEntry user) {
		Element userElement = doc.createElement("user");
		
		Element name = doc.createElement("name");
		name.setTextContent(user.getName());
		userElement.appendChild(name);
		
		Element email = doc.createElement("email");
		email.setTextContent(user.getEmail());
		userElement.appendChild(email);
		
		Element hashedPassword = doc.createElement("hashedPassword");
		hashedPassword.setTextContent(user.getHashedPassword());
		userElement.appendChild(hashedPassword);
		
		Element role = doc.createElement("role");
		role.setTextContent(user.getRole());
		userElement.appendChild(role);
		
		if (user instanceof EmployeeEntry && ((EmployeeEntry) user).getTasks().size() > 0) {
			Element tasks = doc.createElement("tasks");
			for (int i = 0; i < ((EmployeeEntry) user).getTasks().size(); i++) {
				TaskEntry task = ((EmployeeEntry) user).getTasks().get(i);
				System.out.println(task.getId());
				Element taskId = doc.createElement("taskId");
				taskId.setTextContent(Integer.toString(task.getId()));
				tasks.appendChild(taskId);
			}
			userElement.appendChild(tasks);
		}
		
		return userElement;
	}
	
	private Element getUserElementByEmail(String email) {
		Element docElement = doc.getDocumentElement();
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
	
	private String getStringValue(Element element, String tag) {
		String str = null;
		NodeList nodeList = element.getElementsByTagName(tag);
		
		if (nodeList != null && nodeList.getLength() > 0) {
			Element tagElement = (Element) nodeList.item(0);
			str = tagElement.getFirstChild().getNodeValue();
		}
		
		return str;
	}
	
	private List<TaskEntry> getTaskListValue(Element element, String tag) {
		List<TaskEntry> taskList = new ArrayList<TaskEntry>();
		ProjectRepository projects = new ProjectRepositoryImpl();
		NodeList nodeList = element.getElementsByTagName("taskId");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				int taskId = Integer.parseInt(tagElement.getFirstChild().getNodeValue());
				TaskEntry task = projects.getTaskById(taskId);
				taskList.add(task);
			}
		}
		
		return taskList;
	}
	
	private boolean containsTask(Element tasksElement, Integer id) {
		NodeList nodeList = tasksElement.getElementsByTagName("taskId");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				Integer taskId = Integer.parseInt(tagElement.getFirstChild().getNodeValue());
				if (taskId == id) {
					return true;
				}
			}
		}
		return false;
	}
}
