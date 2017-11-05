import java.util.*;
import org.json.*;
import java.io.*;

public class GraphApp {
	private static int size = 0;
	private static int edgeSize = 0;
	//AA AC SQ WN
	private static String airlineCode = "AK";
	public static JSONObject airportsID = getFile("airports-name-"+airlineCode+".json");
	public static JSONObject planeRoute = getFile("routes-"+airlineCode+".json");
	
	public static void main(String args[]){
//		test();
		size = airportsID.length();
		long start,stop;
		Graph gr = new Graph(size);
		gr = getGraph();
		//gr.printGraph();
		String dep,arr;
		Scanner scan = new Scanner(System.in);
		int option;
		System.out.println("1. See all cities\n2. See route from .. to ..\n3. Stats");
		option = scan.nextInt();
		if (option == 1){
			int count = 1;
			Iterator<String> cities = airportsID.keys();
			while (cities.hasNext()){
				System.out.printf("%d. %s\n",count,cities.next());
				count++;
			}
		}
		else if (option == 2){
			System.out.println("Insert departure city");
			dep = scan.next();
			System.out.println("Insert arrival city");
			arr = scan.next();
			start = System.nanoTime();
			gr.BFS(getAirportID(dep), getAirportID(arr), true);
			stop = System.nanoTime();
			System.out.println("CPU Time = "+(stop-start)+" ns");
		}
		else if (option == 3){
			Random rand = new Random();
			int totalFlight = 0;
			System.out.println("Number of tests :");
			int testCase = scan.nextInt();
			start = System.nanoTime();
			for (int i=0; i<testCase; i++){
				totalFlight += gr.BFS(rand.nextInt(size), rand.nextInt(size), false);
			}
			stop = System.nanoTime();
			System.out.println("CPU Time = "+(stop-start)+" ns");
			System.out.printf("Graph have %d nodes and %d edges\n",size,edgeSize);
			System.out.printf("Average flight = %f", (double)totalFlight/testCase);
		}
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
		g.BFS(1,4, true);
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
				edgeSize++;
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
