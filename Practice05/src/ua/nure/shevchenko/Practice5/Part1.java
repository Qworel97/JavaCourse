package ua.nure.shevchenko.Practice5;

public class Part1 {

	public static void main(String[] args) {
		Thread t = new Part1().new MyThread();
		t.start();
		Runnable m = new MyRunnable();
		m.run();
	}

	public class MyThread extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 6; i++) {
				System.out.println(this.getName());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}
	
	public static class MyRunnable implements Runnable{

		@Override
		public void run() {
			for (int i = 0; i < 6; i++) {
				System.out.println(this.toString());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		
	}
}
