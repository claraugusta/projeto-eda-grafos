package graphs;
import java.util.*;

public class AdjListWeighted {

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

    public boolean addNode(int node) {
        if (node >= 0 && node < nodes) {
            return true;
        }
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

    public boolean hasEdge(int u, int v) {
        for (Edge edge : adjList.get(u)) {
            if (edge.nodeOut == v) {
                return true;
            }
        }
        return false;
    }

    public void removeEdge(int u, int v) {
        adjList.get(u).removeIf(edge -> edge.nodeOut == v);
    }

    public List<Edge> getAdj(int node) {
        return adjList.get(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes; i++) {
            sb.append("Node ").append(i).append(" -> ");
            for (Edge edge : adjList.get(i)) {
                sb.append("(").append(edge.nodeOut).append(", Peso: ").append(edge.weight).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
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
