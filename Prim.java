
//-----------------------------------------------------
//Title: Prim class
//Author:Doruk Arslan
//Description: Performs Prim algorithm on given Graph
//----------------------------------------------------
import java.util.LinkedList;
import java.util.Queue;

public class Prim {

	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;

	// Summary: Constructor of the class
	// Precondition: Intended graph given.
	// Postcondition:In order to find the minimum spanning tree in the given graph,
	// all vertices are evaluated and visited by considering their weights.
	// Eventually, edges added to mst if they are appropriate.
	public Prim(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		mst = new LinkedList<Edge>();
		marked = new boolean[G.V()];
		visit(G, 0);
		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (marked[v] && marked[w])
				continue;
			if (EdgeControl(e)) {
				mst.add(e);

			} else {
				int End = e.either();
				int Other = e.other(End);
				Edge newEdge = new Edge(Other, End, e.weight);
				mst.add(newEdge);
			}
			if (!marked[v])
				visit(G, v);
			if (!marked[w])
				visit(G, w);
		}
	}

	// Summary:It visits the given vertex and adds adjacent edges to the PQ.
	// Precondition: Intended graph and vertex are given.
	// Postcondition: Given vertex marked as visited and adjacent edges added to the
	// priority queue.
	private void visit(EdgeWeightedGraph G, int v) {

		marked[v] = true;
		for (Edge e : G.adj(v))
			if (!marked[e.other(v)])
				pq.insert(e);
	}

	// Summary: It controls to edge that will be added to the mst.
	// Precondition: Intended edge is given.
	// Postcondition: Before adding an edge to the mst we check it an format it.
	// Assume that vertex to be added is given as 4-0 and this edge is connected to
	// mst by the vertex 0(4 not visited yet ). If that is the case we format the
	// edge to 0-4 instead of 4-0 by exchanging the parameters.
	public boolean EdgeControl(Edge e) {

		int End = e.either();
		int Other = e.other(End);
		for (Edge edge : mst) {

			int comparedEnd = edge.either();
			int comparedOther = edge.other(comparedEnd);

			if (Other == comparedOther) {
				return false;
			}

		}
		return true;
	}

	// To return the founded mst.
	public Iterable<Edge> mst() {

		return mst;
	}

}
