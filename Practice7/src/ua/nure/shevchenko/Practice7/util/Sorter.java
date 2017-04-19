package ua.nure.shevchenko.Practice7.util;

import java.util.Collections;
import java.util.Comparator;

import ua.nure.shevchenko.Practice7.constants.Constants;
import ua.nure.shevchenko.Practice7.controller.DOMController;
import ua.nure.shevchenko.Practice7.entity.Bank;
import ua.nure.shevchenko.Practice7.entity.ContributionsCollection;

/**
 * Contains static methods for sorting.
 * 
 * @author D.Kolesnikov
 * 
 */
public class Sorter {

	// //////////////////////////////////////////////////////////
	// these are comparators
	// //////////////////////////////////////////////////////////

	public static final Comparator<Bank> SORT_BANKS_BY_NAME = new Comparator<Bank>() {
		@Override
		public int compare(Bank o1, Bank o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	public static final Comparator<Bank> SORT_BANKS_BY_AMMOUNTONDEPOSIT = new Comparator<Bank>() {
		@Override
		public int compare(Bank o1, Bank o2) {
			return o1.getAmountOnDeposit().compareTo(o2.getAmountOnDeposit());
		}
	};

	public static final Comparator<Bank> SORT_BANKS_BY_PROFITABILITY = new Comparator<Bank>() {
		@Override
		public int compare(Bank o1, Bank o2) {
			return o1.getProfitability().compareTo(o2.getProfitability());
		}
	};

	public static final void sortBankByName(ContributionsCollection cc) {
		Collections.sort(cc.getBanks(), SORT_BANKS_BY_NAME);
	}
	
	public static final void sortBankByAmmountOnDeposit(ContributionsCollection cc) {
		Collections.sort(cc.getBanks(), SORT_BANKS_BY_AMMOUNTONDEPOSIT);
	}
	
	public static final void sortBankByProfitability(ContributionsCollection cc) {
		Collections.sort(cc.getBanks(), SORT_BANKS_BY_PROFITABILITY);
	}



	public static void main(String[] args) throws Exception {
		// Test.xml --> Test object
		DOMController domController = new DOMController(
				Constants.VALID_XML_FILE);
		domController.parse(false);
		/*
		ContributionsCollection test = domController.ge();

		System.out.println("====================================");
		System.out.println(test);
		System.out.println("====================================");

		System.out.println("====================================");
		Sorter.sortQuestionsByQuestionText(test);
		System.out.println(test);
		System.out.println("====================================");

		System.out.println("====================================");
		Sorter.sortAnswersByContent(test);
		System.out.println(test);
		*/
	}
}