package ua.nure.shevchenko.Practice4.part5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import ua.nure.shevchenko.Practice4.Part5;

public class Part5Test {
	@Test
	public void testRU() throws IOException {
		String actual = Part5.getResourceData("apple ru");
		String expected = "€блоко";
		Assert.assertEquals(expected,actual);
	}
	@Test
	public void testEN() throws IOException {
		String actual = Part5.getResourceData("table en");
		String expected = "table";
		Assert.assertEquals(expected,actual);
	}
	@Test
	public void testMain() throws IOException {
		Part5 p5=new Part5();
		InputStream STD_IN = System.in;
		System.setIn(new ByteArrayInputStream(("table ru"+System.lineSeparator()+"table en"+System.lineSeparator()+"apple ru"+System.lineSeparator()+"stop").getBytes("Cp1251")));
        Part5.main(null);
        System.setIn(STD_IN);
	}
}
