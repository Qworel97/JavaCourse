package ua.nure.shevchenko.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
	private final static String INPUT_DATA;
	private final static String PATH = "part3.txt";
	private final static Pattern PATTERN_MAX;
	
	public static void main(String[] args) {
		System.out.println(convert(INPUT_DATA));
	}

	static {
		INPUT_DATA = Util.readFile(PATH);
		//fix word
		PATTERN_MAX = Pattern.compile("(?mU)(?<=\\W)(\\w)\\w++(?=\\W|$)");
	}

	public static String convert(String input) {
		String sb = input;
		Matcher m = PATTERN_MAX.matcher(INPUT_DATA);
		while (m.find()) {
			sb = sb.replace(INPUT_DATA.substring(m.start(), m.end()),
					Character.toUpperCase(m.group(1).charAt(0)) + INPUT_DATA.substring(m.start() + 1, m.end()));
		}
		return sb.toString();
	}
}
