package dataStructure;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import utils.Point3D;


public class DGraph implements graph{
	public int keyNum;
	public LinkedList<Vertex> ver;
	public LinkedList<LinkedList<Edge>> edge;
	public int edgesSize;
	public int mc;

	public DGraph() {
		this.ver=new LinkedList<>();
		this.edge=new LinkedList<LinkedList<Edge>>();
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
		if(n!=null) {
			Vertex tmp=(Vertex)n;
			tmp.key=keyNum;
			keyNum++;
			this.ver.add(tmp);
			this.edge.add(new LinkedList<Edge>());
			this.mc++;
		}
	}

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
		if(src<this.edge.size()&&dest<this.edge.size()) {
			this.edge.get(src).add(new Edge(src,dest,w));
			this.edgesSize++;
			this.mc++;
		}
	}

	@Override
	public Collection<node_data> getV() {
		if(this.nodeSize()>0) {
			LinkedList<Vertex> vertmp=new LinkedList<>(this.ver);
			vertmp.removeAll(Collections.singleton(null));
			return (Collection)vertmp;
		}
		else {
			return (Collection)new LinkedList<Vertex>();
		}
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		LinkedList<Edge> edgetmp=new LinkedList<>(this.edge.get(node_id));
		edgetmp.removeAll(Collections.singleton(null));
		return (Collection)edgetmp;
	}

	@Override
	public node_data removeNode(int key) {
		if(key>=this.nodeSize()||key<0) {
			System.out.println("Invalid vertex key");
			return null;
		}
			Vertex temp=this.ver.get(key);
			this.ver.set(key, null);
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
				this.mc++;
				return temp2;
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
			for (int j = 0;this.edge.get(i)!=null&&j<this.edge.get(i).size(); j++) {
				System.out.println(this.edge.get(i).get(j).getInfo()+" ,");
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
			tmp.edge.add(new LinkedList<Edge>());
		}

		for (int i = 0; i < this.edge.size(); i++) {
			for (int j = 0; j < this.edge.get(i).size(); j++) {
				tmp.edge.get(i).add(this.edge.get(i).get(j).copy());
			}
		}

		tmp.edgesSize=this.edgesSize;

		tmp.mc=this.mc;

		return tmp;
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


		//		g.connect(0, 1, 10);
		//		g.connect(0, 2, 10);

		System.out.println(g.getV());
	}
}
