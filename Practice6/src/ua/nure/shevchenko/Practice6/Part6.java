package ua.nure.shevchenko.Practice6;

import java.util.Scanner;

public class Part6 {
public static void main(String[] args) {
	
}
public void frequency(String input){
	WordContainer wc = new WordContainer();
	String[] tempArray = input.split(" ");
	for (String x : tempArray) {
		wc.add(x);
	}
	wc.printThree();
}
public void length(String input){
	WordContainer wc = new WordContainer();
	String[] tempArray = input.split(" ");
	for (String x : tempArray) {
		wc.add(x);
	}
	wc.maxThree();
}
public void duplicates(String input){
	WordContainer wc = new WordContainer();
	String[] tempArray = input.split(" ");
	for (String x : tempArray) {
		wc.add(x);
	}
	wc.printThreeDuplicates();
}
}
