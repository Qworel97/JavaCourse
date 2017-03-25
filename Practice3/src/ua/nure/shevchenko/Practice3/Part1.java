package ua.nure.shevchenko.Practice3;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.nure.shevchenko.Practice3.Util;

public class Part1 {
	private final static String INPUT_DATA;
	private final static String PATH = "part1.txt";
	private final static Pattern PATTERN;
	private final static String LINE_SEPARATOR = System.lineSeparator();
	private final static String SEPARATOR = ";";

	public static void main(String[] args) {
		System.out.println(convert1());
		System.out.println("______");
		System.out.println(convert2());
		System.out.println("______");
		System.out.println(convert3());
		System.out.println("______");
		System.out.println(convert4());
		System.out.println("______");
	}

	static {
		INPUT_DATA = Util.readFile(PATH);
		PATTERN = Pattern.compile("(?m)(\\S+)[" + SEPARATOR 
				+ "](\\S+\\s\\S+)[" + SEPARATOR +"]([\\w|\\d]+@)(\\S+$)");
	}

	public static String convert1() {
		StringBuilder sb = new StringBuilder();
		Matcher m = PATTERN.matcher(INPUT_DATA);
		while (m.find()) {
			sb.append(m.group(1) + " ==> " + m.group(3) + m.group(4) + LINE_SEPARATOR);
		}
		return sb.toString();
	}

	public static String convert2() {
		StringBuilder sb = new StringBuilder();
		Matcher m = PATTERN.matcher(INPUT_DATA);
		while (m.find()) {
			sb.append(m.group(2) + " (email: " + m.group(3) + m.group(4) + ")" + LINE_SEPARATOR);
		}
		return sb.toString();
	}

	public static String convert3() {
		String s = "";
		Matcher m = PATTERN.matcher(INPUT_DATA);
		while (m.find()) {
			String temp1 = m.group(4);
			if (!s.contains(temp1)) {
				s += m.group(4) + " ==> " + m.group(1);
			}
			Matcher m2 = PATTERN.matcher(INPUT_DATA);
			while (m2.find()) {
				String temp2 = m2.group(4);
				if (!s.contains(m2.group(1)) && temp1.equals(temp2)) {
					s += ", " + m2.group(1);
				}
			}
			s += LINE_SEPARATOR;
		}
		return s;
	}

	public static String convert4() {
		StringBuilder sb = new StringBuilder();
		sb.append("Login;Name;Email;Password" + LINE_SEPARATOR);
		Matcher m = PATTERN.matcher(INPUT_DATA);
		while (m.find()) {
			sb.append(INPUT_DATA.substring(m.start(), m.end())+SEPARATOR 
					+ randInt(1000,9999) + LINE_SEPARATOR);
		}
		return sb.toString();
	}
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}
