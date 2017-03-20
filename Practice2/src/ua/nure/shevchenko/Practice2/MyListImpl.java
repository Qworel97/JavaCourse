package ua.nure.shevchenko.Practice2;

import java.util.Iterator;

import ua.nure.shevchenko.Practice2.MyList;

public class MyListImpl implements MyList, ListIterable {

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
		init();
	}

	@Override
	public boolean remove(Object o) {
		if (contains(o)) {
			Object[] temp = new Object[array.length - 1];
			int j = 0;
			for (int i = 0; i < array.length; i++) {
				try {
					if (o != array[i]) {
						temp[j++] = array[i];
					}
				} catch (NullPointerException npe) {
					i++;
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
			if (array[i] != null)
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
				if (o == array[i]) {
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
		String temp = "";
		for (int i = 0; i < size - 1; i++) {			
			if (array[i]==null){
				temp = "null";
			}
			else{
				temp = array[i].toString();				
			} 
			result.append("[" + temp + "],");
		}
		if (array[size - 1]!=null){
			result.append("[" + array[size - 1].toString() + "]}");	
		}
		else{
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
		public int currentIndex = 0;
		public boolean canBeModified = true;


		public boolean hasNext() { // returns true if the iteration has more
									// elements
			return currentIndex < size;
		}

		public Object next() { // returns the next element in the iteration
			canBeModified = true;
			try {
				return array[currentIndex++];
			} catch (NullPointerException npe) {
				return "null";
			}
		}

		public void remove() { // removes from the underlying collection the
								// last element returned by this iterator
			if (!canBeModified)
				throw new IllegalStateException();
			else {
				MyListImpl.this.remove(MyListImpl.this.objectAt(currentIndex));
				canBeModified = false;
			}
		}
	}

	private void init() {
		array = new Object[8];
		size = 0;

	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return new ListIteratorImpl();
	}
	
	private class ListIteratorImpl extends IteratorImpl implements ListIterator{

		public ListIteratorImpl(){
			currentIndex = size-1;
		}
		@Override
		public boolean hasPrevious() {
			return currentIndex >= 0;
		}

		@Override
		public Object previous() {
			canBeModified = true;
			try {
				return array[currentIndex--];
			} catch (NullPointerException npe) {
				return "null";
			}
		}

		@Override
		public void set(Object e) {
			// TODO Auto-generated method stub
			if (!canBeModified)
				throw new IllegalStateException();
			else {
				array[currentIndex]=e;
				canBeModified = false;
			}
		}
		
	}
}
