package ua.nure.shevchenko.Practice6;

public class Tree<E extends Comparable> {

	Node<E> root;
	{
		root = null;
	}

	public boolean add(E element) {
		if (root == null) {
			root = new Node(element);
			return true;
		} else {
			return root.add(new Node(element));
		}
	}

	public void add(E[] elements) {
		for (E e: elements){
			this.add(e);
		}
	}

	public boolean remove(E element) {
		if (root.value.compareTo(element)==0){
		}
		return true;
	}

	public void print() {

	}

	public static class Node<E extends Comparable> {
		E value;
		Node<E> left;
		Node<E> right;

		public Node(E input) {
			value = input;
		}

		public boolean add(Node element) {
			if (element.value.compareTo(value) > 0 && right == null) {
				right = element;
				return true;
			} else if (element.value.compareTo(value) > 0 && right != null) {
				return right.add(element);
			} else if (element.value.compareTo(value) < 0 && left == null) {
				left =element;
				return false;
			} else {
				return left.add(element);
			}
		}
	}

}
