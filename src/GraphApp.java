import java.util.*;
import org.json.*;
import java.io.*;

public class GraphApp {
	static JSONObject airportsID;
	static JSONObject planeRoute;
	public static void main(String args[]){
		//test json
		getFile("airports-name.json", airportsID);
		getFile("SQ-routes.json", planeRoute);
		System.out.print(airportsID.getInt("Bangkok"));
	}
	public static void main2(String args[]){
		Scanner sc = new Scanner(System.in);
		int graphSize = 5;
		Graph g = new Graph(graphSize);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		
		g.printGraph();
		g.BFS(1,2);
	}
	public static void getFile(String filename, JSONObject returnArray){
		try{
			FileReader filereader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			String line = bufferedreader.readLine();
			bufferedreader.close();
			//While we have read in a valid line
			JSONObject obj = new JSONObject(line);
			returnArray = obj;
		}
		catch(FileNotFoundException filenotfoundexception)
		{
			System.out.println("File not found.");
		}
		catch(IOException ioexception)
		{
			System.out.println("File input error occured!");
			ioexception.printStackTrace();
		}
	}
}
