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

    public static int[] bellmanFord(AdjListWeighted graph, int src) {
        int V = graph.size();
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (AdjListWeighted.Edge edge : graph.getAdj(u)) {
                    if (dist[edge.nodeIn] != INF && dist[edge.nodeIn] + edge.weight < dist[edge.nodeOut]) {
                        dist[edge.nodeOut] = dist[edge.nodeIn] + edge.weight;
                    }
                }
            }

        }
        for (int i = 0; i < V; i++) {
            for (AdjListWeighted.Edge edge : graph.getAdj(i)) {
                if (dist[edge.nodeIn] != INF && dist[edge.nodeIn] + edge.weight < dist[edge.nodeOut]) {
                    throw new IllegalStateException("Ciclo negativo detectado");
                }
            }
        }
        return dist;
    }


    public static void main(String[] args) {
        AdjListWeighted graph = new AdjListWeighted(5);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        System.out.println(Arrays.toString(bellmanFord(graph, 0)));
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