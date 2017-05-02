package ua.nure.shevchenko.Practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Spam {

	private static long[] time;
	private static String[] message;
	private static int counter = 0;
	private static Thread[] spammers;

	public Spam(long[] time, String[] message) {
		this.time = time;
		this.message = message;
		spammers = new Thread[time.length];
		for (int i = 0; i < time.length; i++) {
			spammers[i] = new MyThread(i);
		}

	}

	public static void main(String[] args) throws InterruptedException, IOException {
		startAll();
		
		Thread thread = new Thread() {
            public void run() {
                byte[] buffer = new byte[10];
                int count;
                try {
                    do {
                        while ((count = System.in.read(buffer)) == -1)
                            ;
                    } while (!System.lineSeparator().equals(
                            new String(buffer, 0, count)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.out.println("ENTER has been obtained");
                System.out.println("Notify a child thread to terminate");
                interruptAll();
            }
        };
        thread.start();
	}

	private static void startAll() {
		for (Thread t : spammers) {
			t.start();
		}
	}

	private static void interruptAll() {
		for (Thread t : spammers) {
			t.interrupt();
		}
	}

	public class MyThread extends Thread {

		int pos;

		public MyThread(int i) {
			pos = i;
		}

		@Override
		public void run() {
			while (!interrupted()) {
				try {
					Thread.sleep(time[pos]);
					System.out.println(message[pos]);
				} catch (InterruptedException e) {
					return;
				}

			}
		}
	}
}
