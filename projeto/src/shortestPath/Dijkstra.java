package shortestPath;

import graphs.*;
import java.util.*;

public class Dijkstra {

    private static final int INF = Integer.MAX_VALUE;

    public static Map<Integer, Integer> dijkstra(Graph graph, int source) {
        int n = graph.size();
        Map<Integer, Integer> dist = new HashMap<>(); 
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); 

        // Inicializa as distâncias e a fila de prioridade
        for (int i = 0; i < n; i++) {
            dist.put(i, INF);
        }
        dist.put(source, 0);
        pq.add(new int[]{source, 0}); 

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int currentDist = curr[1];

            if (currentDist > dist.get(node)) continue;

            // VerificA os vizinhos
            for (int neighbor : graph.getAdj(node)) {
                int weight = graph.getWeight(node, neighbor);
                if (weight == INF) continue;
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

        // AdjList
//        AdjList adjList = new AdjList(5);
//        adjList.addEdge(0, 1, 10);
//        adjList.addEdge(0, 2, 3);
//        adjList.addEdge(1, 2, 1);
//        adjList.addEdge(1, 3, 2);
//        adjList.addEdge(2, 3, 8);
//        adjList.addEdge(3, 4, 7);

//        System.out.println("Shortest Paths from Node 0 (AdjList):");
//        Map<Integer, Integer> distAdjList = dijkstra(adjList, 0);
//        System.out.println(distAdjList);

        // AdjMatrix
        int[][] adjMatrix = new int[5][5];
        for (int i = 0; i < 5; i++){
            Arrays.fill(adjMatrix[i], INF);
        }
        adjMatrix[0][1] = 10;
        adjMatrix[0][2] = 3;
        adjMatrix[1][2] = 1;
        adjMatrix[1][3] = 2;
        adjMatrix[2][3] = 8;
        adjMatrix[3][4] = 7;

        // adiciona os nós ao adjMatrix
        AdjMatrix matrixGraph = new AdjMatrix(5, adjMatrix);
        for(int i = 0; i < 5; i++){
            matrixGraph.addNode(i);
        }

        System.out.println("Shortest Paths from Node 0 (AdjMatrix):");
        Map<Integer, Integer> distAdjMatrix = dijkstra(matrixGraph, 0);
        System.out.println(distAdjMatrix);
    }
}
