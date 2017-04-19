package ua.nure.shevchenko.Practice7.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.shevchenko.Practice7.constants.Constants;
import ua.nure.shevchenko.Practice7.constants.XML;
import ua.nure.shevchenko.Practice7.entity.ContributionsCollection;
import ua.nure.shevchenko.Practice7.entity.Bank;
import ua.nure.shevchenko.Practice7.entity.Types;
import ua.nure.shevchenko.Practice7.util.Util;

public class STAXController extends DefaultHandler {

	private String xmlFileName;

	// main container
	private ContributionsCollection cc;

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
		
		// current element name holder
		String currentElement = null;
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(
				new StreamSource(xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			// skip any empty content
			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			// handler for start tags
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if (XML.CONTRIBUTIONSCOLLECTION.equalsTo(currentElement)) {
					cc = new ContributionsCollection();
					continue;
				}

				if (XML.BANK.equalsTo(currentElement)) {
					bank = new Bank();
					continue;
				}

			}

			// handler for contents
			if (event.isCharacters()) {
				Characters characters = event.asCharacters();


				if (XML.NAME.equalsTo(currentElement)) {
					bank.setName(characters.getData());
					continue;
				}

				if (XML.COUNTRY.equalsTo(currentElement)) {
					bank.setCountry(characters.getData());
					continue;
				}
				
				if (XML.TYPE.equalsTo(currentElement)) {
					bank.setType(Types.fromValue(characters.getData()));
					continue;
				}
				
				if (XML.DEPOSITOR.equalsTo(currentElement)) {
					bank.setDepositor(characters.getData());
					continue;
				}
				
				if (XML.ACCOUNTID.equalsTo(currentElement)) {
					bank.setAccountId(Integer.parseInt(characters.getData()));
					continue;
				}
				
				if (XML.AMOUNTONDEPOSIT.equalsTo(currentElement)) {
					bank.setAmountOnDeposit(BigDecimal.valueOf(Double.parseDouble(characters.getData())));
					continue;
				}
				
				if (XML.PROFITABILITY.equalsTo(currentElement)) {
					bank.setProfitability(BigDecimal.valueOf(Double.parseDouble(characters.getData())));
					continue;
				}
				
				if (XML.TIMECONSTRAINTS.equalsTo(currentElement)) {
					try {
						bank.setTimeConstraints(Util.fromStringToXMLGregorian(characters.getData()));
					} catch (DatatypeConfigurationException | ParseException e) {
						System.err.println(e.getMessage());
					}
					continue;
				}
			}

			// handler for end tags
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.BANK.equalsTo(localName)) {
					cc.getBanks().add(bank);
					continue;
				}
			}
		}
		reader.close();
	}

	public static void main(String[] args) throws Exception {

		// try to parse (valid) XML file (success)
		STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
		staxContr.parse(); // <-- do parse (success)

		// obtain container
		ContributionsCollection cc = staxContr.getContributionsCollection();

		// we have Test object at this point:
		System.out.println("====================================");
		System.out.print("Here is the test: \n" + cc);
		System.out.println("====================================");
	}
}