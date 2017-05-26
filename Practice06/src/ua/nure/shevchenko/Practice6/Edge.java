package ua.nure.shevchenko.Practice6;

public class Edge {
	private int first;
	private int second;
	
	public Edge(int first, int second){
		this.first=first;
		this.second=second;
	}
	@Override
	public boolean equals(Object obj) {
		Edge temp = (Edge)obj;
		return ((temp.first==this.first && temp.second==this.second)
				||(temp.first==this.second && temp.second==this.first));
		}
	@Override
	public String toString() {
		return this.first + "-----" + this.second;
	}
}
