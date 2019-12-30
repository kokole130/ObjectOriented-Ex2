package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import utils.Point3D;


public class DGraph implements graph,Serializable{
	public int keyNum;
	public ArrayList<Vertex> ver;
	public HashMap<Integer,HashMap<Integer,Edge>> edge;
	public int edgesSize;
	public int nodesSize;
	public int mc;

	public DGraph() {
		this.ver=new ArrayList<>();
		this.edge=new HashMap<Integer,HashMap<Integer,Edge>>();
		this.keyNum=0;
		this.mc=0;
		this.edgesSize=0;
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
			if(this.ver.get(src)!=null) {
				if(this.edge.get(src).containsKey(dest)) {
					return this.edge.get(src).get(dest);
				}
			}
		}
		return null;
	}

	@Override
	public void addNode(node_data n) {
		if(n!=null) {
			Vertex tmp=(Vertex)n;
			tmp.key=keyNum;
			this.ver.add(tmp);
			this.edge.put(keyNum,new HashMap<Integer,Edge>());
			nodesSize++;t
			keyNum++;
			this.mc++;
		}
	}//this.edge.get(src).get(dest)

	@Override
	public void connect(int src, int dest, double w) {
		if((src<0&&dest<0)||(dest>=this.nodeSize()&&src>=this.nodeSize())) {
			System.out.println("there is not such as src or dest");
			return ;
		}
		if(this.ver.get(src)==null) {
			System.out.println("there is not such as src: "+src);
			return ;
		}
		if(this.ver.get(dest)==null) {
			System.out.println("there is not such as dest : "+dest);
			return ;
		}
		if(src==dest) {
			System.out.println("you can't connect the same vertex");
			return;
		}
		if(src<this.edge.size()&&dest<this.edge.size()) {
			this.edge.get(src).put(dest, new Edge(src,dest,w));
			this.edgesSize++;
			this.mc++;
		}
	}

	@Override
	public Collection<node_data> getV() {
		if(this.nodeSize()>0) {
			ArrayList<Vertex> vertmp=new ArrayList<>(this.ver);
			vertmp.removeAll(Collections.singleton(null));
			return (Collection)vertmp;
		}
		else {
			return (Collection)new ArrayList<Vertex>();
		}
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return (Collection<edge_data>) this.edge.get(node_id);
	}

	@Override
	public node_data removeNode(int key) {
		if(key>=this.ver.size()||key<0) {
			System.out.println("Invalid vertex key");
			return null;
		}
		Vertex temp=new Vertex(this.ver.get(key).getKey(),this.ver.get(key));
		this.ver.set(key, null);
		nodesSize--;
		updateEdge(key);
		this.mc++;
		return temp;
	}

	/**
	 * 
	 * @param key
	 */
	private void updateEdge(int key) {
		if(key<this.edgeSize()) {
			for (int i = 0; i < this.edge.size(); i++) {
				if(this.edge.get(i)!=null) {
					if(i!=key) {
						if(this.edge.get(i).containsKey(key)) {
							this.edge.get(i).remove(key);
							edgesSize--;
						}
					}
				}
				else if(i==key) {
					edgesSize=edgesSize-this.edge.get(i).size();
					this.edge.replace(i, null);
				}
			}
		}
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(src<this.edge.size()&&dest<this.edge.size()) {
			if(this.ver.get(src)!=null) {
				if(this.edge.get(src).containsKey(dest)) {
					Edge temp2=new Edge(this.edge.get(src).get(dest));
					this.edge.get(src).remove(dest);
					edgesSize--;
					this.mc++;
					return temp2;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 */
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
			for (int j = 0;this.edge.get(i)!=null&&j<this.edge.size(); j++) {
				if(this.edge.get(i).containsKey(j)) {
					System.out.println(this.edge.get(i).get(j).getInfo()+" ,");
					System.out.println();
				}
			}
		}
		return"";
	}

	/**
	 * Function that do deep copy to graph
	 * @return the graph that we copy
	 */
	public DGraph copy() {
		DGraph tmp = new DGraph();
		tmp.keyNum=this.keyNum;

		for (int i = 0; i < this.nodeSize(); i++) {
			tmp.ver.add(this.ver.get(i).copy());
			tmp.edge.put(i,new HashMap<Integer,Edge>());
		}

		for (int i = 0; i < this.edge.size(); i++) {
			for (int j = 0; j < this.edge.size(); j++) {
				if(this.ver.get(i)==null) {
					tmp.edge.put(i, null);
				}
				else {
					if(this.edge.get(i).containsKey(j)) {
						tmp.edge.get(i).put(this.edge.get(i).get(j).getDest(),this.edge.get(i).get(j).copy());
					}
				}
			}
		}

		tmp.edgesSize=this.edgesSize;

		tmp.mc=this.mc;

		return tmp;
	}

	@Override

	public int nodeSize() {
		return this.nodesSize;
	}

	@Override
	public int edgeSize() {
		return this.edgesSize;
	}

	@Override
	public int getMC() {
		return this.mc;
	}

	public int getkeyNum() {
		return this.keyNum;
	}

	public static void main(String[] args) {
		Vertex v1=new Vertex(new Point3D(2,2,2));
		//Vertex v2=null;
		Vertex v3=new Vertex(new Point3D(2,2,2));
		Vertex v4=new Vertex(new Point3D(2,2,2));

		DGraph g=new DGraph();

		g.addNode(v1);
		//	g.addNode(v2);
		g.addNode(v3);
		g.addNode(v4);

		HashMap<Integer, HashMap<Integer,Edge>> s = new HashMap<>();
		s.put(0, new HashMap<Integer,Edge>());
		s.put(1, new HashMap<Integer,Edge>());

		s.put(2, new HashMap<Integer,Edge>());
		s.get(2).put(3, new Edge(2, 3, 10));
		s.put(3, new HashMap<Integer,Edge>());
		//		g.connect(0, 1, 10);
		//		g.connect(0, 2, 10);

		System.out.println(s);
	}
}
