package ua.nure.shevchenko.Practice1;

public class Part3 {

	public static void main(String[] args) {
		System.out.println(gcd(Integer.parseInt(args[0]),
				Integer.parseInt(args[1])));
	}

	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
}
