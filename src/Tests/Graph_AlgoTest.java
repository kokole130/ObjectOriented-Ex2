package Tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Vertex;
import dataStructure.node_data;
import utils.Point3D;

import org.junit.jupiter.api.BeforeEach;


public class Graph_AlgoTest {
	static int i=1;
	public Graph_Algo g=new Graph_Algo();

	@BeforeClass
	public static void bfrClass() {
		System.out.println("Functions_GUI Test");
	}

	@AfterClass
	public static void aftClass() {
		System.out.println("Finished Test");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Test number "+i);
		i++;
		g=addGA();
	}
	
	@Test
	void testCopy() {
		Graph_Algo copy=new Graph_Algo();
		copy.graph=(DGraph) g.copy();
		for (int i = 0; i < g.graph.ver.size(); i++) {
			assertEquals(copy.graph.ver.get(i), g.graph.ver.get(i));
		}
		for (int i = 0; i <g.graph.edge.size(); i++) {
			for(Edge e:g.graph.edge.get(i).values()) {
				int key=e.getDest();
				assertEquals(copy.graph.edge.get(i).get(key), e);
			}
		}
	}
	
	@Test
	void testisConnected() {
		assertTrue(g.isConnected());
		g.graph.removeEdge(3, 4);
		assertEquals(false, g.isConnected());
	}
	
	@Test
	void testShortestPathDouble() {
		g.graph.connect(0, 3, 6);
		g.graph.connect(4, 1, 5);
		g.graph.connect(5, 2, 4);
		g.graph.connect(2, 0, 7);

		assertEquals(12, g.shortestPathDist(1, 5));
		assertEquals(9, g.shortestPathDist(0, 4));
		assertEquals(10, g.shortestPathDist(4, 3));
		assertEquals(10, g.shortestPathDist(3, 2));

	}
	
	@Test
	void testShortestPathList() {
		g.graph.connect(0, 3, 6);
		g.graph.connect(4, 1, 5);
		g.graph.connect(5, 2, 4);
		g.graph.connect(2, 0, 7);
		List<node_data> test=g.shortestPath(3, 2);
		assertEquals(4, test.size());
		
		test=g.shortestPath(0, 4);
		assertEquals(3, test.size());
		
		test=g.shortestPath(4, 3);
		assertEquals(4, test.size());
	}
	
	@Test
	void testTSP() {
		Graph_Algo g=new Graph_Algo();
		Vertex v1=new Vertex(new Point3D(118,368,0));
		Vertex v2=new Vertex(new Point3D(50,150,0));
		Vertex v3=new Vertex(new Point3D(282,228,0));
		Vertex v4=new Vertex(new Point3D(222,285,0));
		Vertex v5=new Vertex(new Point3D(414,116,0));
		Vertex v6=new Vertex(new Point3D(58,136,0));


		g.init(new DGraph());

		g.graph.addNode(v1);
		g.graph.addNode(v2);
		g.graph.addNode(v3);
		g.graph.addNode(v4);
		g.graph.addNode(v5);
		g.graph.addNode(v6);
		g.graph.connect(0, 1, 4);
		g.graph.connect(1, 2, 2);
		g.graph.connect(1, 4, 6);
		g.graph.connect(1, 5, 5);
		g.graph.connect(2, 3, 3);
		g.graph.connect(2, 0, 5);
		g.graph.connect(3, 0, 4);
		g.graph.connect(3, 4, 5);
		g.graph.connect(4, 5, 2);
		g.graph.connect(5, 3, 1);
		g.graph.connect(5, 0, 3);
		
		LinkedList<Integer> s=new LinkedList<>();
		s.add(0);
		s.add(5);
		s.add(2);
		
		LinkedList<Integer> test=new LinkedList<>();
		test.add(5);
		test.add(0);
		test.add(1);
		test.add(2);
		
		LinkedList<node_data> ans=(LinkedList<node_data>) g.TSP(s);
		
		for (int i = 0; i < ans.size(); i++) {
			assertEquals((int)test.get(i), ans.get(i).getKey());
		}
		
	}
	
	/**
	 * beside function that initializing Graph_Algo with 6 vertex and 6 edges
	 * @return Graph_Algo object
	 */
	private Graph_Algo addGA() {
		Graph_Algo temp=new Graph_Algo();
		for (int i = 0; i < 6; i++) {
			temp.graph.addNode(new Vertex(new Point3D(i,i,i)));
		}
		for (int i = 0; i < 5; i++) {
			temp.graph.connect(i, i+1, (i+4)/2);
		}
		temp.graph.connect(5, 0, 7);
		return temp;
	}
}
