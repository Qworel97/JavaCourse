package ua.nure.shevchenko.Practice4.part3;

import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.shevchenko.Practice4.Part3;


public class Part3Test {
	
	private static final Pattern PATTERN_CHAR;
	private static final Pattern PATTERN_STRING;
	private static final Pattern PATTERN_INT;
	private static final Pattern PATTERN_DOUBLE;

	static {
		PATTERN_CHAR = Pattern.compile("(?<=(\\s|'|^))\\S(?=\\s)",
				Pattern.UNICODE_CHARACTER_CLASS);
		PATTERN_STRING = Pattern.compile("(?<=\\W)(\\p{L}+){2,}(?=\\W)",
				Pattern.UNICODE_CHARACTER_CLASS);
		PATTERN_INT = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
		PATTERN_DOUBLE = Pattern.compile("(?<=\\s|^)\\d+[.]\\d*(?=\\s|$)"
				+ "|(?<=\\s|^)\\d*[.]\\d+(?=\\s|$)");
	}

	@Test
	public void testMatchChar(){
		String actual = Part3.match(PATTERN_CHAR, "a b cdf 5.6 .05");
		String expected = "a b ";
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testMatchString(){
		String actual = Part3.match(PATTERN_STRING, "a b cdf 5.6 .05");
		String expected = "cdf ";
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testMain() throws IOException {
		Part3 p = new Part3();
		Part3.main(null);
	}
}
