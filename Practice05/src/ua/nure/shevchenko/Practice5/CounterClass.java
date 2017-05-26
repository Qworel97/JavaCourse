package ua.nure.shevchenko.Practice5;

import java.util.ArrayList;
import java.util.List;

public class CounterClass {

	private int first = 0;
	private int second = 0;

	private List<Thread> threads;
	
	public CounterClass() {
		threads = new ArrayList<Thread>();
		threads.add(this.new MyThread());
		threads.add(this.new MyThread());
		threads.add(this.new MyThread());
		threads.add(this.new MyThread());
		threads.add(this.new MyThread());
		threads.add(this.new MyThread());
		threads.add(this.new MyThread());
		for(Thread x: threads){
			x.start();
		}
	}

	private void check() throws InterruptedException {
		System.out.println(first + " " + second);
		first++;
		Thread.sleep(300);
		second++;
	}
	
	public void stop() throws InterruptedException {
		for(Thread x: threads){
			x.interrupt();
		}
	}

	private class MyThread extends Thread {
		@Override
		public void run() {

			try {
				while (true) {
					check();
				}
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
