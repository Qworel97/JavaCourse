package ua.nure.shevchenko.Practice4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	private static final String INPUT;
	private static final String PATH = "part1.txt";
	private static final Pattern PATTERN;

	static {
		PATTERN = Pattern.compile("(\\p{L}+)*(\\W)",Pattern.UNICODE_CHARACTER_CLASS);
		INPUT = new String(Util.readFile(PATH,"CP1251"));
	}
	
	public static void main(String[] args) {
		System.out.println(goUp());
	}
	public static String goUp(){
		Matcher m = PATTERN.matcher(INPUT);
		StringBuilder res = new StringBuilder();
		while (m.find()) {
			if (m.end() - m.start() > 4) {
				res.append(INPUT.substring(m.start(), m.end()).toUpperCase());
			}
			else{
				res.append(INPUT.substring(m.start(),m.end()));
			}
		}
		return res.toString();
	}
}
