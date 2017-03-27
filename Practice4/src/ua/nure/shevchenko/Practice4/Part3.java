package ua.nure.shevchenko.Practice4;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

	private static final String DATA = "part3.txt";

	private static final Pattern PATTERN_CHAR;
	private static final Pattern PATTERN_STRING;
	private static final Pattern PATTERN_INT;
	private static final Pattern PATTERN_DOUBLE;

	static {
		PATTERN_CHAR = Pattern.compile("(?<=(\\s|'|^))\\S(?=\\s)",Pattern.UNICODE_CHARACTER_CLASS);
		PATTERN_STRING = Pattern.compile("(?<=\\W)(\\p{L}+){2,}(?=\\W)",Pattern.UNICODE_CHARACTER_CLASS);
		PATTERN_INT = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
		PATTERN_DOUBLE = Pattern.compile("(?<=\\s|^)\\d+[.]\\d*(?=\\s|$)" + "|(?<=\\s|^)\\d*[.]\\d+(?=\\s|$)");
	}

	public static void main(String[] args) {
		String input = Util.readFile(DATA,"CP1251");
		Scanner scan = new Scanner(System.in);
		try {
			String temp = scan.nextLine();
			while (!temp.equals("stop")) {
				switch (temp) {
				case "char": {
					match(PATTERN_CHAR, input);
					break;
				}
				case "String": {
					match(PATTERN_STRING, input);
					break;
				}
				case "int": {
					match(PATTERN_INT, input);
					break;
				}
				case "double": {
					match(PATTERN_DOUBLE, input);
					break;
				}
				}
				temp = scan.nextLine();
			}
		} catch (NoSuchElementException nsee) {
			System.err.println(nsee.getMessage());
		} finally {
			scan.close();
		}
	}

	public static void match(Pattern pat, String input) {
		Matcher m = pat.matcher(input);
		while (m.find()) {
			System.out.print(input.substring(m.start(), m.end()) + " ");
		}
		System.out.println();
	}

}