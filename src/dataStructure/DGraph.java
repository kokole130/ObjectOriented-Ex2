package dataStructure;

import java.util.Collection;
import java.util.LinkedList;

public class DGraph implements graph{
	static int keyNum=0;
	LinkedList<Vertex> ver;
	LinkedList<LinkedList<Edge>> edge;//the outer linkedlist represent the source id by indexes, and the inner linkedlist represent all the edges with the same source id
	String edges;
	int EdgeSize;
	int mc;
	
	public DGraph() {
		this.ver=new LinkedList<>();
		this.edges="";
		
		this.EdgeSize=0;
		this.mc=0;
	}
	
	@Override
	public node_data getNode(int key) {
		if(key<this.ver.size()) {
			return this.ver.get(key);
		}
		else {
			return null;
		}
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(this.edges.contains("s"+src+"d"+dest)) {
			double weight=checkWeight(src, dest);
			Edge data=new Edge(this.ver.get(src), this.ver.get(dest), weight);
			return data;
		}
		else {
			return null;
		}
	}

	
	private double checkWeight(int src,int dest) {//beside function
		String temp="s"+src+"d"+dest;
		int index=this.edges.indexOf(temp);//to get to the beginning of the edge
		index=index+4;//to get 'w' position
		String temp2=this.edges.substring(index+1);
		int after=temp2.indexOf(',');
		double weight=Double.parseDouble(temp2.substring(index+1,after));
		
		return weight;
	}
	
	@Override
	public void addNode(node_data n) {
		
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<node_data> getV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
