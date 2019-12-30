package JunitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import dataStructure.Edge;

public class EdgeTest {

	static int i=1;
	Edge e=new Edge(1,2,5);
	
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
		assertEquals("Source:1\n" + 
				"Destination:2\n" + 
				"Weight:5.0" 
				, e.getInfo());
	}
	
	@Test
	void testSetInfo() {
		String tmp=e.getInfo();
		Edge g=new Edge(0, 0, 1);
		g.setInfo(tmp);
		assertEquals(e.getInfo(), g.getInfo());
	}
	
	@Test
	void testCopy() {
		Edge g=e.copy();
		assertEquals(e.getInfo(), g.getInfo());
	}
}
