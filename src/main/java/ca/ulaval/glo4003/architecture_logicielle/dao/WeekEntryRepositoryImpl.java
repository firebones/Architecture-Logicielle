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

import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntry;
import ca.ulaval.glo4003.architecture_logicielle.model.WeekEntryRepository;


public class WeekEntryRepositoryImpl implements WeekEntryRepository {
	private Document doc;
	
	public ArrayList<WeekEntry> getAllWeekEntries() {
		ArrayList<WeekEntry> weekEntryList = new ArrayList<WeekEntry>();
		
		parseXml();
		
		Element docElement = doc.getDocumentElement();
		NodeList nodeList = docElement.getElementsByTagName("weekEntry");
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				WeekEntry weekEntry = getWeekEntry(element);
				weekEntryList.add(weekEntry);
			}
		}
		
		return weekEntryList;
	}
	
	private WeekEntry getWeekEntry(Element element) {
		
		WeekEntry weekEntry = new WeekEntry();
		weekEntry.setWeekNumber(getStringValue(element, "weekNumber"));
		weekEntry.setStartDate(getStringValue(element, "startDate"));
		weekEntry.setEndDate(getStringValue(element, "endDate"));
		weekEntry.setEmail(getStringValue(element, "email"));
		
		NodeList nodeList = element.getElementsByTagName("kilometer");
		List<Integer> kilometerEntries =  new ArrayList<Integer>(7);
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				kilometerEntries.add(Integer.parseInt(tagElement.getFirstChild().getNodeValue()));
				
			}
		}
		
		nodeList = element.getElementsByTagName("employeeExpense");
		List<Double> employeeExpenseEntries = new ArrayList<Double>(7);
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				employeeExpenseEntries.add(Double.parseDouble(tagElement.getFirstChild().getNodeValue()));
				
			}
		}
		
		nodeList = element.getElementsByTagName("hour");
		List<Double> hourEntries = new ArrayList<Double>(7);
		
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element tagElement = (Element) nodeList.item(i);
				hourEntries.add(Double.parseDouble(tagElement.getFirstChild().getNodeValue()));
				
			}
		}
		
		weekEntry.setKilometersEntries(kilometerEntries);
		weekEntry.setEmployeeExpensesEntries(employeeExpenseEntries);
		weekEntry.setHoursEntries(hourEntries);
		
		return weekEntry;
	}
	
	public WeekEntry getWeekEntryByEmailAndWeek(String email, String weekNumber) {
	
		ArrayList<WeekEntry> weekEntries = getAllWeekEntries();
		for (int i = 0; i < weekEntries.size(); i++) {
				if ((weekEntries.get(i).getEmail().toString().compareTo(email) == 0) && (weekEntries.get(i).getWeekNumber().toString().compareTo(weekNumber) == 0))
					return weekEntries.get(i);
		}
		return null;
	}

	
	private void parseXml() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(new File("database/weekEntries.xml"));
		} catch (ParserConfigurationException parseE) {
			System.out.println(parseE);
		} catch (SAXException saxE) {
			System.out.println(saxE);
		} catch (IOException ioE) {
			System.out.println(ioE);
		}
	}

	@Override
	public ArrayList<String> getExpenses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getKilometers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getEmployeeExpenses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getHours() {
		// TODO Auto-generated method stub
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
