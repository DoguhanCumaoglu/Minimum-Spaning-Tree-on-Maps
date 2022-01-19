
//-----------------------------------------------------
//Title: EdgeWeightedgraph class
//Author:Doruk Arslan

//Description: EdgeWeightedgraph class to create a graph with edges who have weights.
//-----------------------------------------------------
import java.util.Iterator;

public class EdgeWeightedGraph {

	private final int V;
	private final Bag<Edge>[] adj;

	// Summary: Constructor of the class
	// Precondition: Size of the graph is given
	// Postcondition: For every vertex a "Bag" created in order to store the
	// adjacent edges.
	public EdgeWeightedGraph(int V) {
		this.V = V;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Edge>();
	}

	// Summary: Adds edge between two vertices.
	// Precondition: Two vertices are given as argument.
	// Postcondition: For each vertex, we add an edge by adding their vertex id's to
	// the related bags. Since it's a undirected graph implementation we perform it
	// for both of them.
	public void addEdge(Edge e) {
		
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}

	// Summary: It finds the adjacent of the given vertex.
	// Precondition: Vertex id is given.
	// Postcondition: Node is added to table
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

	// To return the amount of vertices in the graph.
	public int V() {
		return V;
	}

	// Bag class to hold adjacent edges
	public class Bag<Item> implements Iterable<Item> {

		private Node<Item> first;
		private int n;

		private class Node<Item> {
			private Item item;
			private Node<Item> next;
		}

		public Bag() {
			first = null;
			n = 0;
		}

		// Returns true if this bag is empty.

		public boolean isEmpty() {
			return first == null;
		}

		// Returns the number of items in this bag.
		public int size() {
			return n;
		}

		// Adds the item to this bag.
		public void add(Item item) {
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			n++;
		}

		public Iterator<Item> iterator() {
			return new LinkedIterator(first);
		}

		private class LinkedIterator implements Iterator<Item> {
			private Node<Item> current;

			public LinkedIterator(Node<Item> first) {
				current = first;
			}

			public boolean hasNext() {
				return current != null;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			public Item next() {
				if (!hasNext())
					return null;
				Item item = current.item;
				current = current.next;
				return item;
			}
		}
	}
}
