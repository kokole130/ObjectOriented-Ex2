package dataStructure;

import java.util.Collection;
import java.util.LinkedList;

public class DGraph implements graph{
	static int keyNum;
	LinkedList<Vertex> ver;
	LinkedList<Edge> edge;
	//String edges;
	//int EdgeSize;
	int mc;

	public DGraph() {
		this.ver=new LinkedList<>();
		this.edge=new LinkedList<>();
		this.keyNum=1;
		this.mc=0;
	}
	
	private int hashCode(int src,int dest) {
		
		return 0;//index in the edges list
	}

	@Override//@@@@@@@@@@@@@@
	public node_data getNode(int key) {
		if(key<this.ver.size()) {
			if(key==this.ver.get(key-1).key) {
				return this.ver.get(key-1);
			}
			else return null;
		}
		else {
			return null;
		}
	}

	@Override//@@@@@@@@@@@@@@
	public edge_data getEdge(int src, int dest) {
//		if(this.edges.contains("s"+src+"d"+dest)) {
//			double weight=checkWeight(src, dest);
//			Edge data=new Edge(this.ver.get(src), this.ver.get(dest), weight);
//			return data;
//		}
//		else {
//			return null;
//		}
		return null;
	}

//
//	private double checkWeight(int src,int dest) {//beside function
//		String temp="s"+src+"d"+dest;
//		int index=this.edges.indexOf(temp);//to get to the beginning of the edge
//		index=index+4;//to get 'w' position
//		String temp2=this.edges.substring(index+1);
//		int after=temp2.indexOf(',');
//		double weight=Double.parseDouble(temp2.substring(index+1,after));
//
//		return weight;
//	}

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
		return this.ver.size();
	}

	@Override
	public int edgeSize() {
		return this.edge.size();
	}

	@Override
	public int getMC() {
		return this.mc;
	}

}
