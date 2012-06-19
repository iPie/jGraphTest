package jgraphtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Directed graph implementation. Library is under construction.
 *
 * @version 0.2b
 * @author iPie
 */
public class Graph {

    public static final int WHITE = 1;
    public static final int GREY = 2;
    public static final int BLCK = 3;
    private List<Vertex> verticies;
    private List<Edge> edges;
    private Vertex rootVertex;

    public Graph() {
        verticies = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(Vertex v) {
        verticies.add(v);
    }

    @Deprecated
    public void removeVertex() {
    }

    public void addEdge(Vertex from, Vertex to, int cost) {
        Edge e = new Edge(from, to, cost);
        from.addOutgoingEdge(e);
        to.addIncomingEdge(e);
        edges.add(e);
    }

    public void addEdge(Vertex from, Vertex to) {
        addEdge(from, to, 0);
    }

    @Deprecated
    public void removeEdge() {
    }

    public void setRootVertex(Vertex root) {
        if (!verticies.contains(root)) {
            addVertex(root);
        }
        this.rootVertex = root;
    }

    public Vertex getRootVertex() {
        return this.rootVertex;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : verticies) {
            String v1 = v.getData().toString();
            List<Vertex> verticiesList = v.getOutgoingVerticies();
            for (Vertex vertex : verticiesList) {
                sb.append(v1);
                sb.append(" -> ");
                sb.append(vertex.getData().toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void depthFirstSearch(Vertex vertex, List<Vertex> output) {
        vertex.setVisited(true);
        for (Vertex v : verticies) {
            if (!v.isVisited()) {
                this.depthFirstSearch(v, output);
            }
        }
        output.add(vertex);
    }

    public String topologicalSort() {
        for (Vertex v : verticies) {
            v.setVisited(false);
        }
        List<Vertex> sortedVerticies = new ArrayList<>();

        for (Vertex vertex : verticies) {
            if (!vertex.isVisited()) {
                depthFirstSearch(vertex, sortedVerticies);
            }
        }
        Collections.reverse(sortedVerticies);
        StringBuilder sb = new StringBuilder();
        for (Vertex v : sortedVerticies) {
            sb.append(v.getData().toString());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    @Deprecated
    private void findCycles() {
    }
}

class Edge {

    private Vertex from;
    private Vertex to;
    private int cost;
    private boolean visited;

    public Edge(Vertex from, Vertex to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
        visited = false;
    }

    public Vertex getTo() {
        return to;
    }

    public Vertex getFrom() {
        return from;
    }

    public int getCost() {
        return cost;
    }

    public void setVisited(boolean value) {
        visited = value;
    }

    public boolean isVisited() {
        return visited;
    }
}

class Vertex<T> {

    private List<Edge> incomingEdges;
    private List<Edge> outgoingEdges;
    private T data;
    //private String color;
    private boolean visited;

    public Vertex(T data) {
        incomingEdges = new ArrayList<>();
        outgoingEdges = new ArrayList<>();
        this.data = data;
        visited = false;
    }

    public T getData() {
        return this.data;
    }

    public void addIncomingEdge(Edge edge) {
        incomingEdges.add(edge);
    }

    public void addOutgoingEdge(Edge edge) {
        outgoingEdges.add(edge);
    }

    public List<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public List<Vertex> getIncomingVerticies() {
        List<Vertex> incomingVerticies = new ArrayList<>();
        for (Edge e : incomingEdges) {
            incomingVerticies.add(e.getFrom());
        }
        return incomingVerticies;
    }

    public List<Vertex> getOutgoingVerticies() {
        List<Vertex> outgoingVerticies = new ArrayList<>();
        for (Edge e : outgoingEdges) {
            outgoingVerticies.add(e.getTo());
        }
        return outgoingVerticies;
    }

    public void setVisited(boolean value) {
        visited = value;
    }

    public boolean isVisited() {
        return visited;
    }
}
