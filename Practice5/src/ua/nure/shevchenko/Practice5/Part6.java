package ua.nure.shevchenko.Practice5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Part6 extends Thread {

	private static final Object MONITOR = new Object();

	private static final int THREADS_NUMBER = 10;

	private static final int COLUMNS = 20;

	private static final int EOL_LENGTH = System.lineSeparator().length();

	private static String fileName = "test.txt";

	private RandomAccessFile out;

	// TODO place your code here

	@Override
	public void run() {
		try {
			out = new RandomAccessFile("test.txt","rw");
		} catch (FileNotFoundException e) {
		}
		Writer wr = new Writer(out);
		wr.go();
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new Part6().run();
	}

}