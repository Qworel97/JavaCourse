package ua.nure.shevchenko.SummaryTask3.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.shevchenko.SummaryTask3.constants.XML;
import ua.nure.shevchenko.SummaryTask3.entity.ContributionsCollection;
import ua.nure.shevchenko.SummaryTask3.entity.Bank;
import ua.nure.shevchenko.SummaryTask3.entity.Types;
import ua.nure.shevchenko.SummaryTask3.util.Util;

public class STAXController extends DefaultHandler {

	private String xmlFileName;

	private ContributionsCollection cc;

	/**
	 * Returns current ContributionsCollection
	 * 
	 * @return ContributionsCollection object
	 */
	public ContributionsCollection getContributionsCollection() {
		return cc;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	/**
	 * Parses XML document with StAX (based on event reader). There is no validation during the
	 * parsing.
	 */
	public void parse() throws ParserConfigurationException, SAXException,
			IOException, XMLStreamException {

		Bank bank = null;
		
		String currElem = null;
		
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		
		xmlInputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(
				new StreamSource(xmlFileName));

		while (xmlEventReader.hasNext()) {
			XMLEvent xmlEvent = xmlEventReader.nextEvent();

			if (xmlEvent.isCharacters() && xmlEvent.asCharacters().isWhiteSpace()) {
				continue;
			}

			if (xmlEvent.isStartElement()) {
				StartElement startElem = xmlEvent.asStartElement();
				currElem = startElem.getName().getLocalPart();

				if (XML.CONTRIBUTIONSCOLLECTION.equalsTo(currElem)) {
					cc = new ContributionsCollection();
					continue;
				}

				if (XML.BANK.equalsTo(currElem)) {
					bank = new Bank();
					continue;
				}

			}

			if (xmlEvent.isCharacters()) {
				Characters characters = xmlEvent.asCharacters();


				if (XML.NAME.equalsTo(currElem)) {
					bank.setName(characters.getData());
					continue;
				}

				if (XML.COUNTRY.equalsTo(currElem)) {
					bank.setCountry(characters.getData());
					continue;
				}
				
				if (XML.TYPE.equalsTo(currElem)) {
					bank.setType(Types.fromValue(characters.getData()));
					continue;
				}
				
				if (XML.DEPOSITOR.equalsTo(currElem)) {
					bank.setDepositor(characters.getData());
					continue;
				}
				
				if (XML.ACCOUNTID.equalsTo(currElem)) {
					bank.setAccountId(Integer.parseInt(characters.getData()));
					continue;
				}
				
				if (XML.AMOUNTONDEPOSIT.equalsTo(currElem)) {
					bank.setAmountOnDeposit(BigDecimal.valueOf(Double.parseDouble(characters.getData())));
					continue;
				}
				
				if (XML.PROFITABILITY.equalsTo(currElem)) {
					bank.setProfitability(BigDecimal.valueOf(Double.parseDouble(characters.getData())));
					continue;
				}
				
				if (XML.TIMECONSTRAINTS.equalsTo(currElem)) {
					bank.setTimeConstraints(Util.fromStringToXMLGregorian(characters.getData()));
					continue;
				}
			}

			if (xmlEvent.isEndElement()) {
				EndElement endElem = xmlEvent.asEndElement();
				String localName = endElem.getName().getLocalPart();

				if (XML.BANK.equalsTo(localName)) {
					cc.getBanks().add(bank);
					continue;
				}
			}
		}
		xmlEventReader.close();
	}
}