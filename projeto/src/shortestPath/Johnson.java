package shortestPath;

import graphs.AdjList;
import graphs.AdjMatrix;
import graphs.Graph;

import java.util.Arrays;
import java.util.Map;

import static shortestPath.BellmanFord.bellmanFord;
import static shortestPath.Dijkstra.dijkstra;

public class Johnson {

    private static final int INF = Integer.MAX_VALUE;

    public static int[][] johnson(Graph graph) {
        int V = graph.size();
        int s = V;
        V++;

        int[][] newMatrix = new int[V][V];
        for (int u = 0; u < V - 1; u++) {
            for (int v = 0; v < V - 1; v++) {
                newMatrix[u][v] = graph.getWeight(u, v);
            }
            newMatrix[u][s] = INF;
            newMatrix[s][u] = 0;
        }
        newMatrix[s][s] = 0;

        Graph expandedGraph = new AdjMatrix(V, newMatrix);
        int[] h = bellmanFord(expandedGraph, s, V);

        int[][] alteredGraph = new int[V - 1][V - 1];
        for (int u = 0; u < V - 1; u++) {
            for (int v = 0; v < V - 1; v++) {
                int weight = graph.getWeight(u, v);
                if (weight != INF) {
                    alteredGraph[u][v] = weight + h[u] - h[v];
                } else {
                    alteredGraph[u][v] = INF;
                }
            }
        }

        int[][] distances = new int[V - 1][V - 1];
        Graph altGraph = new AdjMatrix(V - 1, alteredGraph);
        for (int u = 0; u < V - 1; u++) {
            Map<Integer, Integer> dist = dijkstra(altGraph, u);
            for (int v = 0; v < V - 1; v++) {
                if (dist.get(v) != null) {
                    distances[u][v] = dist.get(v) - h[u] + h[v];
                } else {
                    distances[u][v] = INF;
                }
            }
        }

        return distances;
    }
}