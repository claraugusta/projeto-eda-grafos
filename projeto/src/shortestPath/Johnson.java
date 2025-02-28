//package shortestPath;
//
//import graphs.AdjMatrix;
//import graphs.Graph;
//
//import static shortestPath.BellmanFord.bellmanFord;
//
//public class Johnson {
//
//    private static final int INF = Integer.MAX_VALUE;
//
//    public void johnson(Graph graph) {
//        int V = graph.size();
//
//        int s = V; // Novo vértice
//        V++; // Aumenta o número de vértices
//
//        int[] h = bellmanFord(graph, s, s);
//
//        // Passo 3: Reponderar as arestas
//        int[][] alteredGraph = new int[V][V];
//        for (int u = 0; u < V; u++) {
//            for (int v = 0; v < V; v++) {
//                int weight = graph.getWeight(u, v);
//                if (weight != INF) {
//                    alteredGraph[u][v] = weight + h[u] - h[v];
//                } else {
//                    alteredGraph[u][v] = INF;
//                }
//            }
//        }
//        Graph altGraph = new AdjMatrix(V, alteredGraph);
//        for (int u = 0; u < V; u++) {
//            int[] dist = dijkstra(altGraph, u);
//
//            // Ajustar as distâncias para refletir os pesos originais
//            for (int v = 0; v < V; v++) {
//                if (dist[v] != INF) {
//                    dist[v] = dist[v] - h[u] + h[v];
//                }
//            }
//
//            // Exibir as distâncias
//            System.out.println("Distâncias a partir do vértice " + u + ":");
//            for (int v = 0; v < V; v++) {
//                System.out.println("Para " + v + ": " + dist[v]);
//            }
//        }
//    }
//}
