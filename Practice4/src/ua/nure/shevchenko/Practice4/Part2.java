package ua.nure.shevchenko.Practice4;

import java.util.Random;

public class Part2 {

	private static final String RAW_DATA = "part2.txt";

	private static final String SORTED_DATA = "part2_sorted.txt";

	private static final int N = 20;

	private static final int MIN = 0;
	private static final int MAX = 50;

	public static void main(String[] args) {
		String randData = createRand();
		Util.writeFile(RAW_DATA, randData);
		System.out.println("input => " + randData);
		String[] input = Util.readFile(RAW_DATA,"CP1251").split(" ");
		int[] toSort = toIntArray(input);
		toSort = sort(toSort);
		StringBuilder temp = new StringBuilder();
		for (int x : toSort) {
			temp.append(x + " ");
		}
		Util.writeFile(SORTED_DATA, temp.toString());
		System.out.println("output => " + temp.toString());
	}

	public static String createRand(){
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < N; i++) {
			temp.append(randInt(MIN, MAX) + " ");
		}
		return temp.toString();
	}
	
	public static int[] toIntArray(String[] input){
		int[] res = new int[input.length];
		for(int i = 0; i<input.length; i++){
			res[i]=Integer.parseInt(input[i]);
		}
		return res;
	}
	public static int randInt(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

	public static int[] sort(int[] input) {
		int[] forWork = input;
		for (int i = 0; i < forWork.length; i++) {
			for (int j = i + 1; j < forWork.length; j++) {
				if (forWork[i] > forWork[j]) {
					int temp = forWork[i];
					forWork[i] = forWork[j];
					forWork[j] = temp;
				}
			}
		}
		return forWork;
	}
}