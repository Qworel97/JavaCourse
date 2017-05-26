package ua.nure.shevchenko.SummaryTask3.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.shevchenko.SummaryTask3.constants.Constants;
import ua.nure.shevchenko.SummaryTask3.constants.XML;
import ua.nure.shevchenko.SummaryTask3.entity.Bank;
import ua.nure.shevchenko.SummaryTask3.entity.ContributionsCollection;
import ua.nure.shevchenko.SummaryTask3.entity.Types;
import ua.nure.shevchenko.SummaryTask3.util.Util;

import javax.xml.datatype.DatatypeConfigurationException;

public class DOMController {

	private String xmlFileName;

	private ContributionsCollection cc;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Returns current ContributionsCollection
	 * 
	 * @return ContributionsCollection object
	 */
	public ContributionsCollection getContributionsCollection() {
		return cc;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void parse(boolean validate) throws ParserConfigurationException,
			SAXException, IOException {

		DocumentBuilderFactory docBuilerFactory = DocumentBuilderFactory.newInstance();

		docBuilerFactory.setNamespaceAware(true);

		if (validate) {
			docBuilerFactory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

			docBuilerFactory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder docBuilder = docBuilerFactory.newDocumentBuilder();

		docBuilder.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e;
			}
		});

		Document document = docBuilder.parse(xmlFileName);

		Element rootElem = document.getDocumentElement();

		cc = new ContributionsCollection();

		NodeList banksNodes = rootElem.getElementsByTagName(XML.BANK.value());

		for (int j = 0; j < banksNodes.getLength(); j++) {
			Bank bank = new Bank();
			try {
				bank = getBank(banksNodes.item(j));
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			} catch (DatatypeConfigurationException e) {
				System.err.println(e.getMessage());
			}
			cc.getBanks().add(bank);
		}
	}

	/**
	 * Extracts bank object from the bank XML node.
	 * 
	 * @param node
	 *            Bank node.
	 * @return Bank object.
	 * @throws DatatypeConfigurationException
	 * @throws ParseException
	 */
	private Bank getBank(Node node) throws DatatypeConfigurationException,
			ParseException {
		Bank bank = new Bank();
		Element elem = (Element) node;

		Node dataNode = elem.getElementsByTagName(XML.NAME.value()).item(0);
		bank.setName(dataNode.getTextContent());

		dataNode = elem.getElementsByTagName(XML.COUNTRY.value()).item(0);
		bank.setCountry(dataNode.getTextContent());

		dataNode = elem.getElementsByTagName(XML.TYPE.value()).item(0);
		bank.setType(Types.fromValue(dataNode.getTextContent()));

		dataNode = elem.getElementsByTagName(XML.DEPOSITOR.value()).item(0);
		bank.setDepositor(dataNode.getTextContent());

		dataNode = elem.getElementsByTagName(XML.ACCOUNTID.value()).item(0);
		bank.setAccountId(Integer.parseInt(dataNode.getTextContent()));

		dataNode = elem.getElementsByTagName(XML.AMOUNTONDEPOSIT.value())
				.item(0);
		bank.setAmountOnDeposit(BigDecimal.valueOf(Double
				.parseDouble(dataNode.getTextContent())));

		dataNode = elem.getElementsByTagName(XML.PROFITABILITY.value()).item(
				0);
		bank.setProfitability(BigDecimal.valueOf(Double.parseDouble(dataNode
				.getTextContent())));

		dataNode = elem.getElementsByTagName(XML.TIMECONSTRAINTS.value())
				.item(0);
		bank.setTimeConstraints(Util.fromStringToXMLGregorian(dataNode
				.getTextContent()));

		return bank;
	}

	/**
	 * Creates XML document to save it from ContributionsCollection.
	 * 
	 * @param cc
	 *            ContributionsCollection cc.
	 * @return Document object.
	 * @throws ParserConfigurationException
	 * @throws DatatypeConfigurationException
	 * @throws ParseException
	 */
	public static Document getDocument(ContributionsCollection cc)
			throws ParserConfigurationException,
			DatatypeConfigurationException, ParseException {

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

		docBuilderFactory.setNamespaceAware(true);

		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		Element textElem = doc.createElement(XML.CONTRIBUTIONSCOLLECTION
				.value());

		doc.appendChild(textElem);

		for (Bank bank : cc.getBanks()) {

			Element elem = doc.createElement(XML.BANK.value());
			textElem.appendChild(elem);

			Element qtElement = doc.createElement(XML.NAME.value());
			qtElement.setTextContent(bank.getName());
			elem.appendChild(qtElement);

			qtElement = doc.createElement(XML.COUNTRY.value());
			qtElement.setTextContent(bank.getCountry());
			elem.appendChild(qtElement);

			qtElement = doc.createElement(XML.TYPE.value());
			qtElement.setTextContent(bank.getType().value());
			elem.appendChild(qtElement);

			qtElement = doc.createElement(XML.DEPOSITOR.value());
			qtElement.setTextContent(bank.getDepositor());
			elem.appendChild(qtElement);

			qtElement = doc.createElement(XML.ACCOUNTID.value());
			qtElement.setTextContent(String.valueOf(bank.getAccountId()));
			elem.appendChild(qtElement);

			qtElement = doc.createElement(XML.AMOUNTONDEPOSIT.value());
			qtElement.setTextContent(bank.getAmountOnDeposit().toString());
			elem.appendChild(qtElement);

			qtElement = doc.createElement(XML.PROFITABILITY.value());
			qtElement.setTextContent(bank.getProfitability().toString());
			elem.appendChild(qtElement);

			qtElement = doc.createElement(XML.TIMECONSTRAINTS.value());
			qtElement.setTextContent(Util.fromXMLGregorianString(bank
					.getTimeConstraints()));
			elem.appendChild(qtElement);
		}

		return doc;
	}

	/**
	 * Saves Test object to XML file.
	 * 
	 * @param cc
	 *            Object to be saved.
	 * @param xmlFileName
	 *            Output XML file name.
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws DatatypeConfigurationException
	 * @throws ParseException
	 */
	public static void saveToXML(ContributionsCollection cc, String xmlFileName)
			throws ParserConfigurationException, TransformerException,
			DatatypeConfigurationException, ParseException {
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
	public static void saveToXML(Document document, String xmlFileName)
			throws TransformerException {

		StreamResult res = new StreamResult(new File(xmlFileName));

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		transformer.transform(new DOMSource(document), res);
	}
}
