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
		PATTERN_CHAR = Pattern.compile("(?<=(\\s|'|^))\\S(?=\\s)",
				Pattern.UNICODE_CHARACTER_CLASS);
		PATTERN_STRING = Pattern.compile("(?<=\\W)(\\p{L}+){2,}(?=\\W)",
				Pattern.UNICODE_CHARACTER_CLASS);
		PATTERN_INT = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
		PATTERN_DOUBLE = Pattern.compile("(?<=\\s|^)\\d+[.]\\d*(?=\\s|$)"
				+ "|(?<=\\s|^)\\d*[.]\\d+(?=\\s|$)");
	}

	public static void main(String[] args) {
		String input = Util.readFile(DATA, "CP1251");
		Scanner scan = new Scanner(System.in);
			while (scan.hasNext()) {
				String temp = scan.nextLine();
				switch (temp) {
				case "char": {
					System.out.println(match(PATTERN_CHAR, input));
					break;
				}
				case "String": {
					System.out.println(match(PATTERN_STRING, input));
					break;
				}
				case "int": {
					System.out.println(match(PATTERN_INT, input));
					break;
				}
				case "double": {
					System.out.println(match(PATTERN_DOUBLE, input));
					break;
				}
				case "stop": {
					scan.close();
					return;
				}
				}
			}	
	}

	public static String match(Pattern pat, String input) {
		StringBuilder res = new StringBuilder();
		Matcher m = pat.matcher(input);
		while (m.find()) {
			res.append(input.substring(m.start(), m.end()) + " ");
		}
		return res.toString();
	}

}