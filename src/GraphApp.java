import java.util.*;
import org.json.*;
import java.io.*;

public class GraphApp {
	private static int size = 0;
	private static int edgeSize = 0;
	//AA AC SQ WN AK
	private static String airlineCode = "WN";
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
			String id[] = new String[]{
					"1150", "2000", "3000", "4000", "5000", "6000", "7000", "8000"
			};
			for (String filename: id){
				//System.out.println(airlineCode+"-"+Integer.toString(size)+"-"+filename+'.json');
				planeRoute = getFile("airport-data//routes-"+airlineCode+"-"+Integer.toString(size)+"-"+filename+".json");
				edgeSize = 0;
				Graph graph = new Graph(size);
				graph = getGraph();
				Random rand = new Random();
				int totalFlight = 0;
				//System.out.println("Number of tests :");
				//int testCase = scan.nextInt();
				int testCase = 10000;
				start = System.nanoTime();
				for (int i=0; i<testCase; i++){
					totalFlight += graph.BFS(rand.nextInt(size), rand.nextInt(size), false);
				}
				stop = System.nanoTime();
				System.out.println(filename+", "+(stop-start)/testCase);
				//System.out.printf("Graph have %d nodes and %d edges\n",size,edgeSize);
				//System.out.printf("Average flight = %f", (double)totalFlight/testCase);
			}
		}
//		gr.BFS(0, 8);
//		System.out.println(getAirportName(3)+getRoute(3));
		
	}
	
	public static void mainx(String[] args){
		int graphSize = 100;
		Graph g = new Graph(graphSize);
		HashMap<Integer,ArrayList<Integer>> Edges = new HashMap<Integer,ArrayList<Integer>>();

		for (int i=0; i<250; i++){
			Random rand = new Random();
			int a,b;
			do{
			a = rand.nextInt(graphSize);
			b = rand.nextInt(graphSize);
			if(Edges.get(a) == null){
				ArrayList<Integer> al = new ArrayList<Integer>();
				
				Edges.put(a, al);
				break;
			}
			} while (Edges.get(a).contains(b));
			Edges.get(a).add(b);
			System.out.print(a+"     ");
			for(Integer qwe: Edges.get(a)){
				System.out.print(qwe+" ");
			}
			System.out.println();
		}
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
