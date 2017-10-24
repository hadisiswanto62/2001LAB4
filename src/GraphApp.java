import java.util.*;
import org.json.*;
import java.io.*;

public class GraphApp {
	static JSONObject data;
	public static void main(String args[]){
		
		getFile();
		System.out.println(data);
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
	
	public static void getFile() {
		try
		{
			FileReader filereader = new FileReader("airports-name.json");
			BufferedReader bufferedreader = new BufferedReader(filereader);
			String line = bufferedreader.readLine();
			bufferedreader.close();
			//While we have read in a valid line
			data = new JSONObject(line);
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
