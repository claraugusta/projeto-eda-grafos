package graphs;
import java.util.*;

public class AdjListWeighted implements Graph{

    private int nodes;
    private List<List<Edge>> adjList;

    public AdjListWeighted(int nodes) {
        this.nodes = nodes;
        adjList = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public int size() {
        return this.nodes;
    }

    @Override
    public boolean addNode(int Node) {
        return false;
    }

    public boolean addEdge(int u, int v, int weight) {
        if (u >= 0 && u < nodes && v >= 0 && v < nodes) {
            adjList.get(u).add(new Edge(u, v, weight));
            return true;
        }
        return false;
    }

    public int getWeight(int u, int v) {
        for (Edge edge : adjList.get(u)) {
            if (edge.nodeOut == v) {
                return edge.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public int numberOfNodes() {
        return 0;
    }

    public List<Edge> getEdges(int node) {
        return adjList.get(node);
    }

    @Override
    public List<Integer> getAdj(int node) {
        List<Integer> pesos = new ArrayList<>();
        if (node >= 0 && node < nodes) {
            for (Edge edge : adjList.get(node)) {
                pesos.add(edge.weight);
            }
        }
        return pesos;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < nodes; i++) {
            out += "de " + i + ":\n";
            for(Edge e : getEdges(i)){
                out += "para " + e.nodeOut + ": " + e.weight+ "\n";
            }
        }
        return out;
    }

    public class Edge {
        public int nodeIn;
        public int nodeOut;
        public int weight;

        public Edge(int nodeIn, int nodeOut, int weight) {
            this.nodeIn = nodeIn;
            this.nodeOut = nodeOut;
            this.weight = weight;
        }
    }
}
