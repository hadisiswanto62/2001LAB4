import java.util.*;

public class Node {
	public int id;
	public String name;
	private ArrayList<Edge> EdgeList = new ArrayList<Edge>();
	private HashMap<Integer,Boolean> Nodes = new HashMap<Integer,Boolean>();
	
	public void setId(int id){
		this.id = id;
	}
	public void addEdge(Edge edge){
		if(Nodes.containsKey(edge.getDestination())) {
			return;
		}
		Nodes.put(edge.getDestination(), true);
		EdgeList.add(edge);
	}
	public ArrayList<Edge> getEdgeList(){
		return EdgeList;
	}
}
