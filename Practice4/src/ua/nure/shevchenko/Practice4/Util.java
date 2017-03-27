package ua.nure.shevchenko.Practice4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

	public static void writeFile(String fileName, String text)
	{
		File file = new File(fileName);
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
			try {
	            bufferWriter.write(text);
			} 
			finally {
	            bufferWriter.close();	
			}

        }
        catch (IOException e) {
            System.out.println(e);
        }
		try{
			if(!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());
			try {
				out.print(text);	
			} 
			finally {
				out.close();
			}
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
	}
}