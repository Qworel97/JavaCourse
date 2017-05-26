package ua.nure.shevchenko.Practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ua.nure.shevchenko.Practice2.MyList;

public class MyListImpl implements MyList, ListIterable {

	private Object[] array;
	private int size;

	public MyListImpl() {
		init();
	}

	@Override
	public void add(Object e) {
		if (size == array.length - 1) {
			Object[] newArray = new Object[2 * array.length];
			System.arraycopy(array, 0, newArray, 0, size);
			newArray[++size] = e;
			array = newArray;
		} else {
			array[size++] = e;
		}
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

				if (o != array[i]) {
					temp[j++] = array[i];
				}

			}
			array = temp;
			size--;
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		for (int i = 0; i <= size; i++) {
			if (array[i] != null) {
				result[i] = array[i];
			}
		}
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object obj) {
		for (int j = 0; j < array.length; j++) {
			if (obj == array[j]) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(MyList other) {
		for (Object obj : other) {
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder result = new StringBuilder();
		result.append("[");
		String temp = "";
		for (int i = 0; i < size - 1; i++) {
			if (array[i] == null) {
				temp = "null";
			} else {
				temp = array[i].toString();
			}
			result.append("" + temp + ", ");
		}
		if (array[size - 1] != null) {
			result.append("" + array[size - 1] + "]");
		} else {
			result.append("null]");
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
		private int currentIndex = -1;
		private boolean canBeModified = true;

		public int getCurrentIndex() {
			return currentIndex;
		}

		public void setCurrentIndex(int currentIndex) {
			this.currentIndex = currentIndex;
		}

		public boolean isCanBeModified() {
			return canBeModified;
		}

		public void setCanBeModified(boolean canBeModified) {
			this.canBeModified = canBeModified;
		}

		public boolean hasNext() {
			return currentIndex < size - 1;
		}

		public Object next(){
			canBeModified = true;
			if (currentIndex < size) {
				if (array[currentIndex + 1] != null) {
					return array[++currentIndex];
				} else {
					return null;
				}
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			if (!canBeModified) {
				throw new IllegalStateException();
			} else {
				MyListImpl.this.remove(MyListImpl.this.objectAt(currentIndex));
				canBeModified = false;
				if (currentIndex != 0) {
					currentIndex--;
				}
			}
		}
	}

	private void init() {
		array = new Object[8];
		size = 0;

	}

	@Override
	public ListIterator listIterator() {

		return new ListIteratorImpl();
	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {

		@Override
		public boolean hasPrevious() {
			return getCurrentIndex() > -1;
		}

		@Override
		public Object previous() {
			setCanBeModified(true);
			if (getCurrentIndex() > -1) {
				if (array[getCurrentIndex()] != null) {
					try{
					return array[getCurrentIndex()];}
					finally{
						setCurrentIndex(getCurrentIndex()-1);
					}
				} else {
					return null;
				}
			}
			return null;

		}

		@Override
		public void set(Object e) {

			if (!isCanBeModified()) {
				throw new IllegalStateException();
			} else {
				if (getCurrentIndex() == -1) {
					setCurrentIndex(0);
				}
				array[getCurrentIndex()] = e;
				setCanBeModified(false);
			}
		}

	}
}
