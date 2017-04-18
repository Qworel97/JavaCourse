package ua.nure.shevchenko.Practice7.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.shevchenko.Practice7.constants.Constants;
import ua.nure.shevchenko.Practice7.constants.XML;
import ua.nure.shevchenko.Practice7.entity.Bank;
import ua.nure.shevchenko.Practice7.entity.ContributionsCollection;
import ua.nure.shevchenko.Practice7.entity.Types;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DOMController {

	private String xmlFileName;

	// main container
	private ContributionsCollection cc;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public ContributionsCollection getTest() {
		return cc;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema.
	 * @throws DatatypeConfigurationException
	 * @throws ParseException 
	 * @throws DOMException 
	 */
	public void parse(boolean validate)
			throws ParserConfigurationException, SAXException, IOException, DatatypeConfigurationException, DOMException, ParseException {

		// obtain DOM parser
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// set properties for Factory

		// XML document contains namespaces
		dbf.setNamespaceAware(true);

		// make parser validating
		if (validate) {
			// turn validation on
			dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

			// turn on xsd validation
			dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder db = dbf.newDocumentBuilder();

		// set error handler
		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				// throw exception if XML document is NOT valid
				throw e;
			}
		});

		// parse XML document
		Document document = db.parse(xmlFileName);

		// get root element
		Element root = document.getDocumentElement();

		// create container
		cc = new ContributionsCollection();

		// obtain questions nodes
		NodeList banksNodes = root.getElementsByTagName(XML.BANK.value());

		// process questions nodes
		for (int j = 0; j < banksNodes.getLength(); j++) {
			Bank bank = getBank(banksNodes.item(j));
			// add question to container
			cc.getBank().add(bank);
		}
	}

	/**
	 * Extracts question object from the question XML node.
	 * 
	 * @param qNode
	 *            Question node.
	 * @return Question object.
	 * @throws DatatypeConfigurationException
	 * @throws ParseException 
	 * @throws DOMException 
	 */
	private Bank getBank(Node qNode) throws DatatypeConfigurationException, DOMException, ParseException {
		Bank question = new Bank();
		Element qElement = (Element) qNode;

		Node qtNode = qElement.getElementsByTagName(XML.NAME.value()).item(0);
		question.setName(qtNode.getTextContent());

		qtNode = qElement.getElementsByTagName(XML.COUNTRY.value()).item(0);
		question.setCountry(qtNode.getTextContent());

		qtNode = qElement.getElementsByTagName(XML.TYPE.value()).item(0);
		question.setType(Types.fromValue(qtNode.getTextContent()));

		qtNode = qElement.getElementsByTagName(XML.DEPOSITOR.value()).item(0);
		question.setDepositor(qtNode.getTextContent());

		qtNode = qElement.getElementsByTagName(XML.ACCOUNTID.value()).item(0);
		question.setAccountId(Integer.parseInt(qtNode.getTextContent()));

		qtNode = qElement.getElementsByTagName(XML.AMOUNTONDEPOSIT.value()).item(0);
		question.setAmountOnDeposit(BigDecimal.valueOf(Double.parseDouble(qtNode.getTextContent())));

		qtNode = qElement.getElementsByTagName(XML.PROFITABILITY.value()).item(0);
		question.setProfitability(BigDecimal.valueOf(Double.parseDouble(qtNode.getTextContent())));

		qtNode = qElement.getElementsByTagName(XML.TIMECONSTRAINTS.value()).item(0);
		DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date = format.parse(qtNode.getTextContent());
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
		question.setTimeConstraints(date2);

		return question;
	}

	public static Document getDocument(ContributionsCollection cc) throws ParserConfigurationException {

		// obtain DOM parser
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// set properties for Factory

		// XML document contains namespaces
		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		// create root element
		Element tElement = document.createElement(XML.CONTRIBUTIONSCOLLECTION.value());

		// add root element
		document.appendChild(tElement);

		// add questions elements
		for (Bank bank : cc.getBank()) {

			// add question
			Element qElement = document.createElement(XML.BANK.value());
			tElement.appendChild(qElement);

			// add question text
			Element qtElement = document.createElement(XML.NAME.value());
			qtElement.setTextContent(bank.getName());
			qElement.appendChild(qtElement);

			qtElement = document.createElement(XML.COUNTRY.value());
			qtElement.setTextContent(bank.getCountry());
			qElement.appendChild(qtElement);

			qtElement = document.createElement(XML.TYPE.value());
			qtElement.setTextContent(bank.getType().toString());
			qElement.appendChild(qtElement);
			
			qtElement = document.createElement(XML.DEPOSITOR.value());
			qtElement.setTextContent(bank.getDepositor());
			qElement.appendChild(qtElement);
			
			qtElement = document.createElement(XML.ACCOUNTID.value());
			qtElement.setTextContent(String.valueOf(bank.getAccountId()));
			qElement.appendChild(qtElement);
			
			qtElement = document.createElement(XML.AMOUNTONDEPOSIT.value());
			qtElement.setTextContent(bank.getAmountOnDeposit().toString());
			qElement.appendChild(qtElement);
			
			qtElement = document.createElement(XML.PROFITABILITY.value());
			qtElement.setTextContent(bank.getProfitability().toString());
			qElement.appendChild(qtElement);
			
			qtElement = document.createElement(XML.TIMECONSTRAINTS.value());
			Calendar calendar = bank.getTimeConstraints().toGregorianCalendar();
			SimpleDateFormat fmt = new SimpleDateFormat(Constants.DATE_FORMAT);
		    fmt.setCalendar(calendar);
		    String dateFormatted = fmt.format(calendar.getTime());
			qtElement.setTextContent(dateFormatted);
			qElement.appendChild(qtElement);
		}

		return document;
	}

	/**
	 * Saves Test object to XML file.
	 * 
	 * @param cc
	 *            Test object to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 */
	public static void saveToXML(ContributionsCollection cc, String xmlFileName)
			throws ParserConfigurationException, TransformerException {
		// Test -> DOM -> XML
		saveToXML(getDocument(cc), xmlFileName);
	}

	/**
	 * Save DOM to XML.
	 * 
	 * @param document
	 *            DOM to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 */
	public static void saveToXML(Document document, String xmlFileName) throws TransformerException {

		StreamResult result = new StreamResult(new File(xmlFileName));

		// set up transformation
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");

		// run transformation
		t.transform(new DOMSource(document), result);
	}

	public static void main(String[] args) throws Exception {

		// try to parse NOT valid XML document with validation on (failed)
		DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
		try {
			// parse with validation (failed)
			domContr.parse(true);
		} catch (SAXException ex) {
			System.err.println("====================================");
			System.err.println("XML not valid");
			System.err.println("Test object --> " + domContr.getTest());
			System.err.println("====================================");
		}

		// try to parse NOT valid XML document with validation off (success)
		domContr.parse(false);

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + domContr.getTest());
		System.out.println("====================================");

		// save test in XML file
		ContributionsCollection cc = domContr.getTest();
		DOMController.saveToXML(cc, Constants.INVALID_XML_FILE + ".dom-result.xml");
	}
}
