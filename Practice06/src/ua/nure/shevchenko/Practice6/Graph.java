package ua.nure.shevchenko.Practice6;

import java.util.ArrayList;
import java.util.Map;

public class Graph {
	private int vertexNumber;
	ArrayList<Edge> edges;

	public Graph(int n) {
		vertexNumber = n;
		edges = new ArrayList<Edge>();
	}

	public boolean add(int first, int second) {
		if (first < vertexNumber && second < vertexNumber) {
			if (!edges.contains(new Edge(first, second))) {
				edges.add(new Edge(first, second));
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(int first, int second) {
		if (first < vertexNumber && second < vertexNumber) {
			if (edges.contains(new Edge(first, second))) {
				edges.remove(new Edge(first, second));
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Edge x: edges) {
			sb.append(x.toString() + System.lineSeparator());
		}
		sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return sb.toString();
	}

}
