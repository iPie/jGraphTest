package jgraphtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Directed graph implementation. Library is under construction.
 *
 * @version 0.1b
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

    public void consoleOutput() {
        StringBuilder strb = new StringBuilder();
        for (Vertex v : verticies) {
            String v1 = v.getData().toString();
            List<Edge> edgeList = v.getOutgoingEdges();
            for (Edge e : edgeList) {
                strb.append(v1 + " -> " + e.getTo().getData().toString() + "\n");
            }
        }
        System.out.println(strb);
    }

    @Deprecated
    private void visit() {
    }

    @Deprecated
    private void topologicalSort() {
    }

    @Deprecated
    private void findCycles() {
    }
}

class Edge {

    private Vertex from;
    private Vertex to;
    private int cost;

    public Edge(Vertex from, Vertex to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
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
}

class Vertex<T> {

    private List<Edge> incomingEdges;
    private List<Edge> outgoingEdges;
    private T data;

    public Vertex(T data) {
        incomingEdges = new ArrayList<>();
        outgoingEdges = new ArrayList<>();
        this.data = data;
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

    @Deprecated
    public void setColor() {
    }

    @Deprecated
    public void getColor() {
    }
}
