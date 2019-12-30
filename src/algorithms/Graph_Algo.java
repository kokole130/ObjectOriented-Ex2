package algorithms;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import GUI.GuiGraph;
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

	public DGraph graph;

	@Override
	public void init(graph g) {
		this.graph=((DGraph)g).copy();
	}

	@Override
	public void init(String file_name) {	       
	        try
	        {    
	            FileInputStream file = new FileInputStream(file_name); 
	            ObjectInputStream in = new ObjectInputStream(file); 
	              
	            this.graph = (DGraph)in.readObject(); 
	              
	            in.close(); 
	            file.close(); 
	              
	            System.out.println("Object has been deserialized"); 
	            System.out.println(this.graph);
	        } 
	          
	        catch(IOException ex) 
	        { 
	            System.out.println("IOException is caught"); 
	        } 
	          
	        catch(ClassNotFoundException ex) 
	        { 
	            System.out.println("ClassNotFoundException is caught"); 
	        } 
	}

	@Override
	public void save(String file_name) {          
        try
        {    
            FileOutputStream file = new FileOutputStream(file_name); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
              
            out.writeObject(this.graph); 
              
            out.close(); 
            file.close(); 
              
            System.out.println("Object has been serialized"); 
        }   
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
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
		tagReset();
		return true;
	}

	/**
	 * This is a recursive function that checking if two point on the graph is connected
	 * @param src is the source point
	 * @param dest is the destination point
	 * @param originalSrc in every itration i want to save the ancestor source
	 * @return boolean, true if connected and false if they doesn't 
	 */
	public boolean isConnected(int src,int dest,int originalSrc) {
		if(this.graph.edge.get(src).containsValue(new Edge(src, dest, 10))) return true;

		for (int i = 0; i < this.graph.edge.size();i++) {
			if(this.graph.edge.get(src).containsKey(i)) {
				if(this.graph.edge.get(src).get(i).getDest()==originalSrc) continue;
				if(this.graph.edge.get(src).get(i).getTag()==0) {
					this.graph.edge.get(src).get(i).setTag(1);
					if(isConnected(this.graph.edge.get(src).get(i).getDest(), dest,originalSrc))
						return true;
				}
			}
		}
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		if((src<0&&dest<0)||(dest>=this.graph.ver.size()&&src>=this.graph.ver.size())) {
			System.out.println("there is not such as src or dest");
		}
		if(this.graph.ver.get(src)==null) {
			System.out.println("there is not such as src: "+src);
			return -1;
		}
		if(this.graph.ver.get(dest)==null) {
			System.out.println("there is not such as dest : "+dest);
			return -1;
		}

		if(!this.isConnected(src, dest, src)) {
			return Integer.MAX_VALUE;
		}
		this.graph.ver.get(src).setWeight(0);
		this.graph.ver.get(src).setTag(1);
		int current=src;
		int i=0;
		while (!allVisited()) {
			if(this.graph.edge.get(current).containsKey(i)) {
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
			}
			if(i==this.graph.edge.size()-1) {
				
				current=minWeightDest(current);
				i=0;
				this.graph.ver.get(current).setTag(1);
				continue;
			}
			i++;
			
		}
		double res=this.graph.ver.get(dest).getWeight();
		tagReset();
		weightReset();
		return res;
	}

	/**
	 * Side function for 'shortestPathDist' that checking what is nearest point to the source that isn't visited
	 * @param current is the current point we standing on in the current itraction
	 * @return the key of the nearest point to the source thats not visited
	 */
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

			if((temp<=minWeight)&&(destTag!=1)) {
				minWeight=temp;
				minindex=i;
			}

		}
		return minindex;
	}
	
	/**
	 * Function that checking if all the point in this graph were visited
	 * @return boolean, true if all visited, false if not all visited
	 */
	private boolean allVisited() {
		for (int i = 0; i < this.graph.ver.size(); i++) {
			if(this.graph.ver.get(i)!=null&&this.graph.ver.get(i).getTag()==0)return false;
		}
		return true;
	}

	/**
	 * Function tha reset all the weight of the vertexes to infinite(Integer.MAX_VALUE) 
	 */
	private void weightReset() {
		for (int i = 0; i < this.graph.ver.size(); i++) {
			if(this.graph.ver.get(i)!=null) {
				this.graph.ver.get(i).setWeight(Integer.MAX_VALUE);
			}
		}
	}

	/**
	 * Function that reset all the tags in the vertex and Edges in the graph to 0(Not visited)
	 */
	private void tagReset() {
		for (int i = 0; i < this.graph.ver.size(); i++) {
			if(this.graph.ver.get(i)!=null) {
				this.graph.ver.get(i).setTag(0);
				for (int j = 0; j < this.graph.edge.size(); j++) {
					if(this.graph.edge.get(i)!=null&&this.graph.edge.get(i).containsKey(j)) {
						this.graph.edge.get(i).get(j).setTag(0);
					}
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
		tagReset();
		weightReset();
		return tmp2;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		Graph_Algo tmp = new Graph_Algo();
		tmp.graph=this.graph.copy();
		for (int i = 0; i < tmp.graph.ver.size(); i++) {
			boolean exist=false;
			for (int j = 0; (j<targets.size())&&(!exist); j++) {
				if(tmp.graph.ver.get(i)!=null && targets.get(j)==i) {
					exist=true;
				}
			}
			if(tmp.graph.ver.get(i)!=null&&!exist) {
				tmp.graph.removeNode(i);
			}
		}

		if(!tmp.isConnected()) return null;
		
		double min=Integer.MAX_VALUE;
		
		LinkedList<node_data> path=new LinkedList<>();
		for (int i = 0; i < tmp.graph.ver.size(); i++) {
			for (int j = 0; j < tmp.graph.ver.size(); j++) {
				if(tmp.graph.ver.get(i)!=null&&tmp.graph.ver.get(j)!=null&&i!=j) {
					if(tmp.shortestPathDist(i, j)<min) {
						if(tmp.shortestPath(i, j).size()==targets.size()) {
							min=shortestPathDist(i, j);
							path=new LinkedList<>(tmp.shortestPath(i, j));
						}
					}
				}
			}
		}
		return path;
	}

	@Override
	public graph copy() {
		DGraph temp=new DGraph();
		temp=this.graph.copy();
		return temp;
	}

	public static void main(String[] args) {
		Graph_Algo g=new Graph_Algo();
		Vertex v1=new Vertex(new Point3D(118,368,0));
		Vertex v2=new Vertex(new Point3D(50,150,0));
		Vertex v3=new Vertex(new Point3D(282,228,0));
		Vertex v4=new Vertex(new Point3D(222,285,0));
		Vertex v5=new Vertex(new Point3D(414,116,0));
		Vertex v6=new Vertex(new Point3D(58,136,0));


		g.init(new DGraph());

		System.out.println(g.isConnected());
		g.graph.addNode(v1);
		g.graph.addNode(v2);
		g.graph.addNode(v3);
		g.graph.addNode(v4);
		g.graph.connect(0, 1, 2);
		g.graph.connect(1, 2, 4);
		g.graph.connect(2, 3, 6);
		g.graph.connect(3, 0, 5);
		g.graph.connect(1, 3, 3);
		g.graph.connect(0, 2, 1);
		g.graph.connect(3, 2, 2);
		System.out.println(g.isConnected());
		

		//System.out.println(g.isConnected());

		LinkedList<Integer> s=new LinkedList<>();
		s.add(0);
		s.add(1);
		s.add(2);
		s.add(3);

		
		//System.out.println(g.TSP(s));


		System.out.println(g.TSP(s));
//		String path="C:\\Users\\Yogev\\Desktop\\.metadata\\.metadata\\ObjectOrientedEx3\\graph.txt";
//		g.save(path);
//		g.init(path);
		

	}
}