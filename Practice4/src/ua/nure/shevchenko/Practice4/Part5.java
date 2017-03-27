package ua.nure.shevchenko.Practice4;

import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

	private static final String BASE_NAME = "resources_";

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String temp = scan.nextLine();
		try {
			while (!temp.equals("stop")) {
				ResourceBundle rb = ResourceBundle.getBundle(BASE_NAME + temp.split(" ")[1]);
				System.out.println(rb.getString(temp.split(" ")[0]));
				temp = scan.nextLine();
			}
		} catch (NoSuchElementException nsee) {
			System.err.println(nsee.getMessage());
		} finally {
			scan.close();
		}
	}

}