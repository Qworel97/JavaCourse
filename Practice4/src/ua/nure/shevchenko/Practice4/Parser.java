package ua.nure.shevchenko.Practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser implements Iterable<String> {

	private static final Pattern PATTERN_LINE;

	private String[] array;
	private int size;

	static {
		PATTERN_LINE = Pattern.compile("\\p{Lu}([^.!?]+)[.]");
	}

	{
		array = new String[31582];
		size = 0;
	}

	public Parser(String fileName, String encoding) {
		String input = Util.readFile(fileName, encoding);
		Matcher m = PATTERN_LINE.matcher(input);
		while (m.find()) {
				array[size++] = input.substring(m.start(), m.end())
						.replace(System.lineSeparator(), " ")
						.replace("  ", " ");
			}
		}

	@Override
	public Iterator<String> iterator() {
		return (Iterator<String>)new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<String> {
		private int currentIndex = -1;

		public boolean hasNext() {
			return currentIndex < size - 1;
		}

		public String next() {
			if (currentIndex < size) {
				if (array[currentIndex + 1] != null) {
					return array[++currentIndex];
				} else {
					return null;
				}
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}