package ua.nure.shevchenko.Practice2;

import java.util.Iterator;

public class Demo {
	public static void main(String[] args) {
		System.out.println("==== Part1");
		MyList testList1 = new MyListImpl();
		testList1.add("A");
		testList1.add("B");
		testList1.add(1);
		testList1.add(7);
		System.out.println(testList1);
		testList1.clear();
		System.out.println(testList1);
		testList1.add("A");
		testList1.add("B");
		testList1.add(7);
		System.out.println(testList1);
		testList1.remove("B");
		testList1.remove("C");
		System.out.println(testList1);
		System.out.println(testList1.size()); 
		System.out.println(testList1.contains("B")); 
		System.out.println(testList1.contains(7)); 
		for (Object el : testList1.toArray()) {
			System.out.print(el + " ");
		}
		System.out.println();
		MyList listToCompare = new MyListImpl();
		listToCompare.add("A");
		listToCompare.add(9);
		System.out.println(testList1.containsAll(listToCompare));
		testList1.add(9);
		System.out.println(testList1.containsAll(listToCompare));
		System.out.println("==== Part2");
		MyList testList2 = new MyListImpl();
		testList2.add("A");
		testList2.add(7);
		testList2.add(9);
		for (Object o : testList1) {
			System.out.print(o + " ");
		}
		System.out.println();
		Iterator<Object> testIterator = testList1.iterator();
		while (testIterator.hasNext()) {
			System.out.print(testIterator.next() + " ");
		}
		System.out.println();
		testIterator = testList1.iterator();
		testIterator.next();
		testIterator.next();
		testIterator.remove();
		System.out.println(testList1);
		System.out.println(testIterator.hasNext());
		System.out.println(testIterator.next());
		System.out.println(testIterator.hasNext());
		System.out.println("==== Part3");
		MyList testList3 = new MyListImpl();
		testList3.add("A");
		testList3.add("B");
		testList3.add(7);
		ListIterator listIterator = testList3.listIterator();
		System.out.println(testList3);
		while (listIterator.hasNext()) {
			System.out.print(listIterator.next() + " ");
		}
		System.out.println();
		listIterator = testList3.listIterator();
		listIterator.next();
		listIterator.next();
		listIterator.set("C");
		System.out.println(testList3);
		while (listIterator.hasPrevious()) {
			System.out.print(listIterator.previous() + " ");
		}
		System.out.println();
		listIterator.set(1);
		System.out.println(testList3);
	}
}
