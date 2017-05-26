package ua.nure.shevchenko.Practice3;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	private static final Pattern PATTERN;
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String SEPARATOR = ";";

	public static void main(String[] args) {
	}

	static {
		PATTERN = Pattern.compile("(?m)(\\S+)[" + SEPARATOR + "](\\S+\\s\\S+)[" + SEPARATOR + "]([\\w|\\d]+@)(\\S+$)");
	}

	public static String convert1(String input) {
		StringBuilder sb = new StringBuilder();
		Matcher m = PATTERN.matcher(input);
		while (m.find()) {
			sb.append(m.group(1) + " ==> " + m.group(3) + m.group(4) + LINE_SEPARATOR);
		}
		return sb.toString();
	}

	public static String convert2(String input) {
		StringBuilder sb = new StringBuilder();
		Matcher m = PATTERN.matcher(input);
		while (m.find()) {
			sb.append(m.group(2) + " (email: " + m.group(3) + m.group(4) + ")" + LINE_SEPARATOR);
		}
		return sb.toString();
	}

	public static String convert3(String input) {
		StringBuilder s = new StringBuilder();
		Matcher m = PATTERN.matcher(input);
		while (m.find()) {
			String temp1 = m.group(4);
			if (!s.toString().contains(temp1)) {
				s.append(m.group(4) + " ==> " + m.group(1));
			}
			Matcher m2 = PATTERN.matcher(input);
			while (m2.find()) {
				String temp2 = m2.group(4);
				if (!s.toString().contains(m2.group(1)) && temp1.equals(temp2)) {
					s.append(", " + m2.group(1));
				}
			}
			s.append(LINE_SEPARATOR);
		}
		return s.toString();
	}

	public static String convert4(String input) {
		StringBuilder sb = new StringBuilder();
		sb.append("Login;Name;Email;Password" + LINE_SEPARATOR);
		Matcher m = PATTERN.matcher(input);
		while (m.find()) {
			sb.append(input.substring(m.start(), m.end()) + SEPARATOR + randInt(1000, 9999) + LINE_SEPARATOR);
		}
		return sb.toString();
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
}
