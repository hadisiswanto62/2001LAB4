import java.util.*;
import java.io.*;

public class Graph {
	private Node[] nodeList;
	private int totalNode;
	
	public Graph(int n){
		this.totalNode = n;
		nodeList = new Node[totalNode];
		for (int i=0; i<totalNode; i++){
			nodeList[i] = new Node();
			nodeList[i].setId(i);
		}
	}
	public void addEdge(int node1, int node2){
		Edge e1 = new Edge(node1, node2);
		nodeList[node1].addEdge(e1);
		Edge e2 = new Edge(node2, node1);
		nodeList[node2].addEdge(e2);
	}
	public void setNodeName(int i, String name){
		this.nodeList[i].name = name;
	}
	
	public void BFS(int s, int target){
		boolean[] marked = new boolean[this.totalNode];
		ArrayList<Integer> queue = new ArrayList<Integer>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		HashMap<Integer,Integer> source = new HashMap<Integer,Integer>();
		marked[s] = true;
		boolean found = false;
		int init = s;
		queue.add(s);
//		System.out.print(this.nodeList[s].id+", ");
		while (queue.size() != 0 && !found){
			s = queue.get(0);
			queue.remove(0);
			//System.out.print(this.nodeList[s].id+", ");
			for (Edge i : this.nodeList[s].getEdgeList()){
				int n = i.getDestination();
				source.put(n,s);
				if(n == target){
					found = true;
//					System.out.print(this.nodeList[n].id+" ");
					break;
				}
				if (!marked[n]){
					marked[n] = true;
					queue.add(n);
				}
			}
		}
		
		int temp = target;
		path.add(temp);
		
		while (temp != init) {
			path.add(source.get(temp));
			temp = source.get(temp);
		}
		for (int i = path.size() - 1; i >= 0;i--) {
			System.out.print(path.get(i) + " ");
		}
		
	}
	
	public void printGraph(){
		for (int i=0; i<this.totalNode; i++){
			System.out.print(i+ " = ");
			for (Edge edge : this.nodeList[i].getEdgeList()){
				System.out.print(edge.getDestination() + ", ");
			}
			System.out.println();
		}
		
	}
}
