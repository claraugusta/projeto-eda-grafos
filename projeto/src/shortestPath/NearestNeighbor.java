package shortestPath;

import graphs.AdjMatrix;
import graphs.Graph;

public class NearestNeighbor {
    private static final int INF = Integer.MAX_VALUE;

    public static int[] nearestNeighbor(Graph graph, int start) {
        int V = graph.size();
        int[] tour = new int[V];
        boolean[] visited = new boolean[V];
        int currentNode = start;
        int tourIndex = 0;

        tour[tourIndex++] = currentNode;
        visited[currentNode] = true;

        while (tourIndex < V) {
            int nearestNode = -1;
            int minDistance = INF;

            System.out.println("No atual: " + currentNode);
            for (int i = 0; i < V; i++) {
                if (!visited[i] && graph.getWeight(currentNode, i) > 0 && graph.getWeight(currentNode, i) != INF) {
                    int distance = graph.getWeight(currentNode, i);
                    System.out.println("Vizinho: " + i + " com peso: " + distance);
                    if (distance < minDistance) {
                        nearestNode = i;
                        minDistance = distance;
                    }
                }
            }
            currentNode = nearestNode;
            visited[currentNode] = true;
            tour[tourIndex++] = currentNode;
        }
        return tour;
    }

    public static void main(String[] args) {

        Graph graph = new AdjMatrix(4, Integer.MAX_VALUE);
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        // graph.addNode(4);

        graph.addEdge(0, 1,10);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 0, 3);
        // graph.addEdge(2, 4, 5);
        // graph.addEdge(3, 4, 4);

        int start = 0;
        int[] tour = nearestNeighbor(graph, start);

        System.out.println("Caminho encontrado: ");
        for (int node : tour) {
            System.out.print(node + " ");
        }
    }
}
