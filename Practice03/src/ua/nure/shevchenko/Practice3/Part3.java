package ua.nure.shevchenko.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
	
	private static final Pattern PATTERN_MAX;

	public static void main(String[] args) {
	}

	static {
		PATTERN_MAX = Pattern.compile("(?mU)(?<=\\W)(\\w)\\w++(?=\\W|$)");
	}

	public static String convert(String input) {
		String s = input;
		Matcher m = PATTERN_MAX.matcher(input);
		while (m.find()) {
			if (m.end() - m.start() > 1) {
				s = s.replace(input.substring(m.start(), m.end()),
						Character.toUpperCase(m.group(1).charAt(0)) + input.substring(m.start() + 1, m.end()));
			}
		}
		return s;
	}
}
