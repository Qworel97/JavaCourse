package ua.nure.shevchenko.Practice5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Writer {

	private static RandomAccessFile t;
	private static Writer m = new Writer();
	private static String monitor = "";
	
	public static void main(String[] args) throws FileNotFoundException {
		t = new RandomAccessFile("part6.txt", "rw");
		m.go();
	}

	public Writer(){}
	
	public Writer(RandomAccessFile out){
		t = out;
	}
	public void go(){
		for (int i = 0; i < 10; i++) {
			m.new MyThread(i).start();
		}
		try {
			t.setLength(200);
			String result = null;
			try {
				byte[] byteArray = Files.readAllBytes(Paths.get("test.txt"));
				result = new String(byteArray, "UTF-8");
				System.out.println(result);
			} catch (IOException e) {
				System.err.println(e);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public void write(RandomAccessFile raf, int i) throws IOException {
		synchronized (monitor) {
			for (int j = 0; j <= 20; j++) {
				raf.seek(i * 21 + j * 1);
				raf.write('0'+ i);
			}
			raf.seek(i * 21 + 20);
			raf.write('\n');
		}
	}

	public class MyThread extends Thread {

		int pos;

		public MyThread(int i) {
			pos = i;
		}

		@Override
		public void run() {
			try {
				write(t, pos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
