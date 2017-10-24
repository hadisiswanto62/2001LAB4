import java.util.*;

public class Node {
	public int id;
	public String name;
	private ArrayList<Edge> EdgeList = new ArrayList<Edge>();
	
	public void setId(int id){
		this.id = id;
	}
	public void addEdge(Edge edge){
		EdgeList.add(edge);
	}
	public ArrayList<Edge> getEdgeList(){
		return EdgeList;
	}
}
