package shortestPath;

import graphs.AdjMatrix;
import graphs.Graph;
import java.util.Arrays;


public class BellmanFord {

    private final static int INF = Integer.MAX_VALUE;

    public static int[] bellmanFord(Graph graph, int src, int V) {

        int[] distances = new int[V];
        Arrays.fill(distances, INF);
        distances[src] = 0;

        boolean changed;
        for (int i = 0; i < V - 1; i++) {
            changed = false;
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    int weight = graph.getWeight(u, v);
                    if (weight != INF && distances[u] != INF) {
                        int newDist = distances[u] + weight;
                        if (newDist < distances[v]) {
                            distances[v] = newDist;
                            changed = true;
                        }
                    }
                }
            }
            if (!changed) break;
        }

        // Verificação de ciclos negativos
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                int weight = graph.getWeight(u, v);
                if (weight != INF && distances[u] != INF && distances[u] + weight < distances[v]) {
                    throw new IllegalStateException("Ciclo negativo detectado");
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Graph adj = new AdjMatrix(5, new int[][]  {
                {0, -5, 2, 3},
                {0, 0, 4, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        });

        Graph graph1 = new AdjMatrix(4, new int[][] {
                {0, 4, 2, INF},  // Arestas do vértice 0
                {INF, 0, 5, INF}, // Arestas do vértice 1
                {INF, INF, 0, 3}, // Arestas do vértice 2
                {INF, INF, INF, 0} // Arestas do vértice 3
        });


        Graph graph2 = new AdjMatrix(4, new int[][] {
                {0, 1, INF, INF},  // Arestas do vértice 0
                {INF, 0, -1, INF},  // Arestas do vértice 1
                {INF, INF, 0, -2},  // Arestas do vértice 2
                {1, INF, INF, 0}    // Arestas do vértice 3
        });

        Graph graph3 = new AdjMatrix(5, new int[][] {
                {0, 6, INF, 7, INF},  // Arestas do vértice 0
                {INF, 0, 5, 8, -4},   // Arestas do vértice 1
                {INF, -2, 0, INF, INF}, // Arestas do vértice 2
                {INF, INF, -3, 0, 9},  // Arestas do vértice 3
                {2, INF, INF, INF, 0}  // Arestas do vértice 4
        });
        int src = 0;
        int[] ans = bellmanFord(graph3, src, graph3.size());
        for (int dist : ans)
            System.out.print(dist + " ");
    }
}