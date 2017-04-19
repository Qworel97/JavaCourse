package ua.nure.shevchenko.SummaryTask3;

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
	
	public static void main(String[] args) throws Exception {
		/*
		if (args.length != 1) {
			usage();
			return;
		}*/
		
		String xmlFileName = "input.xml";
		System.out.println("Input ==> " + xmlFileName);
		
		////////////////////////////////////////////////////////
		// DOM
		////////////////////////////////////////////////////////
		
		// get
		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);
		ContributionsCollection cc = domController.getContributionsCollection();

		// sort (case 1)
		Sorter.sortBankByName(cc);
		
		// save
		String outputXmlFile = "output.dom.xml";
		DOMController.saveToXML(cc, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);

		////////////////////////////////////////////////////////
		// SAX
		////////////////////////////////////////////////////////
		
		// get
		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse(true);
		cc = saxController.getContributionsCollection();
		
		// sort  (case 2)
		Sorter.sortBankByAmmountOnDeposit(cc);
		
		// save
		outputXmlFile = "output.sax.xml";
		
		// other way: 
		DOMController.saveToXML(cc, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
		
		
		////////////////////////////////////////////////////////
		// StAX
		////////////////////////////////////////////////////////
		
		// get
		STAXController staxController = new STAXController(xmlFileName);
		staxController.parse();
		cc = staxController.getContributionsCollection();
		
		// sort  (case 3)
		Sorter.sortBankByProfitability(cc);
		
		// save
		outputXmlFile = "output.stax.xml";
		DOMController.saveToXML(cc, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);
	}

}