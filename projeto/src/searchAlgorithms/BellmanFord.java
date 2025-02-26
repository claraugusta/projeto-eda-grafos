package searchAlgorithms;

import graphs.AdjMatrix;

import java.util.Arrays;

public class BellmanFord {

    private final static int INF = Integer.MAX_VALUE;

    public static int[] bellmanFord(AdjMatrix adjM, int src) {
        // Initially distance from source to all other vertices
        // is not known(Infinite).
        int[][] adj = adjM.getAdjacencyMatrix();
        int V = adj.length;
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Relaxation of all the edges V times, not (V - 1) as we
        // need one additional relaxation to detect negative cycle
        for (int i = 0; i < V; i++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (adj[u][v] != INF){
                        if (dist[u] != INF && dist[u] + adj[u][v] < dist[v]) {
                            // If this is the Vth relaxation, then there is
                            // a negative cycle
                            if (i == V - 1)
                                return new int[]{-1};

                            // Update shortest distance to node v
                            dist[v] = dist[u] + adj[u][v];
                        }
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        AdjMatrix adj = new AdjMatrix(9, new int[][] {
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

        int src = 0;
        int[] ans = bellmanFord(adj, src);
        for (int dist : ans)
            System.out.print(dist + " ");
    }
}