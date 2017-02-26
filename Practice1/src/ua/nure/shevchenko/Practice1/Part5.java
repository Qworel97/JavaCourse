package ua.nure.shevchenko.Practice1;

public class Part5 {

	public final static int ALPHABET_LENGTH = 26;
	public final static int ALPHABET_START = 'A' - 1;

	public static void main(String[] args) {
		System.out.print(chars2digits(args[0]));
		System.out.print(" ===> ");
		System.out.print(digits2chars(chars2digits(args[0])));
		System.out.print(" ===> ");
		System.out.println(rightColumn(args[0]));
	}

	public static int chars2digits(String number) {
		int result = 0;
		for (int i = 0; i < number.length(); i++) {
			result = result * ALPHABET_LENGTH + (int) number.charAt(i)
					- ALPHABET_START;
		}
		return result;
	}

	public static String digits2chars(int number) {
		String tempResult = new String();
		for (; number > 0;) {
			char temp = 0;
			if ((char) (number % ALPHABET_LENGTH + ALPHABET_START) == ALPHABET_START)
				temp = ALPHABET_START + ALPHABET_LENGTH;
			else
				temp = (char) (number % ALPHABET_LENGTH + ALPHABET_START);

			tempResult += temp;
			if (number == 26)
				break;
			else if (number % 26 == 0) {
				number /= ALPHABET_LENGTH;
				number -= 1;
			} else {
				number /= ALPHABET_LENGTH;
			}
		}
		StringBuilder resultBuild = new StringBuilder(tempResult);
		return resultBuild.reverse().toString();
	}

	public static String rightColumn(String number) {
		return digits2chars(chars2digits(number) + 1);
	}
}
