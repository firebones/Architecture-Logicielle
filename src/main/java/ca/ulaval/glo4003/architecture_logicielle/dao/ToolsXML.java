package ca.ulaval.glo4003.architecture_logicielle.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ca.ulaval.glo4003.architecture_logicielle.model.Employee;

public class ToolsXML {
	
	
	
	
	public String getStringValue( Element objet, String strTag) {
		Node nValeur = (Node) objet.getElementsByTagName(strTag).item(0).getFirstChild();
		return nValeur.getNodeValue();
	}
	
	
}
