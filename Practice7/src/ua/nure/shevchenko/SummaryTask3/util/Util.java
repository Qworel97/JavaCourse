package ua.nure.shevchenko.SummaryTask3.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ua.nure.shevchenko.SummaryTask3.constants.Constants;

public class Util {

	/**
	 * Creates XMLGregorianCalendar object from String.
	 * 
	 * @param text
	 *            String text.
	 * @return XMLGregorianCalendar object.
	 */
	public static XMLGregorianCalendar fromStringToXMLGregorian(String text) {
		DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date = new Date();
		try {
			date = format.parse(text);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(
					calendar);
		} catch (DatatypeConfigurationException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates String object from XMLGregorianCalendar.
	 * 
	 * @param XMLGregorianCalendar
	 *            XMLGregorianCalendar xmlGregorianCalendar.
	 * @return String object.
	 */
	public static String fromXMLGregorianString(
			XMLGregorianCalendar xmlGregorianCalendar) {
		Calendar calendar = xmlGregorianCalendar.toGregorianCalendar();
		SimpleDateFormat fmt = new SimpleDateFormat(Constants.DATE_FORMAT);
		fmt.setCalendar(calendar);
		return fmt.format(calendar.getTime());
	}
}
