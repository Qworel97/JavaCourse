package ua.nure.shevchenko.SummaryTask3.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.shevchenko.SummaryTask3.constants.Constants;
import ua.nure.shevchenko.SummaryTask3.constants.XML;
import ua.nure.shevchenko.SummaryTask3.entity.Bank;
import ua.nure.shevchenko.SummaryTask3.entity.ContributionsCollection;
import ua.nure.shevchenko.SummaryTask3.entity.Types;
import ua.nure.shevchenko.SummaryTask3.util.Util;

public class SAXController extends DefaultHandler {
	
	private String xmlFileName;

	private String currentElement;

	private ContributionsCollection cc;
	
	private Bank bank;

	public SAXController(String xmlFileName) {
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
	 *            If true validate XML document against its XML schema. With
	 *            this parameter it is possible make parser validating.
	 */
	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

		saxParserFactory.setNamespaceAware(true);
		
		if (validate) {
			saxParserFactory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			saxParserFactory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		SAXParser saxParser = saxParserFactory.newSAXParser();
		saxParser.parse(xmlFileName, this);
	}

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		throw e;
	};

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

		String elemText = new String(ch, start, length).trim();

		if (elemText.isEmpty()) { 
			return;
		}

		if (XML.NAME.equalsTo(currentElement)) {
			bank.setName(elemText);
			return;
		}

		if (XML.COUNTRY.equalsTo(currentElement)) {
			bank.setCountry(elemText);
			return;
		}
		
		if (XML.TYPE.equalsTo(currentElement)) {
			bank.setType(Types.fromValue(elemText));
			return;
		}
		
		if (XML.DEPOSITOR.equalsTo(currentElement)) {
			bank.setDepositor(elemText);
			return;
		}
		
		if (XML.ACCOUNTID.equalsTo(currentElement)) {
			bank.setAccountId(Integer.parseInt(elemText));
			return;
		}
		
		if (XML.AMOUNTONDEPOSIT.equalsTo(currentElement)) {
			bank.setAmountOnDeposit(BigDecimal.valueOf(Double.parseDouble(elemText)));
			return;
		}
		
		if (XML.PROFITABILITY.equalsTo(currentElement)) {
			bank.setProfitability(BigDecimal.valueOf(Double.parseDouble(elemText)));
			return;
		}
		
		if (XML.TIMECONSTRAINTS.equalsTo(currentElement)) {
			bank.setTimeConstraints(Util.fromStringToXMLGregorian(elemText));
			return;
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (XML.BANK.equalsTo(localName)) {
			cc.getBanks().add(bank);
			return;
		}
	}
}