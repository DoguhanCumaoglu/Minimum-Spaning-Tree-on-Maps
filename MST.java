
//-----------------------------------------------------
//Title: MST class
//Author:Doruk Arslan
//Description: MST class to find minimum spanning tree on intended graph.
import java.util.ArrayList;

public class MST {

	String cities;
	String[] list;
	EdgeWeightedGraph g;
	int rootVertex = 0;
	String cityNamesArray[];

	// Summary: Constructor of the class.
	// Precondition: Intended city list and related edges are given as parameter.
	// Postcondition: A graph is created by considering the given edges and
	// vertices(city names). Prim's algorithm ran on the created graph
	public MST(String cities, String[] list) {
		this.cities = cities;
		this.list = list;
		g = new EdgeWeightedGraph(cities.length());
		createGraph();
		Prim p = new Prim(g);
		PrintMST(p.mst());

	}

	// Summary: It adds obtained from the input to the graph that created in
	// constructor.
	// Precondition: Graph is empty.
	// Postcondition: By parsing the edge array obtained from the input, required
	// edge objects created and added to the graph.
	public void createGraph() {
		cityNamesArray = cities.split(" ");

		for (String data : list) {
			String divided[] = data.split(" ");
			int FirstVertex = findVertexNo(cityNamesArray, divided[0]);
			int SecondVertex = findVertexNo(cityNamesArray, divided[1]);
			int Distance = Integer.parseInt(divided[2]);
			Edge e = new Edge(FirstVertex, SecondVertex, Distance);

			g.addEdge(e);
		}
	}

	// Summary: It prints the elements in the mst by considering the alphabetical
	// order.
	// Precondition: Iterable mst that holds edges given as a parameter.
	// Postcondition: By considering their level order, appropriate edge is printing
	// in every iteration.
	public void PrintMST(Iterable<Edge> mst) {
		System.out.println(totalWeightMST(mst));

		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		for (Edge e : mst) {
			edgeList.add(e);
		}
		// Until printing all edges
		// When a edge is processed exchange it with a temporary edge with all the
		// values as -1
		while (!Control(edgeList)) {
			for (int i = 0; i < edgeList.size(); i++) {

				// If already processed that edge.
				if (edgeList.get(i).either() == -1) {
					continue;
				}
				// To print last edge in the list.
				if (i == edgeList.size() - 1) {
					System.out.print(cityNamesArray[edgeList.get(i).either()] + " ");
					System.out.print(cityNamesArray[edgeList.get(i).other(edgeList.get(i).either())] + " ");
					System.out.println(Math.round(edgeList.get(i).weight));
					Edge e = new Edge(-1, -1, -1);
					edgeList.set(i, e);
					break;
				}
				// If there are vertex with same destination and weight.
				if (edgeList.get(i).either() == edgeList.get(i + 1).either()
						&& edgeList.get(i).weight == edgeList.get(i + 1).weight) {

					int firstEnd = edgeList.get(i).either();
					int firstOther = edgeList.get(i).other(firstEnd);

					int nextEnd = edgeList.get(i + 1).either();
					int nextOther = edgeList.get(i + 1).other(nextEnd);

					// Means next vertices name is greater alphabetically.
					if (cityNamesArray[nextOther].compareTo(cityNamesArray[firstOther]) < 0) {
						continue;
					} else {
						System.out.print(cityNamesArray[edgeList.get(i).either()] + " ");
						System.out.print(cityNamesArray[edgeList.get(i).other(edgeList.get(i).either())] + " ");
						System.out.println(Math.round(edgeList.get(i).weight));
						Edge e = new Edge(-1, -1, -1);
						edgeList.set(i, e);
						break;
					}

				}
				// If next vertex does not have same destination and weight
				else {
					System.out.print(cityNamesArray[edgeList.get(i).either()] + " ");
					System.out.print(cityNamesArray[edgeList.get(i).other(edgeList.get(i).either())] + " ");
					System.out.println(Math.round(edgeList.get(i).weight));
					Edge e = new Edge(-1, -1, -1);
					edgeList.set(i, e);
					break;
				}
			}
		}

	}

	// Summary: Checks whether the all arrays processed
	// Precondition: List of all edges given
	// Postcondition: Since an edge marked with -1 values when processed, we check
	// if all edges marked in order to understand all edges processed.
	public boolean Control(ArrayList<Edge> edgeList) {

		for (Edge e : edgeList) {
			if (e.either() != -1) {
				return false;

			}
		}
		return true;

	}

	// Summary: It returns the total weight of the minimum spanning tree.
	// Precondition: Edges in the mst are given.
	// Postcondition: By iterating through given edges and adding their weights,
	// total weight is returned.
	public int totalWeightMST(Iterable<Edge> mst) {
		int totalWeight = 0;
		for (Edge e : mst) {
			totalWeight += e.weight;
		}
		return totalWeight;
	}

	// Summary: It founds the vertex ID of a given city Name.
	// Precondition: List of names and intended city name is given.
	// Postcondition: Since vertex id is the index of the city in the name array, we
	// find the relevant index and return it.
	public int findVertexNo(String[] list, String name) {

		for (int i = 0; i < list.length; i++) {
			if (name.equals(list[i])) {

				return i;
			}
		}
		return -1;
	}

}
