package ua.nure.shevchenko.Practice7.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.shevchenko.Practice7.constants.Constants;
import ua.nure.shevchenko.Practice7.constants.XML;
import ua.nure.shevchenko.Practice7.entity.Bank;
import ua.nure.shevchenko.Practice7.entity.ContributionsCollection;
import ua.nure.shevchenko.Practice7.entity.Types;
import ua.nure.shevchenko.Practice7.util.Util;

public class SAXController extends DefaultHandler {
	
	private String xmlFileName;

	// current element name holder
	private String currentElement;

	// main container
	private ContributionsCollection cc;
	
	private Bank bank;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document.
	 * 
	 * @param validate
	 *            If true validate XML document against its XML schema. With
	 *            this parameter it is possible make parser validating.
	 */
	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {
		
		// obtain sax parser factory
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// XML document contains namespaces
		factory.setNamespaceAware(true);
		
		// set validation
		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	// ///////////////////////////////////////////////////////////
	// ERROR HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		// if XML document not valid just throw exception
		throw e;
	};

	public ContributionsCollection getContributionsCollection() {
		return cc;
	}

	// ///////////////////////////////////////////////////////////
	// CONTENT HANDLER IMPLEMENTATION
	// ///////////////////////////////////////////////////////////


	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = localName;

		if (XML.CONTRIBUTIONSCOLLECTION.equalsTo(currentElement)) {
			cc = new ContributionsCollection();
			return;
		}

		if (XML.BANK.equalsTo(currentElement)) {
			bank = new Bank();
			return;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String elementText = new String(ch, start, length).trim();

		// return if content is empty
		if (elementText.isEmpty()) { 
			return;
		}

		if (XML.NAME.equalsTo(currentElement)) {
			bank.setName(elementText);
			return;
		}

		if (XML.COUNTRY.equalsTo(currentElement)) {
			bank.setCountry(elementText);
			return;
		}
		
		if (XML.TYPE.equalsTo(currentElement)) {
			bank.setType(Types.fromValue(elementText));
			return;
		}
		
		if (XML.DEPOSITOR.equalsTo(currentElement)) {
			bank.setDepositor(elementText);
			return;
		}
		
		if (XML.ACCOUNTID.equalsTo(currentElement)) {
			bank.setAccountId(Integer.parseInt(elementText));
			return;
		}
		
		if (XML.AMOUNTONDEPOSIT.equalsTo(currentElement)) {
			bank.setAmountOnDeposit(BigDecimal.valueOf(Double.parseDouble(elementText)));
			return;
		}
		
		if (XML.PROFITABILITY.equalsTo(currentElement)) {
			bank.setProfitability(BigDecimal.valueOf(Double.parseDouble(elementText)));
			return;
		}
		
		if (XML.TIMECONSTRAINTS.equalsTo(currentElement)) {
			try {
				bank.setTimeConstraints(Util.fromStringToXMLGregorian(elementText));
			} catch (DatatypeConfigurationException | ParseException e) {
				System.err.println(e.getMessage());
			}
			return;
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (XML.BANK.equalsTo(localName)) {
			// just add question to container
			cc.getBanks().add(bank);
			return;
		}
	}

	public static void main(String[] args) throws Exception {

		// try to parse valid XML file (success)
		SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);
		
		// do parse with validation on (success)
		saxContr.parse(true);
		
		// obtain container
		ContributionsCollection cc = saxContr.getContributionsCollection();

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + cc);
		System.out.println("====================================");

		// now try to parse NOT valid XML (failed)
		saxContr = new SAXController(Constants.INVALID_XML_FILE);
		try {			
			// do parse with validation on (failed)
			saxContr.parse(true);
		} catch (Exception ex) {
			System.err.println("====================================");
			System.err.println("Validation is failed:\n" + ex.getMessage());
			System.err
					.println("Try to print test object:" + saxContr.getContributionsCollection());
			System.err.println("====================================");
		}

		// and now try to parse NOT valid XML with validation off (success)
		saxContr.parse(false);		

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + saxContr.getContributionsCollection());
		System.out.println("====================================");
	}
}