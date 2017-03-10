package ua.nure.shevchenko.Practice2;

import java.util.Iterator;

public class Demo {
	public static void main(String[] args) {

		MyList con = new MyListImpl();
		MyList con2 =  new MyListImpl();
		con2.add("A");
		con2.add("B");
		con.add("A");
		con.add("B");
		con.add(433);
		con.add(888);
		con.add(new Object());
//		con.add(null);
		System.out.println(con.containsAll(con2));
		for (Object o : con) {
		    System.out.println(o);
		}
		Iterator<Object> it = con.iterator();
		while (it.hasNext())
		    System.out.println(it.next());
	}
}
