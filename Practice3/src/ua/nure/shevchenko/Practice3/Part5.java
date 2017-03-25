package ua.nure.shevchenko.Practice3;

public class Part5 {
	private final static String[] ROME = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C" };
	private final static Integer[] DIGITS = { 1, 4, 5, 9, 10, 40, 50, 90, 100 };

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			System.out.println(i + " ==> " + decimal2Roman(i) + " ==> " + roman2Decimal(decimal2Roman(i)));
		}
	}

	public static String decimal2Roman(int x) {
		int res = x;
		String s = "";
		while (res > 0) {
			for (int i = 8; i >= 0;) {
				if (res >= DIGITS[i]) {
					res -= DIGITS[i];
					s += ROME[i];
				} else {
					i--;
				}
			}
		}
		return s;
	}
	public static int roman2Decimal(String s) {
		int res = 0;
		while (s.length()>0){
			for(int i = 8; i>=0;){
				if(s.indexOf(ROME[i])==0){
					s = s.substring(ROME[i].length(),s.length());
					res += DIGITS[i];
				}
				else
				{
					i--;
				}
			}
		}
		return res;
	}
}
