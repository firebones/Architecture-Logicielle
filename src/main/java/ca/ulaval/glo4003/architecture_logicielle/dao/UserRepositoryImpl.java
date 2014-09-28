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
	
	public ArrayList<UserEntry> getAllUser() {
		ArrayList<UserEntry> userList = new ArrayList<UserEntry>();
		
		parseXml();
		
		Element docElement = doc.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("employee");
		
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
			doc = docBuilder.parse(new File("src/xmlsrc/employees.xml"));
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
		//user.updateTasks(getTaskEntryValue(element));
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
	
	// A completer...
	/*private List<TaskEntry> getTaskEntryValue(Element element) {
		List<TaskEntry> taskList;
		TaskEntry task = null;
		ProjectRepository projects = new ProjectRepositoryImpl();
		NodeList nodeList = element.getElementsByTagName("taskId");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				String stringTaskId = tagElement.getFirstChild().getNodeValue();
				task = projects.getById(Integer.parseInt(stringTaskId));
				taskList.add(task);
			}
		}
		
		return null;
	}*/
}
