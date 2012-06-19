package jgraphtest;

public class JGraphTest {

    public static void main(String[] args) {
        // Create graph
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
        // Add edges verticies
        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v1);
        graph.addEdge(v3, v4);
        System.out.print(graph.toString());
        
    }
}
