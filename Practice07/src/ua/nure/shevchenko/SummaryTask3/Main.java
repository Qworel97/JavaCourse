package ua.nure.shevchenko.SummaryTask3;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import ua.nure.shevchenko.SummaryTask3.controller.DOMController;
import ua.nure.shevchenko.SummaryTask3.controller.SAXController;
import ua.nure.shevchenko.SummaryTask3.controller.STAXController;
import ua.nure.shevchenko.SummaryTask3.entity.ContributionsCollection;
import ua.nure.shevchenko.SummaryTask3.util.Sorter;

public class Main {
	public static void usage() {
		System.out.println("Usage:\njava -jar ST3ExampleSimple.jar xmlFileName");
		System.out.println("java ua.nure.shevchenko.Practice7.Main xmlFileName");
	}
	
	public static void main(String[] args){
		
		if (args.length != 1) {
			usage();
			return;
		}
		
		String xmlFileName = "input.xml";
		System.out.println("Input ==> " + xmlFileName);
		
		DOMController domController = new DOMController(xmlFileName);
		try {
			domController.parse(true);
		} catch (DOMException | ParserConfigurationException | SAXException
				| IOException e) {
			System.err.println(e.getMessage());
		}
		ContributionsCollection cc = domController.getContributionsCollection();

		Sorter.sortBankByName(cc);
		
		String outputXmlFile = "output.dom.xml";
		try {
			DOMController.saveToXML(cc, outputXmlFile);
		} catch (DOMException | ParserConfigurationException
				| TransformerException | DatatypeConfigurationException
				| ParseException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Output ==> " + outputXmlFile);

		SAXController saxController = new SAXController(xmlFileName);
		try {
			saxController.parse(true);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println(e.getMessage());
		}
		cc = saxController.getContributionsCollection();
		
		Sorter.sortBankByAmmountOnDeposit(cc);
		
		outputXmlFile = "output.sax.xml";
		
		try {
			DOMController.saveToXML(cc, outputXmlFile);
		} catch (DOMException | ParserConfigurationException
				| TransformerException | DatatypeConfigurationException
				| ParseException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Output ==> " + outputXmlFile);
		
		STAXController staxController = new STAXController(xmlFileName);
		try {
			staxController.parse();
		} catch (ParserConfigurationException | SAXException | IOException
				| XMLStreamException e) {
			System.err.println(e.getMessage());
		}
		cc = staxController.getContributionsCollection();
		
		Sorter.sortBankByProfitability(cc);
		
		outputXmlFile = "output.stax.xml";
		try {
			DOMController.saveToXML(cc, outputXmlFile);
		} catch (DOMException | ParserConfigurationException
				| TransformerException | DatatypeConfigurationException
				| ParseException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Output ==> " + outputXmlFile);
	}

}