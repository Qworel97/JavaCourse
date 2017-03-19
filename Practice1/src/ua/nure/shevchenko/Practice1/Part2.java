package ua.nure.shevchenko.Practice1;

public class Part2 {

	public static void main(String[] args) {
		System.out.println(sum(args[0], args[1]));
	}

	public static int sum(String first, String second) {
		return Integer.parseInt(first) + Integer.parseInt(second);
	}
}
