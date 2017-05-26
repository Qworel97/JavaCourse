package ua.nure.shevchenko.Practice1;

public class Part4 {

	public static void main(String[] args) {
		System.out.println(digitSum(args[0]));
	}

	public static int digitSum(String number) {
		int result = 0;
		int input = Integer.parseInt(number);
		for (; input > 0;) {
			result += input % 10;
			input /= 10;
		}
		return result;
	}
}
