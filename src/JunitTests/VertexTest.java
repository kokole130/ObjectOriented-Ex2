package JunitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataStructure.Vertex;
import utils.Point3D;


public class VertexTest {
	static int i=1;
	Vertex v=new Vertex(new Point3D(2,2,2));
	
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
	}
	@Test
	void testGetInfo() {
		assertEquals("Vertex:0\n" + 
				"Weight:2.147483647E9\n" + 
				"Point:2.0,2.0,2.0" 
				, v.getInfo());
	}
	
	@Test
	void testSetInfo() {
		String tmp=v.getInfo();
		Vertex t=new Vertex(new Point3D(0,0,0));
		t.setInfo(tmp);
		assertEquals(t.getInfo(), v.getInfo());
	}
	@Test
	void testCopy() {
		Vertex tmp=v.copy();
		assertEquals(v.getInfo(), tmp.getInfo());
	}
}
