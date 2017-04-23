package ua.nure.shevchenko.SummaryTask3.util;

import java.util.Collections;
import java.util.Comparator;

import ua.nure.shevchenko.SummaryTask3.entity.Bank;
import ua.nure.shevchenko.SummaryTask3.entity.ContributionsCollection;

public class Sorter {

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
}