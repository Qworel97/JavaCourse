package ua.nure.shevchenko.Practice1;

public class Part5 {

	public static final int ALPHABET_LENGTH = 26;
	public static final int ALPHABET_START = 'A' - 1;
	private static final String[] INPUTS = { "A", "B", "Z", "AA", "AZ", "BA",
			"ZZ", "AAA" };

	public static void main(String[] args) {
		for (String x : INPUTS) {
			System.out.println(x + " ==> " + chars2digits(x) + " ==> "
					+ digits2chars(chars2digits(x)));
		}

	}

	public static int chars2digits(String num) {
		int result = 0;
		for (int i = 0; i < num.length(); i++) {
			result = result * ALPHABET_LENGTH + (int) num.charAt(i)
					- ALPHABET_START;
		}
		return result;
	}

	public static String digits2chars(int num) {
		int number = num;
		StringBuilder tempResult = new StringBuilder();
		for (; number > 0;) {
			char temp = 0;
			if ((char) (number % ALPHABET_LENGTH + ALPHABET_START) == ALPHABET_START) {
				temp = ALPHABET_START + ALPHABET_LENGTH;
			} else {
				temp = (char) (number % ALPHABET_LENGTH + ALPHABET_START);
			}
			tempResult.append(temp);
			if (number == 26) {
				break;
			} else if (number % 26 == 0) {
				number /= ALPHABET_LENGTH;
				number -= 1;
			} else {
				number /= ALPHABET_LENGTH;
			}
		}
		return tempResult.reverse().toString();
	}

	public static String rightColumn(String number) {
		return digits2chars(chars2digits(number) + 1);
	}
}
