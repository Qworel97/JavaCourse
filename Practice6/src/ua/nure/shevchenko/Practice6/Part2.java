package ua.nure.shevchenko.Practice6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
	public static void main(String[] args) {
		ArrayList t1 = new ArrayList<Integer>();
		LinkedList t2 = new LinkedList<Integer>();
		for (int i = 0; i < 100000; i++) {
			t1.add(i);
			t2.add(i);
		}
		long temp = System.currentTimeMillis();
		removeArray(t1, 5);
		System.out.println(System.currentTimeMillis() - temp);
		temp = System.currentTimeMillis();
		removeLinked(t2, 5);
		System.out.println(System.currentTimeMillis() - temp);

	}

	public static void removeArray(ArrayList list, int k) {
		int number = k;
		while (list.size() > 1) {
			list.remove(number);
			number = number + k;
			if (number >= list.size()) {
				number = number % list.size();
			}
		}
	}

	public static void removeLinked(LinkedList<Integer> list, int k) {
		int number = 0;
		Iterator<Integer> iterator = list.iterator();
		while (list.size() > 1) {
			number++;
			iterator.next();
			if (number%k==0){
				iterator.remove();
				}
			if (!iterator.hasNext()){
				iterator=list.iterator();
			}
			}
	}
}
