package ua.nure.shevchenko.Practice4.part4;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;

import ua.nure.shevchenko.Practice4.Parser;
import ua.nure.shevchenko.Practice4.Part4;

public class Part4Test {
	private static final String Iterator = null;

	@Test
	public void testParser() throws IOException {
		Parser parser = new Parser("part4.txt", "Cp1251");
		Iterator<String> iter = parser.iterator();
		String[] exp = { "Класс должен разбирать.",
				"Под предложением понимать.", "Du hast mich gefragt.",
				"Du hast mich gefragt und ich hab nichts gesagt." };
		int i = 0;
		while (iter.hasNext()) {
			Assert.assertEquals(exp[i], iter.next());
			i++;
		}

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testError() throws IOException {
		Parser parser = null;
		parser = new Parser("part4.txt", "Cp1251");
		Iterator<String> iter = parser.iterator();
		while (iter.hasNext()) {
			iter.remove();
		}
	}

	@Test
	public void testMain() throws IOException {
		Part4 p4 = new Part4();
		Part4.main(null);
	}
}
