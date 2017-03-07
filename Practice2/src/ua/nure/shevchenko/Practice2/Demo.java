package ua.nure.shevchenko.Practice2;

import java.util.Iterator;

public class Demo {
	public static void main(String[] args) {

		MyList con = new MyListImpl();
		con.add("A");
		con.add("B");
		con.add(433);
		con.add(888);
		con.add(new Object());
		con.add(null);
		System.out.println(con.toString());
		System.out.println(con.contains("A"));
		con.remove("A");
		System.out.println(con.toString());
		for (Object o : con) {
		    System.out.println(o);
		}
		Iterator<Object> it = con.iterator();
		while (it.hasNext())
		    System.out.println(it.next());
	}
}
