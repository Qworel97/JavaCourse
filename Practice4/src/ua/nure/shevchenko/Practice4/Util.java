package ua.nure.shevchenko.Practice4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Util {

	public static String readFile(String way, String encoding) {
		String result = null;
		try {
			byte[] byteArray = Files.readAllBytes(Paths.get(way));
			result = new String(byteArray, encoding);
		} catch (IOException e) {
			System.err.println(e);
		}
		return result;
	}

	public static void writeFile(String fileName, String text) {
		File file = new File(fileName);

		try {
			if (!file.exists() && !file.createNewFile()) {
				throw new IOException();
			}
			Writer writer = new OutputStreamWriter(new FileOutputStream(
					fileName), StandardCharsets.UTF_8);
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			bufferWriter.write(text);
			bufferWriter.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public static void main(String[] args) {
	}
}