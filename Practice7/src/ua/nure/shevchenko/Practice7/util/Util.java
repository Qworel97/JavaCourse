package ua.nure.shevchenko.Practice7.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import ua.nure.shevchenko.Practice7.constants.Constants;

public class Util {

	public static XMLGregorianCalendar fromStringToXMLGregorian(String text) throws DatatypeConfigurationException, ParseException{
		DateFormat format = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date = format.parse(text);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
	}
	
	public static String fromXMLGregorianString(XMLGregorianCalendar xmlGregorianCalendar) throws DatatypeConfigurationException, ParseException{
		Calendar calendar = xmlGregorianCalendar.toGregorianCalendar();
		SimpleDateFormat fmt = new SimpleDateFormat(Constants.DATE_FORMAT);
	    fmt.setCalendar(calendar);
		return fmt.format(calendar.getTime());
	}
}
