package algorithms;

import java.util.LinkedList;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Vertex;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	DGraph graph;

	@Override
	public void init(graph g) {
		this.graph=(DGraph)g;
	}

	@Override
	public void init(String file_name) {

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		for (int i = 0; i < this.graph.ver.size(); i++) {
			for (int j = 0; j < this.graph.ver.size(); j++) {
				tagReset();
				if(this.graph.ver.get(i)!=null&&this.graph.ver.get(j)!=null) {
					if(i!=j) {
						if(!isConnected(i, j,i))return false;
					}
				}
			}
		}
		return true;
	}

	private boolean isConnected(int src,int dest,int originalSrc) {
		if(this.graph.edge.get(src).contains(new Edge(src, dest, 10))) return true;

		for (int i = 0; i < this.graph.edge.get(src).size();i++) {
			if(this.graph.edge.get(src).get(i).getDest()==originalSrc) continue;
			if(this.graph.edge.get(src).get(i).getTag()==0) {
				this.graph.edge.get(src).get(i).setTag(1);
				if(isConnected(this.graph.edge.get(src).get(i).getDest(), dest,originalSrc))
					return true;
			}
		}
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		tagReset();
		weightReset();
		if(this.graph.ver.get(src)==null) {
			System.out.println("there is not such as src: "+src);
			return -1;
		}
		if(this.graph.ver.get(dest)==null) {
			System.out.println("there is not such as dest : "+dest);
			return -1;
		}

		this.graph.ver.get(src).setWeight(0);
		this.graph.ver.get(src).setTag(1);
		int current=src;
		int i=0;
		while (!allVisited()) {
			int tmp=this.graph.edge.get(current).get(i).getDest();
			//a specific dest edge in current list

			double verWeight=this.graph.ver.get(current).getWeight();
			//the current weight of current vertex

			double edgeWeight=this.graph.edge.get(current).get(i).getWeight();
			//the weight of the 'current to tmp' edge 

			if(this.graph.ver.get(tmp).getWeight()>verWeight+edgeWeight) {
				this.graph.ver.get(tmp).setWeight(verWeight+edgeWeight);
				this.graph.ver.get(tmp).setLastKey(current);
			}
			if(i==this.graph.edge.get(current).size()-1) {
				current=minWeightDest(current);
				i=0;
				this.graph.ver.get(current).setTag(1);
				continue;
			}
			i++;
		}
		return this.graph.ver.get(dest).getWeight();

	}

	private int minWeightDest(int current) {
		int minindex=0;
		double minWeight=Integer.MAX_VALUE;
		
		for (int i = 0; i < this.graph.ver.size(); i++) {
			if(this.graph.ver.get(i)==null) {
				continue;
			}
			double temp=this.graph.ver.get(i).getWeight();
			//weight of the current edge in 'current' list
			
			int destTag=this.graph.ver.get(i).getTag();
			//the tag of the current dest vertex we check

			if((temp<minWeight)&&(destTag!=1)) {
				minWeight=temp;
				minindex=i;
			}

		}
		return minindex;
	}

	private boolean allVisited() {
		for (int i = 0; i < this.graph.nodeSize(); i++) {
			if(this.graph.ver.get(i)!=null&&this.graph.ver.get(i).getTag()==0)return false;
		}
		return true;
	}

	private void weightReset() {
		for (int i = 0; i < this.graph.ver.size(); i++) {
			this.graph.ver.get(i).setWeight(Integer.MAX_VALUE);
		}
	}

	private void tagReset() {
		for (int i = 0; i < this.graph.nodeSize(); i++) {
			if(this.graph.ver.get(i)!=null) {
				this.graph.ver.get(i).setTag(0);
				for (int j = 0; j < this.graph.edge.get(i).size(); j++) {
					this.graph.edge.get(i).get(j).setTag(0);
				}
			}
		}
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		if(this.graph.ver.get(src)==null||this.graph.ver.get(dest)==null) {
			return null;
		}
		this.shortestPathDist(src, dest);
		LinkedList<node_data> tmp=new LinkedList<node_data>();
		tmp.add(this.graph.ver.get(dest));
		Vertex v=this.graph.ver.get(dest);
		while(v.getKey()!=src) {
			v = this.graph.ver.get(this.graph.ver.get(v.getKey()).getLastKey());
			tmp.add(v);
		}
		LinkedList<node_data> tmp2=new LinkedList<node_data>();
		for (int i = tmp.size()-1; i >= 0; i--) {
			tmp2.add(tmp.get(i));
		}
		return tmp2;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		DGraph temp=new DGraph();
		temp.edge=new LinkedList<>(this.graph.edge);
		temp.ver=new LinkedList<>(this.graph.ver);
		temp.keyNum=this.graph.getkeyNum();
		temp.mc=this.graph.mc;
		temp.edgesSize=this.graph.edgesSize;
		return temp;
	}


	public static void main(String[] args) {
		Graph_Algo g=new Graph_Algo();
		Vertex v1=new Vertex(new Point3D(2,2,2));
		Vertex v2=new Vertex(new Point3D(2,4,6));
		Vertex v3=new Vertex(new Point3D(3,2,-9));
		Vertex v4=new Vertex(new Point3D(-1,0,0));
		Vertex v5=new Vertex(new Point3D(-5,0,8));

		g.init(new DGraph());
		g.graph.addNode(v1);
		g.graph.addNode(v2);
		g.graph.addNode(v3);
		g.graph.addNode(v4);
		g.graph.addNode(v5);

		g.graph.connect(0,1,10);
		g.graph.connect(1,2,1);
		g.graph.connect(0,4,5);
		g.graph.connect(1,4,2);
		g.graph.connect(2,3,4);
		g.graph.connect(3,0,7);
		g.graph.connect(3,2,6);
		g.graph.connect(4,1,3);
		g.graph.connect(4,3,2);


		//System.out.println(g.isConnected(4,2,4));
		List<node_data> s=g.shortestPath(0, 2);
		for (int i = 0; i < s.size(); i++) {
			System.out.print(s.get(i).getKey()+",");
		}
	}
}