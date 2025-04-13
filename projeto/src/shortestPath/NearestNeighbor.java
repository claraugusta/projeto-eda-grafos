package shortestPath;

import graphs.AdjMatrix;
import graphs.Graph;

public class NearestNeighbor {
    private static final int INF = Integer.MAX_VALUE;

    public static int[] nearestNeighbor(Graph graph, int start) {
        if(graph == null) throw new NullPointerException();

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

            for (int i = 0; i < V; i++) {
                if (!visited[i] && graph.getWeight(currentNode, i) > 0 && graph.getWeight(currentNode, i) != INF) {
                    int distance = graph.getWeight(currentNode, i);
                    if (distance < minDistance) {
                        nearestNode = i;
                        minDistance = distance;
                    }
                }
            }

            if (nearestNode == -1) {
                throw new IllegalArgumentException();
            }

            currentNode = nearestNode;
            visited[currentNode] = true;
            tour[tourIndex++] = currentNode;
        }
        return tour;
    }
    }

