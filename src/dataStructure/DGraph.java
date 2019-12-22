package dataStructure;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import utils.Point3D;


public class DGraph implements graph{
	static int keyNum;
	LinkedList<Vertex> ver;
	LinkedList<LinkedList<Edge>> edge;
	static int edgesSize=0;
	int mc;

	public DGraph() {
		this.ver=new LinkedList<>();
		this.edge=new LinkedList<LinkedList<Edge>>();
		this.keyNum=0;
		this.mc=0;
	}

	@Override
	public node_data getNode(int key) {
		if(key<this.ver.size()) {
			if(key==this.ver.get(key).key) {
				return this.ver.get(key);
			}
			else return null;
		}
		else {
			return null;
		}
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(src<this.edge.size()&&dest<this.edge.size()) {
			int i=this.edge.get(src).indexOf(new Edge(src, dest, 10));
			if(i==-1) {
				return null;
			}
			else {
				return this.edge.get(src).get(i);
			}
		}
		return null;
	}

	@Override
	public void addNode(node_data n) {
		Vertex tmp=(Vertex)n;
		tmp.key=keyNum;
		keyNum++;
		this.ver.add(tmp);
		this.edge.add(new LinkedList<Edge>());
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(src<this.edge.size()&&dest<this.edge.size()) {
			this.edge.get(src).add(new Edge(src,dest,w));
			this.edgesSize++;
		}
	}

	@Override
	public Collection<node_data> getV() {
		LinkedList<Vertex> vertmp=new LinkedList<>(this.ver);
		vertmp.removeAll(Collections.singleton(null));
		return (Collection)vertmp;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		LinkedList<Edge> edgetmp=new LinkedList<>(this.edge.get(node_id));
		edgetmp.removeAll(Collections.singleton(null));
		return (Collection)edgetmp;
	}

	@Override
	public node_data removeNode(int key) {
		Vertex temp=this.ver.get(key);
		this.ver.remove(key);
		for (int i = key; i < ver.size(); i++) {
			this.ver.get(i).key--;
		}
		keyNum--;
		updateEdge(key);


		return temp;
	}

	private void updateEdge(int key) {
		if(key<this.edgeSize()) {
			for (int i = 0; i < this.edge.size(); i++) {
				if(i!=key) {
					int j=this.edge.get(i).indexOf(new Edge(i, key, 10));
					if(j!=-1) {
						this.edge.get(i).remove(j);
					}
				}
				else if(i==key) {
					this.edge.set(i, null);
				}
			}
		}
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(src<this.edge.size()&&dest<this.edge.size()) {
			Edge temp=new Edge(src, dest, 10);
			int i=this.edge.get(src).indexOf(temp);
			if(i==-1) {
				return null;
			}
			else {
				Edge temp2=new Edge(this.edge.get(src).get(i));
				this.edge.get(src).remove(i);
				return temp2;
			}
		}
		return null;
	}
	
	public String toString() {
		System.out.println("Vertex List:");
		for (int i = 0; i < this.nodeSize(); i++) {
			if(this.ver.get(i)!=null) {
				System.out.println(this.ver.get(i).getInfo());
			}
		}
		System.out.println();
		System.out.println("Edges");
		for (int i = 0; i < this.edge.size(); i++) {
			System.out.print("list number "+i+":");
			System.out.println();
			System.out.println();
			for (int j = 0;this.edge.get(i)!=null&&j<this.edge.get(i).size(); j++) {
					System.out.println(this.edge.get(i).get(j).getInfo()+" ,");
				}
		}
		return"";
	}
	
	@Override
	public int nodeSize() {
		return this.ver.size();
	}

	@Override
	public int edgeSize() {
		return this.edgesSize;
	}

	@Override
	public int getMC() {
		return this.mc;
	}
}
