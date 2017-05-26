package ua.nure.shevchenko.Practice6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Part1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		WordContainer wc = new WordContainer();
		String temp = scan.nextLine();
		String[] tempArray = temp.split(" ");
		for (String x : tempArray) {
			wc.add(x);
		}
		wc.print();
		System.out.println("~~~~~~~~~~~~~~~~~");
		wc.printThree();
		System.out.println("~~~~~~~~~~~~~~~~~");
		wc.maxThree();
		System.out.println("~~~~~~~~~~~~~~~~~");
		wc.printThreeDuplicates();
	}

}
