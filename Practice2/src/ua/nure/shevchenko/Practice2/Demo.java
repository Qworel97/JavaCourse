package ua.nure.shevchenko.Practice2;

import java.util.Iterator;

public class Demo {
	public static void main(String[] args) {

		MyListImpl con = new MyListImpl();

		con.add("A");
		con.add("B");
		con.add(433);
		con.add(888);
		con.add(new Object());
		con.add(null);
		con.add(888);
		Iterator temp = con.iterator();
		while(temp.hasNext())
		{
			System.out.println(temp.next());
		}
		System.out.println("_____________");
		ListIterator temp2 = con.listIterator();
		temp2.set(777);
		while(temp2.hasPrevious()){
			System.out.println(temp2.previous());
		}
	}
}
