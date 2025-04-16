package shortestPath;

import graphs.AdjListWeighted;
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
        Graph graph = new AdjListWeighted(5);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        System.out.println(Arrays.toString(bellmanFord(graph, 0, graph.size())));
        int[][] graph1 = {
                {  0,  -1,   4,  INF, INF },
                { INF,   0,   3,   2,   2 },
                { INF, INF,   0, INF, INF },
                { INF,   1,   5,   0, INF },
                { INF, INF, INF,  -3,   0 }
        };
        Graph adjmtx = new AdjMatrix(5, graph1);
        int[] ans = bellmanFord(adjmtx, 0, adjmtx.size());
        System.out.println(Arrays.toString(ans));
    }
}