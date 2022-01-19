//Title: Edge class
//Author:Doruk Arslan
//Description: Edge class to create edges that will be added to the graph.
//-----------------------------------------------------
public class Edge implements Comparable<Edge> {
	private final int v, w;
	public final double weight;

	// Summary: Constructor of the class
	// Precondition: Vertices of the edge are not defined yet.
	// Postcondition:Vertices and weight are identified.
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	// It returns one end of the edge
	public int either() {
		return v;
	}

	// It returns other end of the edge
	public int other(int vertex) {
		if (vertex == v)
			return w;
		else
			return v;
	}

	// It compares the edge with given edge
	public int compareTo(Edge that) {
		if (this.weight < that.weight)
			return -1;
		else if (this.weight > that.weight)
			return +1;
		else
			return 0;
	}
}
