package ua.nure.shevchenko.Practice3;

import java.io.IOException;
import java.nio.file.*;

public class Util {

	private static final String ENCODING = "Cp1251";

	public static String readFile(String way) {
		String result = null;
		try {
			byte[] byteArray = Files.readAllBytes(Paths.get(way));
			result = new String(byteArray, ENCODING);
		} catch (IOException e) {
			System.err.println(e);
		}
		return result;
	}

	public static void main(String[] args) {
	}
}