package ua.nure.shevchenko.Practice5;

import java.util.concurrent.atomic.AtomicInteger;

public class Comparationer {

	private AtomicInteger max = new AtomicInteger();
	private int[][] matrix;

	public Comparationer(int[][] matrix) {
		this.matrix = matrix;
	}

	public long compare() throws InterruptedException {
		long temp = System.currentTimeMillis();
		max.set(matrix[0][0]);
		for (int i = 0; i < matrix.length-1; i++) {
			new MyThread(i).start();
		}
		Thread t = new MyThread(matrix.length-1);
		t.start();
		t.join();
		return System.currentTimeMillis()-temp;
	}

	public class MyThread extends Thread {

		int pos;

		public MyThread(int i) {
			pos = i;
		}

		@Override
		public void run() {
			for (int i = 0; i < matrix[pos].length; i++) {
				if (max.get() < matrix[pos][i]) {
					max.set(matrix[pos][i]);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
