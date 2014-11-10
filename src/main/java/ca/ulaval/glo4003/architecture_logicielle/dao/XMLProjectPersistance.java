package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class XMLProjectPersistance
{
	public static XMLProjectPersistance instance = null;
	private Document xmlFile;
	
	private XMLProjectPersistance(){
		
	}
	
	public static synchronized XMLProjectPersistance getInstance(){
		if(instance == null){
			instance = new XMLProjectPersistance();
		}
		
		return instance;
	}
	
	public ArrayList<ArrayList<String>> getAllProjects() {
		ArrayList<ArrayList<String>> projectList = new ArrayList<ArrayList<String>>();
		
		parseXml();
		
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("project");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String idProject = getStringValue(element, "id");
				String nameProject = getStringValue(element, "name");
				
				ArrayList<String> listElement = new ArrayList<String>();
				listElement.add(0, idProject);
				listElement.add(1, nameProject);
				
				NodeList node = element.getElementsByTagName("task");
				
				if (node != null && node.getLength() > 0) {
					int j = 0;
					int index = 2;
					
					do{
						Element tagElement = (Element) node.item(j);
						
						listElement.add(index, getStringValue(tagElement, "id"));
						listElement.add(index+1, getStringValue(tagElement, "name"));
						listElement.add(index+2, getStringValue(tagElement, "rate"));
						j++;
						index+=3;
					}while(j<node.getLength());
				}
				
				projectList.add(listElement);
			}
		}
		
		return projectList;
	}
	
	private Element getProjectElementById(Integer projectId) {
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("project");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element projectElement = (Element) nodeList.item(i);
				Element idElement = (Element) projectElement.getElementsByTagName("id").item(0);
				String idString = idElement.getFirstChild().getNodeValue();
				if (idString.equals(projectId.toString()))
					return projectElement;
			}
		}
		
		return null;
	}
	
	private Element getTaskElementById(Integer taskId) {
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("task");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element taskElement = (Element) nodeList.item(i);
				Element idElement = (Element) taskElement.getElementsByTagName("id").item(0);
				String idString = idElement.getFirstChild().getNodeValue();
				if (idString.equals(taskId.toString()))
					return taskElement;
			}
		}
		
		return null;
	}
	
	public void addProject(ArrayList<String> project) {
		parseXml();
		
		Element rootElement = xmlFile.getDocumentElement();
		Element newProject = getProjectElement(project);
		rootElement.appendChild(newProject);
		
		saveXml();
	}
	
	public void addTaskToProject(int projectId, ArrayList<String> task) {
		parseXml();
		
		Element projectElement = getProjectElementById(projectId);
		Element newTask = getTaskElement(task);
		
		NodeList taskList = projectElement.getElementsByTagName("tasks");
		taskList.item(0).appendChild(newTask);
		
		saveXml();
	}
	
	public void updateProject(ArrayList<String> project) {
		parseXml();
		
		if (project.size() != 0){
		
		Element oldProjectElement = getProjectElementById(Integer.parseInt(project.get(0)));
		
		NodeList nameNode = oldProjectElement.getElementsByTagName("name");
		nameNode.item(0).setTextContent(project.get(1));
	
		saveXml();
		}
	}
	
	public void updateTask(ArrayList<String> task) {
		parseXml();
		
		if (task.size() != 0){
		
		Element oldTaskElement = getTaskElementById(Integer.parseInt(task.get(0)));
		Element newTaskElement = getTaskElement(task);
		
		oldTaskElement.getParentNode().replaceChild(newTaskElement, oldTaskElement);
	
		saveXml();
		}
	}
	
	private Element getProjectElement(ArrayList<String> project) {
		Element projectElement = xmlFile.createElement("project");
		
		Element id = xmlFile.createElement("id");
		id.setTextContent(project.get(0));
		projectElement.appendChild(id);
		
		Element name = xmlFile.createElement("name");
		name.setTextContent(project.get(1));
		projectElement.appendChild(name);
		
		Element tasks = xmlFile.createElement("tasks");
		projectElement.appendChild(tasks);
		
		return projectElement;
	}

	private Element getTaskElement(ArrayList<String> task) {
		Element taskElement = xmlFile.createElement("task");
		
		Element id = xmlFile.createElement("id");
		id.setTextContent(task.get(0));
		taskElement.appendChild(id);
		
		Element name = xmlFile.createElement("name");
		name.setTextContent(task.get(1));
		taskElement.appendChild(name);
		
		Element rate = xmlFile.createElement("rate");
		rate.setTextContent(task.get(2));
		taskElement.appendChild(rate);
		
		return taskElement;
	}
	
	private synchronized void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			xmlFile = docBuilder.parse(new File("database/projects.xml"));
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
			StreamResult result = new StreamResult(new File("database/projects.xml"));
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

}
