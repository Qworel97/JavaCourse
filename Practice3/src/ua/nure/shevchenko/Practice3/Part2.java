package ua.nure.shevchenko.Practice3;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
	private final static String INPUT_DATA;
	private final static String PATH = "part2.txt";
	private final static Pattern PATTERN_MIN;
	private final static Pattern PATTERN_MAX;
	private final static String LINE_SEPARATOR = System.lineSeparator();

	public static void main(String[] args) {
		System.out.println(convert(PATH));
	}

	static {
		INPUT_DATA = Util.readFile(PATH);
		PATTERN_MIN = Pattern.compile("(?m)(?<=\\W)\\S(?=\\s)");
		PATTERN_MAX = Pattern.compile("(?m)(?<=\\W)\\w++(?=\\W)");
	}

	public static String convert(String input) {
		String sb = "Min: ";
		Matcher m = PATTERN_MIN.matcher(INPUT_DATA);
		while (m.find()) {
			if (!sb.contains(INPUT_DATA.substring(m.start(), m.end()))) {
				sb += INPUT_DATA.substring(m.start(), m.end());
				if (!m.hitEnd()) {
					sb += ", ";
				}
			}
		}
		sb = sb.substring(0, sb.length()-2);
		sb += LINE_SEPARATOR;
		sb += "Max: ";
		m = PATTERN_MAX.matcher(INPUT_DATA);
		ArrayList<String> temp = new ArrayList<String>();
		while (m.find()) {
			if (!sb.contains(INPUT_DATA.substring(m.start(), m.end()))) {
				temp.add(INPUT_DATA.substring(m.start(), m.end()));
			}
		}
		int maxLength = 0;
		for (String x : temp) {
			if (x.length() > maxLength) {
				maxLength = x.length();
			}
		}
		for (String x : temp) {
			if (!sb.contains(x) && x.length() == maxLength) {
				sb += x + ", ";
			}
		}
		sb = sb.substring(0, sb.length()-2);
		return sb.toString();
	}
}
