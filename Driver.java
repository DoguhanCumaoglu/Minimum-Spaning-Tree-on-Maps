
//-----------------------------------------------------
//Title: Driver class
//Author:Doruk Arslan
//Description: Main class of the application
//----------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner key = new Scanner(System.in);

		String fileInfo = key.nextLine();

		File file = new File(fileInfo);

		key.close();

		Scanner filekey = new Scanner(file);

		// To read given city names.
		String cities = filekey.nextLine();

		ArrayList<String> edgeList = new ArrayList<String>();

		// To read given edges
		while (filekey.hasNextLine()) {
			String currentEdge = filekey.nextLine();
			edgeList.add(currentEdge);
		}

		String[] list = new String[edgeList.size()];

		for (int i = 0; i < list.length; i++) {
			list[i] = edgeList.get(i);
		}
		// To find minimum spanning tree with respect to the given input.
		MST mst = new MST(cities, list);

	}

}
