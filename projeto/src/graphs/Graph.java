package graphs;

import java.util.List;

public interface Graph {

    boolean addNode(int Node);
    boolean addEdge(int u, int v, int weight);
    int getWeight(int u, int v);
    int numberOfNodes();
    int size();
    List<Integer> getAdj(int node);
    String toString();
}
