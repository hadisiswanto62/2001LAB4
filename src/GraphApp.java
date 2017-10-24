import java.util.*;
import org.json.*;
import java.io.*;

public class GraphApp {
	static JSONArray arrayData;
	public static void main(String args[]){
		//test json
		getFile();
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
	public static void getFile(){
		try{
			FileReader filereader = new FileReader("test1.json");
			BufferedReader bufferedreader = new BufferedReader(filereader);
			String line = bufferedreader.readLine();
			bufferedreader.close();
			//While we have read in a valid line
			JSONObject obj = new JSONObject(line);
			arrayData = obj.getJSONArray("data");
		}
	}
}
