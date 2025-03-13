package shortestPath;

import graphs.*;
import java.util.*;

public class Dijkstra {

    public static Map<Integer, Integer> dijkstra(Graph graph, int source) {
        int n = graph.size();
        Map<Integer, Integer> dist = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (int i = 0; i < n; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }
        dist.put(source, 0);
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int currentDist = curr[1];

            // Se a distância já for maior do que a distância mínima encontrada, ignoramos
            if (currentDist > dist.get(node)) continue;

            // Verificando os vizinhos
            for (int neighbor : graph.getAdj(node)) {
                int weight = graph.getWeight(node, neighbor);
                if (weight == Integer.MAX_VALUE) continue;
                int newDist = currentDist + Math.abs(weight);

                // Se encontramos um caminho mais curto, atualizamos a distância
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    pq.add(new int[]{neighbor, newDist});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        // Criando o grafo com a implementação de Lista de Adjacência ponderada
        AdjList adjList = new AdjList(5);
        adjList.addEdge(0, 1, 10);
        adjList.addEdge(0, 2, 3);
        adjList.addEdge(1, 2, 1);
        adjList.addEdge(1, 3, 2);
        adjList.addEdge(2, 3, 8);
        adjList.addEdge(3, 4, 7);

        System.out.println("Shortest Paths from Node 0 (AdjList):");
        Map<Integer, Integer> distAdjList = dijkstra(adjList, 0);
        System.out.println(distAdjList);

        // Criando o grafo com a implementação de Matriz de Adjacência ponderada
        int[][] adjMatrix = new int[5][5];
        adjMatrix[0][1] = 10;
        adjMatrix[0][2] = 3;
        adjMatrix[1][2] = 1;
        adjMatrix[1][3] = 2;
        adjMatrix[2][3] = 8;
        adjMatrix[3][4] = 7;

        Graph matrixGraph = new AdjMatrix(5, adjMatrix);

        System.out.println("Shortest Paths from Node 0 (AdjMatrix):");
        Map<Integer, Integer> distAdjMatrix = dijkstra(matrixGraph, 0);
        System.out.println(distAdjMatrix);
    }
}