package shortestPath;

import graphs.AdjList;
import graphs.AdjMatrix;
import graphs.Graph;
import java.util.Map;

import static shortestPath.BellmanFord.bellmanFord;
import static shortestPath.Dijkstra.dijkstra;

public class Johnson {

    private static final int INF = Integer.MAX_VALUE;

    public void johnson(Graph graph) {
        int V = graph.size();

        int s = V;
        V++;

        graph.addNode(V-1);
        for (int v = 0; v < V-1; v++)
            graph.addEdge(V-1, v, 0);


        int[] h = bellmanFord(graph, s, V);

        int[][] alteredGraph = new int[V][V];
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                int weight = graph.getWeight(u, v);
                if (weight != INF) {
                    alteredGraph[u][v] = weight + h[u] - h[v];
                } else {
                    alteredGraph[u][v] = INF;
                }
            }
        }
        Graph altGraph = new AdjMatrix(V, alteredGraph);
        for (int u = 0; u < V-1; u++) {
            Map<Integer, Integer> dist = dijkstra(altGraph, u);

            for (int v = 0; v < V-1; v++) {
                if (dist.get(v) != INF) {
                    dist.put(v, dist.get(v)-h[u]+h[v]);
                }
            }

            // Exibir as distâncias
            System.out.println("Distâncias a partir do vértice " + u + ":");
            for (int v = 0; v < V-1; v++) {
                System.out.println("Para " + v + ": " + dist.get(v));
            }
        }
    }


    public static void main(String[] args) {
        // Criando um grafo com 4 vértices
        Graph graph = new AdjMatrix(5, INF);

        // Adicionando arestas ao grafo com pesos
        graph.addEdge(0, 1, 3);  // Aresta de 0 para 1 com peso 3
        graph.addEdge(0, 2, 8);  // Aresta de 0 para 2 com peso 8
        graph.addEdge(0, 3, 5);  // Aresta de 0 para 3 com peso 5
        graph.addEdge(1, 2, 2);  // Aresta de 1 para 2 com peso 2
        graph.addEdge(2, 3, 1);  // Aresta de 2 para 3 com peso 1

        // Instanciando o algoritmo de Johnson
        Johnson johnson = new Johnson();

        // Executando o algoritmo de Johnson no grafo
        johnson.johnson(graph);
    }
}
