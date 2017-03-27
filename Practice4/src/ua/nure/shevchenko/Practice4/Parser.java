package ua.nure.shevchenko.Practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser implements Iterable<String> {

	private static final Pattern PATTERN_LINE;

	private String[] array;
	private int size;

	static {
		PATTERN_LINE = Pattern.compile("\\p{Lu}([^.!?]+)[.]");
	}

	{
		array = new String[8];
		size = 0;
	}

	public Parser(String fileName, String encoding) {
		String input = Util.readFile(fileName, encoding);
		Matcher m = PATTERN_LINE.matcher(input);
		while (m.find()) {
			if (size == array.length - 1) {
				String[] newArray = new String[2 * array.length];
				System.arraycopy(array, 0, newArray, 0, size);
				newArray[++size] = input.substring(m.start(), m.end());
				array = newArray;
			} else {
				array[size++] = input.substring(m.start(), m.end());
			}
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