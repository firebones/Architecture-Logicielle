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
	
	public void addPeriodPay(ArrayList<String> periodPayElement)
	{
		parseXml();
		
		Element rootElement = xmlFile.getDocumentElement();
		Element newPeriodPay = getPeriodPayElement(periodPayElement);
		rootElement.appendChild(newPeriodPay);
		
		saveXml();
		
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
	
	private void saveXml()
	{
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domsource = new DOMSource(xmlFile);
			StreamResult result = new StreamResult(new File("database/weekEntries.xml"));
			transformer.transform(domsource, result);
		} catch (TransformerException transException) {
			System.out.println(transException);
		}		
	}
	
	private Element getPeriodPayElement(ArrayList<String> periodPayElement)
	{
		Element userElement = xmlFile.createElement("weekEntry");
		
		Element email = xmlFile.createElement("email");
		email.setTextContent(periodPayElement.get(0));
		userElement.appendChild(email);
		
		Element weekNumber = xmlFile.createElement("weekNumber");
		weekNumber.setTextContent(periodPayElement.get(1));
		userElement.appendChild(weekNumber);
		
		Element startDate = xmlFile.createElement("startDate");
		startDate.setTextContent(periodPayElement.get(2));
		userElement.appendChild(startDate);
		
		Element endDate = xmlFile.createElement("endDate");
		endDate.setTextContent(periodPayElement.get(3));
		userElement.appendChild(endDate);
		
		Element isApproved = xmlFile.createElement("isApproved");
		isApproved.setTextContent(periodPayElement.get(4));
		userElement.appendChild(isApproved);
		
		int i=5;
		
		if (periodPayElement.get(i) == "listKilometer") {
			Element kilometers = xmlFile.createElement("kilometers");
			i++;	
			do{
				
				Element kilometer = xmlFile.createElement("kilometer");
				kilometer.setTextContent(periodPayElement.get(i));
				userElement.appendChild(kilometer);
				i++;
			}while(periodPayElement.get(i) != "listExpenses");
			
			userElement.appendChild(kilometers);
		}
		
		if (periodPayElement.get(i) == "listExpenses") {
			Element employeeExpenses = xmlFile.createElement("employeeExpenses");
			i++;	
			do{
				
				Element employeeExpense = xmlFile.createElement("employeeExpense");
				employeeExpense.setTextContent(periodPayElement.get(i));
				userElement.appendChild(employeeExpense);
				i++;
			}while(periodPayElement.get(i) != "listHours");
			
			userElement.appendChild(employeeExpenses);
		}
		
		if (periodPayElement.get(i) == "listHours") {
			Element hours = xmlFile.createElement("hours");
			i++;	
			do{
				
				Element hour = xmlFile.createElement("hour");
				hour.setTextContent(periodPayElement.get(i));
				userElement.appendChild(hour);
				i++;
			}while(periodPayElement.get(i) != "fin");
			
			userElement.appendChild(hours);
		}
		
		return userElement;
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
