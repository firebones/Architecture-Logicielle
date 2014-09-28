package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.UserEntry;

public class UserRepositoryImpl implements UserRepository {
	Document doc;
	
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
	
	public UserEntry getUserByEmail(String email) {
		ArrayList<UserEntry> users = getAllUsers();
		for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getEmail() == email)
					return users.get(i);
		}
		return null;
	}
	
	public void addUser(UserEntry user) {
		
	}
	
	public void updateUser(UserEntry user) {
		
	}
	
	public void deleteUser(String email) {
		
	}
	
	public void addTaskToUser(TaskEntry task, UserEntry user) {
		
	}
	
	private void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(new File("src/xmlsrc/users.xml"));
		} catch (ParserConfigurationException parseE) {
			System.out.println(parseE);
		} catch (SAXException saxE) {
			System.out.println(saxE);
		} catch (IOException ioE) {
			System.out.println(ioE);
		}
	}
	
	private UserEntry getUser(Element element) {
		UserEntry user = new UserEntry();
		user.setName(getStringValue(element, "name"));
		user.setEmail(getStringValue(element, "email"));
		user.setHashedPassword(getStringValue(element, "hashedPassword"));
		user.setRole(getStringValue(element, "role"));
		user.updateTasks(getTaskListValue(element, "tasks"));
		return user;
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
}
