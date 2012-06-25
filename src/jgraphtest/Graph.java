package jgraphtest;

import java.util.*;

/**
 * Directed graph implementation. Library is under construction.
 *
 * @version 0.4b
 * @author iPie
 */
public class Graph {

    private List<Vertex> verticies;
    private List<Edge> edges;
    private Vertex rootVertex;

    public Graph() {
        verticies = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(Vertex v) {
        if (verticies.isEmpty()) {
            rootVertex = v;
        }
        verticies.add(v);
    }

    public void addVerticies(Vertex... verticies) {
        if (this.verticies.isEmpty()) {
            rootVertex = verticies[0];
        }
        this.verticies.addAll(Arrays.asList(verticies));
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

    /**
     * Returns a string representation of the directed Graph. String contains
     * pairs of verticies with a '\n' char after each pair.
     *
     * <br><br><b>Example:</b>
     *
     * <br><br>D = ({1, 3, 4, 5}, {(4, 1), (4, 3), (1, 3), (3, 5)})
     *
     * <br><br>will be represented as:<br>
     *
     * <br>4 -> 1 <br>4 -> 3 <br>1 -> 3 <br>3 -> 5
     *
     * @return a string representation of the Graph.
     */
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

    @Deprecated
    public void depthFirstSearch(Vertex vertex, List<Vertex> output) {
        vertex.setVisited(true);
        List<Vertex> adjacentList = vertex.getOutgoingVerticies();
        for (Vertex v : adjacentList) {
            if (!v.isVisited()) {
                this.depthFirstSearch(v, output);
            }
        }
        output.add(vertex);
    }

    public String topologicalSort() {
        Stack<Vertex> startVerticies = new Stack<>();
        List<Vertex> sortedGraph = new ArrayList<>();
        for (Vertex v : verticies) {
            v.setVisited(false);
            if (v.getIncomingEdges() == null) {
                startVerticies.add(v);
            }
        }
        for (Edge e : edges) {
            e.setVisited(false);
        }
        while (!startVerticies.isEmpty()) {
            Vertex v = startVerticies.pop();
            sortedGraph.add(v);
            List<Edge> outgoingEdges = v.getOutgoingEdges();
            if (outgoingEdges != null) {
                for (Edge e : outgoingEdges) {
                    e.setVisited(true);
                    if (!hasUnvisitedIncomingEdges(e.getTo())) {
                        startVerticies.push(e.getTo());
                    }
                }
            }
        }
        boolean hasCycles = false;
        for (Edge e : edges) {
            if (!e.isVisited()) {
                hasCycles = true;
                break;
            }
        }
        if (!hasCycles) {
            return verticiesToString(sortedGraph);
        } else {
            throw new UnsupportedOperationException("graph contains cycles");
        }
    }

    private String verticiesToString(List<Vertex> vertexList) {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertexList) {
            sb.append(v.getData().toString());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    private boolean hasUnvisitedIncomingEdges(Vertex vertex) {
        List<Edge> vertexIncomingEdges = vertex.getIncomingEdges();
        for (Edge edge : vertexIncomingEdges) {
            if (edge.isVisited() == false) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycles() {
        try {
            String str = this.topologicalSort();
            return false;
        } catch (Exception e) {
            return true;
        }
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
        if (!incomingEdges.isEmpty()) {
            return incomingEdges;
        } else {
            return null;
        }
    }

    public List<Edge> getOutgoingEdges() {
        if (!outgoingEdges.isEmpty()) {
            return outgoingEdges;
        } else {
            return null;
        }
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
