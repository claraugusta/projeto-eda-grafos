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

    public static void johnson(Graph graph) {
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
        int[] h;
        try {
            h = bellmanFord(expandedGraph, s, V);
        } catch (IllegalStateException e) {
            return;
        }

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

        Graph altGraph = new AdjMatrix(V - 1, alteredGraph);
        for (int u = 0; u < V - 1; u++) {
            Map<Integer, Integer> dist = dijkstra(altGraph, u);

            for (int v = 0; v < V - 1; v++) {
                if (dist.get(v) != null) {
                    dist.put(v, dist.get(v) - h[u] + h[v]);
                }
            }
        }
    }


    public static void main(String[] args) {
        // Criando um grafo com 4 vértices
        Graph graph = new AdjMatrix(4, INF);

        Graph adj = new AdjMatrix(5, new int[][] {
                {0, 3, 8, INF, -4},  // Arestas do vértice 0
                {INF, 0, INF, 1, 7},  // Arestas do vértice 1
                {INF, 4, 0, INF, INF}, // Arestas do vértice 2
                {2, INF, -5, 0, INF},  // Arestas do vértice 3
                {INF, INF, INF, 6, 0}  // Arestas do vértice 4
        });

        Graph graph1 = new AdjMatrix(4, new int[][] {
                {0, -4, 2, INF},  // Arestas do vértice 0
                {INF, 0, 5, INF}, // Arestas do vértice 1
                {INF, INF, 0, -3}, // Arestas do vértice 2
                {INF, INF, INF, 0} // Arestas do vértice 3
        });

        Graph graph3 = new AdjMatrix(5, new int[][] {
                {0, 6, INF, 7, INF},  // Arestas do vértice 0
                {INF, 0, 5, 8, -4},   // Arestas do vértice 1
                {INF, -2, 0, INF, INF}, // Arestas do vértice 2
                {INF, INF, -3, 0, 9},  // Arestas do vértice 3
                {2, INF, INF, INF, 0}  // Arestas do vértice 4
        });

        Johnson johnson = new Johnson();

        johnson.johnson(graph3);
    }
}
