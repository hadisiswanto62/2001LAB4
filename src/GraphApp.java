import java.util.*;
import org.json.*;
import java.io.*;

public class GraphApp {
	private static final int size = 100;
	public static JSONObject airportsID = getFile("airports-name.json");
	public static JSONObject planeRoute = getFile("SQ-routes.json");
	
	public static void main(String args[]){
//		test();
		long start,stop;
		Graph gr = new Graph(size);
		gr = getGraph();
//		gr.printGraph();
		String dep,arr;
		Scanner scan = new Scanner(System.in);
		System.out.println("Insert departure city");
		dep = scan.nextLine();
		System.out.println("Insert arrival city");
		arr = scan.nextLine();
		start = System.nanoTime();
		gr.BFS(getAirportID(dep), getAirportID(arr));
		stop = System.nanoTime();
		System.out.println("CPU Time = "+(stop-start)+" ns");
//		gr.BFS(0, 8);
//		System.out.println(getAirportName(3)+getRoute(3));
		
	}
	
	public static void test(){
		int graphSize = 5;
		Graph g = new Graph(graphSize);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(3, 4);
		g.printGraph();
		g.BFS(1,4);
	}
	
	public static int getAirportID(String airportName){
		return airportsID.getInt(airportName);
	}
	public static String getAirportName(int airportID){
		Iterator<String> keys = airportsID.keys();
		while (keys.hasNext()){
			String key = keys.next();
			if (airportID == getAirportID(key)){
				return key;
			}
		}
		return "NOT FOUND";
	}
	public static JSONArray getRoute(int airportID){
		Iterator<String> keys = planeRoute.keys();
		while (keys.hasNext()){
			String key = keys.next();
			if (airportID == getAirportID(key)){
				return planeRoute.getJSONArray(key);
			}
		}
		return null;
	}
	
	public static Graph getGraph() {
		Graph graph = new Graph(size);
		for(int i=0;i<size;i++)
		{
			graph.setNodeName(i,getAirportName(i));
			JSONArray array = new JSONArray();
			array = getRoute(i);
			Integer length = array.length();
			for(int j=0; j<length;j++)
			{
				String name = array.getString(j);
				graph.addEdge(i, getAirportID(name));
			}
		}
		return graph;
	}
	
	public static JSONObject getFile(String filename){
		try{
			FileReader filereader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			String line = bufferedreader.readLine();
			bufferedreader.close();
			//While we have read in a valid line
			JSONObject obj = new JSONObject(line);
			return obj;
		}
		catch(FileNotFoundException filenotfoundexception)
		{
			System.out.println("File not found.");
			return null;
		}
		catch(IOException ioexception)
		{
			System.out.println("File input error occured!");
			ioexception.printStackTrace();
			return null;
		}
	}
}
