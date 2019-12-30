package JunitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.Vertex;
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
		assertEquals(6, g.nodesSize);
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
