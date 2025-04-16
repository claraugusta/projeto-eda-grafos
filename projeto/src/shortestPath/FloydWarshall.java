package shortestPath;

import graphs.*;

public class FloydWarshall {
    static final int INF = Integer.MAX_VALUE;

    public static int[][] floydWarshall(Graph graph) {
        int V = graph.size();
        int[][] dist = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                int weight = graph.getWeight(i, j);
                if (i == j) {
                    dist[i][j] = 0;
                } else dist[i][j] = weight;
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        for (int i = 0; i < dist.length; i++) {
            if (dist[i][i] < 0) {
                throw new IllegalArgumentException();
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        Graph graph = new DirectedAdjMatrix(4, INF);
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        //graph.addNode(4);


//        graph.addEdge(0, 1, 6);
//        graph.addEdge(1, 2, -1);
//        graph.addEdge(2, 3, 5);
        //graph.addEdge(3, 0, 1);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, -2);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 5);


        int[][] dist = floydWarshall(graph);
        

            // Exibindo a matriz de distâncias mínimas
            System.out.println("Matriz de Menores Distâncias:");
            for (int[] ints : dist) {
                for (int anInt : ints) {
                System.out.print((anInt == INF ? "INF" : anInt) + "\t");
            }
            System.out.println();
        }
        }
    }
