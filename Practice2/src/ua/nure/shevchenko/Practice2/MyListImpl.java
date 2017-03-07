package ua.nure.shevchenko.Practice2;

import java.util.Iterator;

import ua.nure.shevchenko.Practice2.MyList;

public class MyListImpl implements MyList {

	public Object[] array;
	private int size;

	public MyListImpl() {
		init();
	}

	@Override
	public void add(Object e) {
		if (size == array.length - 1) {
			Object[] newArray = new Object[2 * array.length];
			for (int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			newArray[++size] = e;
			array = newArray;
		} else
			array[size++] = e;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		init();
	}

	@Override
	public boolean remove(Object o) {
		if (contains(o)) {
			Object[] temp = new Object[array.length - 1];
			int j = 0;
			for (int i = 0; i < array.length; i++) {
				if (!o.equals(array[i])) {
					temp[j++] = array[i];
				}
			}
			array = temp;
			size--;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		for (int i = 0; i <= size; i++) {
			result[i] = array[i];
		}
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < array.length; i++)
			if (o.equals(array[i])) {
				return true;
			}
		return false;
	}

	@Override
	public boolean containsAll(MyList c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		for (int i = 0; i < size - 1; i++) {
			result.append("[" + array[i].toString() + "],");
		}
		try {
			result.append("[" + array[size - 1].toString() + "]}");
		} catch (NullPointerException npe) {
			result.append("[null]}");
		}
		return result.toString();
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	public Object objectAt(int i) {
		if (i < size) {
			return array[i];
		} else {
			return null;
		}

	}

	private class IteratorImpl implements Iterator<Object> {
		private int currentIndex = 0;
		private boolean wasMoved = true;
		private boolean wasRemoved = false;

		public boolean hasNext() { // returns true if the iteration has more
									// elements
			return currentIndex < size;
		}

		public Object next() { // returns the next element in the iteration
			wasMoved = true;
			try{
				return array[currentIndex++];
			}
			catch(NullPointerException npe){
				return "null";
			}
			
		}

		public void remove() { // removes from the underlying collection the
								// last element returned by this iterator
			if (!wasMoved || wasRemoved)
				throw new IllegalStateException();
			else {
				MyListImpl.this.remove(MyListImpl.this.objectAt(currentIndex));
				wasMoved = false;
				wasRemoved = true;
			}
		}
	}

	private void init() {
		array = new Object[8];
		size = 0;

	}
}
