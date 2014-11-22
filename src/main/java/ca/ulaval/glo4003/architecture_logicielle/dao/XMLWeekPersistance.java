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

public class XMLWeekPersistance {
	public static XMLWeekPersistance instance = null;
	private Document xmlFile;

	private XMLWeekPersistance() {

	}

	public static synchronized XMLWeekPersistance getInstance() {
		if (instance == null) {
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
				String elementYearNumber = getStringValue(element, "yearNumber");
				String elementisState = getStringValue(element, "state");
				ArrayList<String> listElement = new ArrayList<String>();
				listElement.add(0, elementEmail);
				listElement.add(1, elementWeekNumber);
				listElement.add(2, elementYearNumber);
				listElement.add(3, elementisState);

				NodeList nodes = element.getElementsByTagName("kilometer");
				listElement.add(4, "listKilometer");
				int k = 5;

				if (nodes != null && nodes.getLength() > 0) {
					for (int j = 0; j < nodes.getLength(); j++) {
						Element tagElement = (Element) nodes.item(j);
						listElement.add(k, tagElement.getFirstChild()
								.getNodeValue());
						k++;
					}
				}

				nodes = element.getElementsByTagName("employeeExpense");
				listElement.add(k, "listExpenses");
				k++;

				if (nodes != null && nodes.getLength() > 0) {
					for (int j = 0; j < nodes.getLength(); j++) {
						Element tagElement = (Element) nodes.item(j);
						listElement.add(k, tagElement.getFirstChild()
								.getNodeValue());
						k++;
					}
				}

				nodes = element.getElementsByTagName("hour");
				listElement.add(k, "listHours");
				k++;

				if (nodes != null && nodes.getLength() > 0) {
					for (int j = 0; j < nodes.getLength(); j++) {
						Element tagElement = (Element) nodes.item(j);
						listElement.add(k, tagElement.getFirstChild()
								.getNodeValue());
						k++;
					}
				}

				listElement.add(k, "fin");

				weekEntryList.add(listElement);
			}
		}

		return weekEntryList;
	}

	public ArrayList<String> getWeekEntryByEmailAndWeek(String email,
			Integer weekNumber, Integer yearNumber) {

		ArrayList<ArrayList<String>> weekEntries = getAllWeekEntries();
		for (int i = 0; i < weekEntries.size(); i++) {
			ArrayList<String> strings = weekEntries.get(i);
			if ((strings.get(0).equals(email))
					&& (strings.get(1).equals(weekNumber.toString()))
					&& (strings.get(2).equals(yearNumber.toString()))) {
				return strings;
			}
		}
		return null;
	}

	public ArrayList<ArrayList<String>> getWeekEntryByEmail(String email) {

		ArrayList<ArrayList<String>> weekEntries = getAllWeekEntries();
		for (int i = 0; i < weekEntries.size(); i++) {
			if ((weekEntries.get(i).get(0).toString().compareTo(email) != 0))
				weekEntries.remove(i);
		}
		return weekEntries;
	}

	public void addPeriodPay(ArrayList<String> periodPayElement) {
		parseXml();

		Element rootElement = xmlFile.getDocumentElement();
		Element newPeriodPay = getweekEntryElement(periodPayElement);
		rootElement.appendChild(newPeriodPay);

		saveXml();

	}

	public void updateWeekEntry(ArrayList<String> week) {
		parseXml();

		if (week.size() != 0) {

			Element oldWeekEntryElement = getWeekEntryElementByEmailAndNumberWeekAndNumberYear(
					week.get(0), week.get(1), week.get(2));
			Element newWeekEntryElement = getweekEntryElement(week);
			xmlFile.getDocumentElement().replaceChild(newWeekEntryElement,
					oldWeekEntryElement);

			saveXml();
		}
	}

	private void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
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

	private void saveXml() {
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domsource = new DOMSource(xmlFile);
			StreamResult result = new StreamResult(new File(
					"database/weekEntries.xml"));
			transformer.transform(domsource, result);
		} catch (TransformerException transException) {
			System.out.println(transException);
		}
	}

	private Element getweekEntryElement(ArrayList<String> weekEntryElement) {
		Element userElement = xmlFile.createElement("weekEntry");

		Element email = xmlFile.createElement("email");
		email.setTextContent(weekEntryElement.get(0));
		userElement.appendChild(email);

		Element weekNumber = xmlFile.createElement("weekNumber");
		weekNumber.setTextContent(weekEntryElement.get(1));
		userElement.appendChild(weekNumber);

		Element yearNumber = xmlFile.createElement("yearNumber");
		yearNumber.setTextContent(weekEntryElement.get(2));
		userElement.appendChild(yearNumber);

		Element isApproved = xmlFile.createElement("state");
		isApproved.setTextContent(weekEntryElement.get(3));
		userElement.appendChild(isApproved);

		/*
		 * Element isSubmitted = xmlFile.createElement("isSubmitted");
		 * isSubmitted.setTextContent(weekEntryElement.get(3));
		 * userElement.appendChild(isSubmitted);
		 * 
		 * Element inProgess = xmlFile.createElement("inProgess");
		 * inProgess.setTextContent(weekEntryElement.get(4));
		 * userElement.appendChild(inProgess);
		 */

		int i = 4;

		if (weekEntryElement.get(i) == "listKilometer") {
			Element kilometers = xmlFile.createElement("kilometers");
			i++;
			do {

				Element kilometer = xmlFile.createElement("kilometer");
				kilometer.setTextContent(weekEntryElement.get(i));
				userElement.appendChild(kilometer);
				i++;
			} while (weekEntryElement.get(i) != "listExpenses");

			userElement.appendChild(kilometers);
		}

		if (weekEntryElement.get(i) == "listExpenses") {
			Element employeeExpenses = xmlFile
					.createElement("employeeExpenses");
			i++;
			do {

				Element employeeExpense = xmlFile
						.createElement("employeeExpense");
				employeeExpense.setTextContent(weekEntryElement.get(i));
				userElement.appendChild(employeeExpense);
				i++;
			} while (weekEntryElement.get(i) != "listHours");

			userElement.appendChild(employeeExpenses);
		}

		if (weekEntryElement.get(i) == "listHours") {
			Element hours = xmlFile.createElement("hours");
			i++;
			do {

				Element hour = xmlFile.createElement("hour");
				hour.setTextContent(weekEntryElement.get(i));
				userElement.appendChild(hour);
				i++;
			} while (weekEntryElement.get(i) != "fin");

			userElement.appendChild(hours);
		}

		return userElement;
	}

	private Element getWeekEntryElementByEmailAndNumberWeekAndNumberYear(
			String email, String weekNumber, String yearNumber) {
		Element docElement = xmlFile.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("weekEntry");

		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element weekEntryElement = (Element) nodeList.item(i);

				Element emailElement = (Element) weekEntryElement
						.getElementsByTagName("email").item(0);
				String emailString = emailElement.getFirstChild()
						.getNodeValue();

				Element weekNumberElement = (Element) weekEntryElement
						.getElementsByTagName("weekNumber").item(0);
				String weekNumberString = weekNumberElement.getFirstChild()
						.getNodeValue();

				Element yearNumberElement = (Element) weekEntryElement
						.getElementsByTagName("yearNumber").item(0);
				String yearNumberString = yearNumberElement.getFirstChild()
						.getNodeValue();

				if (emailString.equals(email)
						&& weekNumberString.equals(weekNumber)
						&& yearNumberString.equals(yearNumber))
					return weekEntryElement;
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

}
