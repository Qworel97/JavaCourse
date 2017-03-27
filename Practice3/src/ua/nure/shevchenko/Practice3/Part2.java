package ua.nure.shevchenko.Practice3;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
	private static final Pattern PATTERN_MIN;
	private static final Pattern PATTERN_MAX;
	private static final String LINE_SEPARATOR = System.lineSeparator();

	public static void main(String[] args) {
	}

	static {
		PATTERN_MIN = Pattern.compile("(?m)(?<=(\\s|'))\\S(?=\\s)");
		PATTERN_MAX = Pattern.compile("(?m)(?<=\\W)([A-z]|[À-ÿ])++(?=\\W)");
	}

	public static String convert(String input) {
		StringBuilder sb = new StringBuilder("Min: ");
		Matcher m = PATTERN_MIN.matcher(input);
		while (m.find()) {
			if (!sb.toString().contains(input.substring(m.start(), m.end()))) {
				sb.append(input.substring(m.start(), m.end()));
				if (!m.hitEnd()) {
					sb.append(", ");
				}
			}
		}
		sb = new StringBuilder(sb.toString().substring(0, sb.length()-2));
		sb.append(LINE_SEPARATOR);
		sb.append("Max: ");
		m = PATTERN_MAX.matcher(input);
		ArrayList<String> temp = new ArrayList<String>();
		while (m.find()) {
			if (!sb.toString().contains(input.substring(m.start(), m.end()))) {
				temp.add(input.substring(m.start(), m.end()));
			}
		}
		int maxLength = 0;
		for (String x : temp) {
			if (x.length() > maxLength) {
				maxLength = x.length();
			}
		}
		for (String x : temp) {
			if (!sb.toString().contains(x) && x.length() == maxLength) {
				sb.append(x + ", ");
			}
		}
		sb = new StringBuilder(sb.toString().substring(0, sb.length()-2));
		return sb.toString();
	}
}
