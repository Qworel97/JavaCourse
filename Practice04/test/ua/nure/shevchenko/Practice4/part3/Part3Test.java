package ua.nure.shevchenko.Practice4.part3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
		PATTERN_CHAR = Pattern.compile("(?<=(\\s|'|^))\\p{L}(?=\\s|$)",
				Pattern.UNICODE_CHARACTER_CLASS);
		PATTERN_STRING = Pattern.compile("(?<=\\W)(\\p{L}+){2,}(?=\\W)",
				Pattern.UNICODE_CHARACTER_CLASS);
		PATTERN_INT = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
		PATTERN_DOUBLE = Pattern.compile("(?<=\\s|^)\\d+[.]\\d*(?=\\s|$)"
				+ "|(?<=\\s|^)\\d*[.]\\d+(?=\\s|$)");
	}
	
	@Test
	public void testChar() throws IOException {
		String actual = Part3.match(PATTERN_CHAR, "a b 43.43 c 432 15. 89 .98 abc û");
		String expected = "a b c û ";
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testInt() throws IOException {

		String actual = Part3.match(PATTERN_INT, "a b 43.43 c 432 15. 89 .98 abc û");
		String expected = "432 89 ";
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testDouble() throws IOException {

		String actual = Part3.match(PATTERN_DOUBLE, "a b 43.43 c 432 15. 89 .98 abc û");
		String expected = "43.43 15. .98 ";
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testString() throws IOException {

		String actual = Part3.match(PATTERN_STRING, "a b 43.43 c 432 15. 89 .98 abc û");
		String expected = "abc ";
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testMain() throws IOException {
		Part3 p3= new Part3();
		InputStream STD_IN = System.in;
		System.setIn(new ByteArrayInputStream(("char"+System.lineSeparator()+"String"+System.lineSeparator()+"int"+System.lineSeparator()+"double"+System.lineSeparator()+"stop").getBytes("Cp1251")));
        Part3.main(null);
        System.setIn(STD_IN);
	}
}
