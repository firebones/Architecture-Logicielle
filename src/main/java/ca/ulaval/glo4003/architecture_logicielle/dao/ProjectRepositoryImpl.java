package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ca.ulaval.glo4003.architecture_logicielle.model.ProjectEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.TaskEntry;

public class ProjectRepositoryImpl implements ProjectRepository {
	private Document doc;
	
	public ArrayList<ProjectEntry> getAllProjects() {
		ArrayList<ProjectEntry> projectList = new ArrayList<ProjectEntry>();
		
		parseXml();
		
		Element docElement = doc.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("project");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				ProjectEntry project = getProject(element);
				projectList.add(project);
			}
		}
		
		return projectList;
	}
	
	public ProjectEntry getProjectById(Integer id) {
		ArrayList<ProjectEntry> projects = getAllProjects();
		for (int i = 0; i < projects.size(); i++) {
				if (projects.get(i).getId() == id)
					return projects.get(i);
		}
		return null;
	}
	
	public TaskEntry getTaskById(Integer id) {
		ArrayList<ProjectEntry> projects = getAllProjects();
		for (int i = 0; i < projects.size(); i++) {
			for (int j = 0; j < projects.get(i).getTasks().size(); j++) {
				if (projects.get(i).getTasks().get(j).getId() == id)
					return projects.get(i).getTasks().get(j);
			}
		}
		return null;
	}
	
	public void addProject(ProjectEntry project) {
		
	}
	
	public void deleteProject(int id) {
		
	}
	
	public void updateProject(int projectId, ProjectEntry project) {
		
	}
	
	public void addTaskToProject(int projectId, TaskEntry task) {
		
	}
	
	public void removeTaskFromProject(int taskId, int projectId) {
		
	}
	
	private void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(new File("src/xmlsrc/projects.xml"));
		} catch (ParserConfigurationException parseE) {
			System.out.println(parseE);
		} catch (SAXException saxE) {
			System.out.println(saxE);
		} catch (IOException ioE) {
			System.out.println(ioE);
		}
	}
	
	private ProjectEntry getProject(Element element) {
		ProjectEntry project = new ProjectEntry();
		project.setId(Integer.parseInt(getStringValue(element, "id")));
		project.setName(getStringValue(element, "name"));
		
		NodeList nodeList = element.getElementsByTagName("task");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				TaskEntry task = getTask(tagElement);
				project.addTask(task);
			}
		}
		
		return project;
	}
	
	private TaskEntry getTask(Element element) {
		TaskEntry task = new TaskEntry();
		task.setId(Integer.parseInt(getStringValue(element, "id")));
		task.setName(getStringValue(element, "name"));
		return task;
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
