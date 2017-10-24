import java.util.*;
import org.json.*;
import java.io.*;

public class GraphApp {
	public static JSONObject airportsID = getFile("airports-name.json");
	public static JSONObject planeRoute = getFile("SQ-routes.json");
	
	public static void main(String args[]){
		System.out.println(getRoute(3));
	}
	
	public static void test(){
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
