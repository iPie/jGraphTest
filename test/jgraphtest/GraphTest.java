/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jgraphtest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;

/**
 *
 * @author iPie
 */
public class GraphTest {

    public GraphTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of topologicalSort method, of class Graph.
     */
    @Test
    public void testTopologicalSort() {
        System.out.println("topologicalSort");
        Graph graph = new Graph();
        // Create verticies
        Vertex v1 = new Vertex(7);
        Vertex v2 = new Vertex(5);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(11);
        Vertex v5 = new Vertex(8);
        Vertex v6 = new Vertex(2);
        Vertex v7 = new Vertex(9);
        Vertex v8 = new Vertex(10);
        // Add verticies to graph
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        // Add edges between verticies
        graph.addEdge(v1, v4);
        graph.addEdge(v1, v5);
        graph.addEdge(v2, v4);
        graph.addEdge(v3, v5);
        graph.addEdge(v3, v8);
        graph.addEdge(v4, v6);
        graph.addEdge(v4, v7);
        graph.addEdge(v4, v8);
        graph.addEdge(v5, v7);
        List<String> expResults = new ArrayList<>();
        expResults.add("7, 5, 3, 11, 8, 2, 9, 10");
        expResults.add("3, 5, 7, 8, 11, 2, 9, 10");
        expResults.add("3, 7, 8, 5, 11, 10, 2, 9");
        expResults.add("5, 7, 3, 8, 11, 10, 9, 2");
        expResults.add("7, 5, 11, 3, 10, 8, 9, 2");
        expResults.add("7, 5, 11, 2, 3, 8, 9, 10");
        String result = graph.topologicalSort();
        assertTrue("topologicalSort output doesn't match any of the correct values",
                expResults.contains(result));
    }

//    /**
//     * Test of addVertex method, of class Graph.
//     */
//    @Test
//    public void testAddVertex() {
//        System.out.println("addVertex");
//        Vertex v = null;
//        Graph instance = new Graph();
//        instance.addVertex(v);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }    

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString000() {
        System.out.println("toString000");
        Graph graph = new Graph();
        // Create verticies
        Vertex v1 = new Vertex(4);
        Vertex v2 = new Vertex(1);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(5);
        // Add verticies to graph
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        // Add edges of cost 10 between verticies
        graph.addEdge(v1, v2, 10);
        graph.addEdge(v1, v3, 10);
        graph.addEdge(v2, v3, 10);
        graph.addEdge(v3, v4, 10);
        StringBuilder sb = new StringBuilder();
        sb.append("4 -> 1\n");
        sb.append("4 -> 3\n");
        sb.append("1 -> 3\n");
        sb.append("3 -> 5\n");
        String expResult = sb.toString();
        String result = graph.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString001() {
        System.out.println("toString001");
        Graph graph = new Graph();
        // Create verticies
        Vertex v1 = new Vertex(4);
        Vertex v2 = new Vertex(1);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(5);
        // Add verticies to graph
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        // Add edges of cost 10 between verticies
        graph.addEdge(v1, v2, 10);
        graph.addEdge(v2, v3, 10);
        graph.addEdge(v3, v1, 10);
        graph.addEdge(v3, v4, 10);
        StringBuilder sb = new StringBuilder();
        sb.append("4 -> 1\n");
        sb.append("1 -> 3\n");
        sb.append("3 -> 4\n");
        sb.append("3 -> 5\n");
        String expResult = sb.toString();
        String result = graph.toString();
        assertEquals(expResult, result);
    }
}
