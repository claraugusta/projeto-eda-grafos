package shortestPath;

import graphs.AdjMatrix;
import graphs.Graph;
import java.util.Arrays;


public class BellmanFord {

    private final static int INF = Integer.MAX_VALUE;

    public static int[] bellmanFord(Graph graph, int src, int V) {

        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Relaxation of all the edges V times, not (V - 1) as we
        // need one additional relaxation to detect negative cycle
        for (int i = 0; i < V; i++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (graph.getWeight(u,v) != INF){
                        if (dist[u] != INF && dist[u] + graph.getWeight(u,v) < dist[v]) {
                            // If this is the Vth relaxation, then there is
                            // a negative cycle
                            if (i == V - 1)
                                return new int[]{-1};

                            // Update shortest distance to node v
                            dist[v] = dist[u] + graph.getWeight(u,v);
                        }
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Graph adj = new AdjMatrix(9, new int[][] {
                {INF, 4, INF, INF, INF, INF, INF, 8, INF},
                {4, INF, 8, INF, INF, INF, INF, 11, INF},
                {INF, 8, INF, 7, INF, INF, INF, INF, 2},
                {INF, INF, 7, INF, 9, 14, INF, INF, INF},
                {INF, INF, INF, 9, INF, 10, INF, INF, INF},
                {INF, INF, 4, 14, 10, INF, 2, INF, INF},
                {INF, INF, INF, INF, INF, 2, INF, 1, 6},
                {8, 11, INF, INF, INF, INF, 1, INF, 7},
                {INF, INF, 2, INF, INF, INF, 6, 7, INF}
        });
        for (int i = 0; i < adj.size(); i++) {
            adj.addNode(i);
        }

        int src = 0;
        System.out.println(adj.getWeight(0,1));
        int[] ans = bellmanFord(adj, src, adj.size());
        for (int dist : ans)
            System.out.print(dist + " ");
    }
}