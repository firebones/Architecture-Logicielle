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
						j++;
						index+=2;
					}while(j<node.getLength());
				}
				
				projectList.add(listElement);
			}
		}
		
		return projectList;
	}
	
	private void parseXml() {
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
