package ua.nure.shevchenko.Practice4;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

	private static final String BASE_NAME = "resources_";

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in, "UTF-8");

			while (scan.hasNext()) {
				String temp = scan.nextLine();
				if (!temp.equals("stop")) {
					System.out.println(getResourceData(temp));
				} else {
					scan.close();
					return;
				}
			}
			return;
		
	}
	public static final String getResourceData(String input){
		ResourceBundle rb = ResourceBundle.getBundle(BASE_NAME
				+ input.split(" ")[1]);
		return rb.getString(input.split(" ")[0]);
	}

}