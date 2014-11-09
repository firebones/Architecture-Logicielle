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


public class XMLWeekPersistance
{
	public static XMLWeekPersistance instance = null;
	private Document xmlFile;
	
	private XMLWeekPersistance(){
		
	}
	
	public static synchronized XMLWeekPersistance getInstance(){
		if(instance == null){
			instance = new XMLWeekPersistance();
		}
		
		return instance;
	}
	
	public ArrayList<ArrayList<String>> getAllWeekEntries() {
		ArrayList<ArrayList<String>> weekEntryList = new ArrayList<ArrayList<String>>();
		
		parseXml();
		
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("weekEntry");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String elementEmail = getStringValue(element, "email");
				String elementWeekNumber = getStringValue(element, "weekNumber");
				String elementStartDate = getStringValue(element, "startDate");
				String elementEndDate = getStringValue(element, "endDate");
				String elementisApproved = getStringValue(element, "isApproved");
				ArrayList<String> listElement = new ArrayList<String>();
				listElement.add(0, elementEmail);
				listElement.add(1, elementWeekNumber);
				listElement.add(2, elementStartDate);
				listElement.add(3, elementEndDate);
				listElement.add(4, elementisApproved);
				
				NodeList nodes = element.getElementsByTagName("kilometer");
				listElement.add(5, "listKilometer");
				int k=6;
				
				if (nodes != null && nodes.getLength() > 0) {
					for (int j = 0; j < nodes.getLength(); j++) {
						Element tagElement = (Element) nodes.item(j);
						listElement.add(k, tagElement.getFirstChild().getNodeValue());
						k++;
					}
				}
				
				nodes = element.getElementsByTagName("employeeExpense");
				listElement.add(k, "listExpenses");
				k++;
				
				if (nodes != null && nodes.getLength() > 0) {
					for (int j = 0; j < nodes.getLength(); j++) {
						Element tagElement = (Element) nodes.item(j);
						listElement.add(k, tagElement.getFirstChild().getNodeValue());
						k++;
					}
				}
				
				nodes = element.getElementsByTagName("hour");
				listElement.add(k, "listHours");
				k++;
				
				if (nodes != null && nodes.getLength() > 0) {
					for (int j = 0; j < nodes.getLength(); j++) {
						Element tagElement = (Element) nodes.item(j);
						listElement.add(k, tagElement.getFirstChild().getNodeValue());
						k++;
					}
				}
				
				listElement.add(k, "fin");
				
				weekEntryList.add(listElement);
			}
		}
		
		return weekEntryList;
	}
	
	public ArrayList<String> getWeekEntryByEmailAndWeek(String email, String weekNumber) {
		
		ArrayList<ArrayList<String>> weekEntries = getAllWeekEntries();
		for (int i = 0; i < weekEntries.size(); i++) {
				if ((weekEntries.get(i).get(0).toString().compareTo(email) == 0) && (weekEntries.get(i).get(1).toString().compareTo(weekNumber) == 0))
					return weekEntries.get(i);
		}
		return null;
	}
	
	private void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			xmlFile = docBuilder.parse(new File("database/weekEntries.xml"));
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
