package ua.nure.shevchenko.Practice7.constants;

public enum XML {
	// elements names
	CONTRIBUTIONSCOLLECTION("ContributionsCollection"), BANK("Bank"),
	NAME("Name"), COUNTRY("Country"),
	TYPE("Type"), DEPOSITOR("Depositor"),
	ACCOUNTID("AccountId"),AMOUNTONDEPOSIT("AmountOnDeposit"),
	PROFITABILITY("Profitability"),TIMECONSTRAINTS("TimeConstraints");

	private String value;

	XML(String value) {
		this.value = value;
	}
	
	/**
	 * Determines if a name is equal to the string value wrapped by this enum element.<br/>
	 * If a SAX/StAX parser make all names of elements and attributes interned you can use
	 * <pre>return value == name;</pre> instead <pre>return value.equals(name);</pre>
	 * @param name string to compare with value. 
	 * @return value.equals(name)
	 */
	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
