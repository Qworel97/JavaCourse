package ua.nure.shevchenko.Practice6;

public class Part4 {
	public static void main(String[] args) {
		Graph graph = new Graph(5);
		graph.add(0, 1);
		graph.add(1, 0);
		graph.add(2, 0);
		graph.add(3, 0);
		graph.add(7, 8);
		System.out.println(graph.toString());
		graph.remove(1,0);
		System.out.println(graph.toString());
	}
}
