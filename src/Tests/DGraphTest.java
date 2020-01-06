package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;

import org.junit.jupiter.api.BeforeEach;

public class DGraphTest {
	static int i=1;
	public DGraph g=new DGraph();


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
		g=addG();
	}

	@Test
	void testAddNode() {
		g.addNode(new Vertex(new Point3D(1,1,1)));
		assertEquals(6, g.nodeSize());
	}

	@Test
	void testAddEdge() {
		g.connect(1, 0, 4);
		assertEquals(8, g.edgeSize());
	}

	@Test
	void testgetV() {
		Collection<node_data> t=g.getV();
		ArrayList<node_data> d=(ArrayList<node_data>)t;
		for (int i = 0; i < t.size(); i++) {
			assertEquals(d.get(i).getKey(), g.ver.get(i).getKey());

		}
	}

	@Test
	void testGetNode() {
		Vertex temp=new Vertex(1,new Vertex(new Point3D(1,1,1)));
		assertEquals(g.getNode(1).getInfo(), temp.getInfo());
	}

	@Test
	void testGetEdge() {
		Edge temp=new Edge(1,2,4);
		assertEquals(g.getEdge(1, 2).getInfo(), temp.getInfo());
	}

	@Test
	void testConnect() {
		int curSize=g.edgeSize();
		g.connect(4, 2, 5.5);
		assertEquals(curSize+1, g.edgeSize());
	}

	@Test
	void testGetV() {
		ArrayList<Vertex> test=new ArrayList<>();
		for (int i = 0; i < g.ver.size(); i++) {
			if(g.ver.get(i)!=null) {
				Vertex v=g.ver.get(i).copy();
				test.add(v);
			}
		}
		ArrayList<node_data> comp=(ArrayList<node_data>)g.getV();
		for (int i = 0; i < comp.size(); i++) {
			assertEquals(test.get(i), (Vertex)comp.get(i));
		}
	}

	@Test
	void testgetE() {
		LinkedList<edge_data> test= (LinkedList<edge_data>) g.getE(0);
		int k=0;
		for (Edge e:g.edge.get(0).values()) {
			assertEquals(test.get(k), e);
			k++;
		}
	}
	
	@Test
	void  testRemoveNode() {
		int Vsize=g.nodeSize();
		int Esize=g.edgeSize();
		g.removeNode(0);
		assertEquals(Vsize, g.nodeSize()+1);
		assertEquals(Esize, g.edgeSize()+3);

	}
	
	@Test
	void testRemoveEdge() {
		int Esize=g.edgeSize();
		g.removeEdge(1, 2);
		assertEquals(Esize, g.edgeSize()+1);
	}
	
	@Test
	void testGetMC() {
		DGraph t=new DGraph();
		t.addNode(new Vertex(new Point3D(2,19,4)));
		t.addNode(new Vertex(new Point3D(-2,0,-10)));
		t.addNode(new Vertex(new Point3D(12,7,-4)));
		t.addNode(new Vertex(new Point3D(5,1,4)));
		t.connect(0, 2, 5);
		t.connect(3	,1, 5);
		t.connect(2, 3, 5);
		t.connect(1, 2, 5);
		assertEquals(8, t.getMC());

	}
	
	/**
	 * beside function that initialing a DGraph with 5 vertex and 7 edges 
	 * @return - DGraph object
	 */
	public static DGraph addG() {
		DGraph tmp=new DGraph();
		for (int i = 0; i < 5; i++) {
			tmp.addNode(new Vertex(new Point3D(i,i,i)));
		}
		tmp.connect(0, 1, 2);
		tmp.connect(1, 2, 4);
		tmp.connect(2, 3, 6);
		tmp.connect(3, 0, 5);
		tmp.connect(1, 3, 3);
		tmp.connect(0, 2, 1);
		tmp.connect(3, 2, 2);
		return tmp;
	}

}
