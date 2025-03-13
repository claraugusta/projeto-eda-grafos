package shortestPath;

import graphs.AdjList;
import graphs.AdjMatrix;
import graphs.Graph;

import java.util.Arrays;
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
        V--;
        System.out.println(Arrays.toString(h));
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
        for (int u = 0; u < V; u++) {
            Map<Integer, Integer> dist = dijkstra(altGraph, u);
            System.out.println(dist);
            for (int v = 0; v < V; v++) {
                if (dist.get(v) != null) {
                    dist.put(v, dist.get(v)-h[u]+h[v]);
                }
            }

            // Exibir as distâncias
            System.out.println("Distâncias a partir do vértice " + u + ":");
            for (int v = 0; v < V; v++) {
                System.out.println("Para " + v + ": " + dist.get(v));
            }
        }
    }


    public static void main(String[] args) {
        // Criando um grafo com 4 vértices
        Graph graph = new AdjMatrix(4, INF);

        // Adicionando arestas ao grafo com pesos
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addEdge(0, 1, 3);  // Aresta de 0 para 1 com peso 3
        graph.addEdge(0, 2, 8);  // Aresta de 0 para 2 com peso 8
        graph.addEdge(0, 3, 5);  // Aresta de 0 para 3 com peso 5
        graph.addEdge(1, 2, 2);  // Aresta de 1 para 2 com peso 2
        graph.addEdge(2, 3, 1);  // Aresta de 2 para 3 com peso 1

        Graph adj = new AdjMatrix(5, new int[][] {
                {0, 3, 8, INF, -4},  // Arestas do vértice 0
                {INF, 0, INF, 1, 7},  // Arestas do vértice 1
                {INF, 4, 0, INF, INF}, // Arestas do vértice 2
                {2, INF, -5, 0, INF},  // Arestas do vértice 3
                {INF, INF, INF, 6, 0}  // Arestas do vértice 4
        });

        // Instanciando o algoritmo de Johnson
        Johnson johnson = new Johnson();

        // Executando o algoritmo de Johnson no grafo
        johnson.johnson(adj);
    }
}
