package graphs;

public interface Graph {

    boolean addNode(int Node);
    boolean addEdge(int u, int v, int weight);
    int getWeight(int u, int v);
    int getNodes();
    int size();

    String toString();
}
