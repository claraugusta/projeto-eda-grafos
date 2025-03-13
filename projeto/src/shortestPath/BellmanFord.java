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

        for (int i = 0; i < V; i++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (graph.getWeight(u,v) != INF){
                        if (dist[u] != INF && dist[u] + graph.getWeight(u,v) < dist[v]) {

                            if (i == V - 1)
                                return new int[]{-1};
                            dist[v] = dist[u] + graph.getWeight(u,v);
                        }
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Graph adj = new AdjMatrix(10, new int[][] {
                {INF, 4, INF, INF, INF, INF, INF, -8, INF, INF},
                {-4, INF, 8, INF, INF, INF, INF, 11, INF, INF},
                {INF, 8, INF, 7, INF, INF, INF, INF, 2, INF},
                {INF, INF, 7, INF, -9, 14, INF, INF, INF, INF},
                {INF, INF, INF, 9, INF, 10, INF, INF, INF, INF},
                {INF, INF, 4, 14, 10, INF, 2, INF, INF, INF},
                {INF, INF, INF, INF, INF, -2, INF, 1, 6, INF},
                {8, 11, INF, INF, INF, INF, 1, INF, 7, INF},
                {INF, INF, 2, INF, INF, INF, 6, 7, INF, -3},
                {INF, INF, INF, INF, INF, INF, INF, INF, -3, INF}
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