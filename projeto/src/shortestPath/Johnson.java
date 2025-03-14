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

        int[][] newMatrix = new int[V][V];
        for (int u = 0; u < V - 1; u++) {
            for (int v = 0; v < V - 1; v++) {
                newMatrix[u][v] = graph.getWeight(u, v);
            }
            newMatrix[u][s] = INF;
            newMatrix[s][u] = 0;
        }
        newMatrix[s][s] = 0;

        Graph expandedGraph = new AdjMatrix(V, newMatrix);
        System.out.println(expandedGraph.toString());
        int[] h;
        try {
            h = bellmanFord(expandedGraph, s, V);
            System.out.println(Arrays.toString(h));
        } catch (IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        // Passo 3: Recalcular os pesos das arestas
        int[][] alteredGraph = new int[V - 1][V - 1];
        for (int u = 0; u < V - 1; u++) {
            for (int v = 0; v < V - 1; v++) {
                int weight = graph.getWeight(u, v);
                if (weight != INF) {
                    alteredGraph[u][v] = weight + h[u] - h[v];
                } else {
                    alteredGraph[u][v] = INF;
                }
            }
        }

        // Passo 4: Executar Dijkstra a partir de cada vértice no grafo com pesos recalculados
        Graph altGraph = new AdjMatrix(V - 1, alteredGraph);
        for (int u = 0; u < V - 1; u++) {
            Map<Integer, Integer> dist = dijkstra(altGraph, u);

            // Passo 5: Ajustar as distâncias para os valores originais
            for (int v = 0; v < V - 1; v++) {
                if (dist.get(v) != null) {
                    dist.put(v, dist.get(v) - h[u] + h[v]);
                }
            }

            // Exibir as distâncias
            System.out.println("Distâncias a partir do vértice " + u + ":");
            for (int v = 0; v < V - 1; v++) {
                System.out.println("Para " + v + ": " + (dist.get(v) == INF ? "INF" : dist.get(v)));
            }
        }
    }


    public static void main(String[] args) {
        // Criando um grafo com 4 vértices
        Graph graph = new AdjMatrix(4, INF);

        Graph adj = new AdjMatrix(5, new int[][] {
                {0, 3, 8, INF, -4},  // Arestas do vértice 0
                {INF, 0, INF, 1, 7},  // Arestas do vértice 1
                {INF, 4, 0, INF, INF}, // Arestas do vértice 2
                {2, INF, -5, 0, INF},  // Arestas do vértice 3
                {INF, INF, INF, 6, 0}  // Arestas do vértice 4
        });

        Graph graph1 = new AdjMatrix(4, new int[][] {
                {0, -4, 2, INF},  // Arestas do vértice 0
                {INF, 0, 5, INF}, // Arestas do vértice 1
                {INF, INF, 0, -3}, // Arestas do vértice 2
                {INF, INF, INF, 0} // Arestas do vértice 3
        });

        Graph graph3 = new AdjMatrix(5, new int[][] {
                {0, 6, INF, 7, INF},  // Arestas do vértice 0
                {INF, 0, 5, 8, -4},   // Arestas do vértice 1
                {INF, -2, 0, INF, INF}, // Arestas do vértice 2
                {INF, INF, -3, 0, 9},  // Arestas do vértice 3
                {2, INF, INF, INF, 0}  // Arestas do vértice 4
        });

        // Instanciando o algoritmo de Johnson
        Johnson johnson = new Johnson();

        // Executando o algoritmo de Johnson no grafo
        johnson.johnson(graph3);
    }
}
