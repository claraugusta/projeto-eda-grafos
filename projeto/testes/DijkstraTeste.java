package testes;

import graphs.*;
import org.junit.jupiter.api.Test;
import shortestPath.Dijkstra;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class DijkstraTeste {

    @Test
    public void testDijkstraAdjMatrix() {
        int INF = Integer.MAX_VALUE;
        int[][] adjMatrix = new int[5][5];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(adjMatrix[i], INF);
        }
        adjMatrix[0][1] = 10;
        adjMatrix[0][2] = 3;
        adjMatrix[1][2] = 1;
        adjMatrix[1][3] = 2;
        adjMatrix[2][3] = 8;
        adjMatrix[3][4] = 7;

        AdjMatrix matrixGraph = new AdjMatrix(5, adjMatrix);
        for (int i = 0; i < 5; i++) {
            matrixGraph.addNode(i);
        }

        Map<Integer, Integer> expectedDistances = new HashMap<>();
        expectedDistances.put(0, 0);
        expectedDistances.put(1, 10);
        expectedDistances.put(2, 3);
        expectedDistances.put(3, 11);
        expectedDistances.put(4, 18);

        Map<Integer, Integer> result = Dijkstra.dijkstra(matrixGraph, 0);
        assertEquals(expectedDistances, result);
    }

    @Test
    public void testDijkstraAdjList() {
        AdjList adjList = new AdjList(5);
        adjList.addEdge(0, 1, 1);
        adjList.addEdge(0, 2, 1);
        adjList.addEdge(1, 2, 1);
        adjList.addEdge(1, 3, 1);
        adjList.addEdge(2, 3, 1);
        adjList.addEdge(3, 4, 1);

        Map<Integer, Integer> expectedDistances = new HashMap<>();
        expectedDistances.put(0, 0);
        expectedDistances.put(1, 1);
        expectedDistances.put(2, 1);
        expectedDistances.put(3, 2);
        expectedDistances.put(4, 3);

        Map<Integer, Integer> result = Dijkstra.dijkstra(adjList, 0);
        assertEquals(expectedDistances, result);
    }

    @Test
    public void testDisconnectedGraph() {
        int INF = Integer.MAX_VALUE;
        int[][] adjMatrix = new int[4][4];
        for (int i = 0; i < 4; i++) {
            Arrays.fill(adjMatrix[i], INF);
        }
        adjMatrix[0][1] = 4;
        adjMatrix[1][2] = 6;
        // Node 3 is disconnected

        AdjMatrix matrixGraph = new AdjMatrix(4, adjMatrix);
        for (int i = 0; i < 4; i++) {
            matrixGraph.addNode(i);
        }

        Map<Integer, Integer> expectedDistances = new HashMap<>();
        expectedDistances.put(0, 0);
        expectedDistances.put(1, 4);
        expectedDistances.put(2, 10);
        expectedDistances.put(3, INF);

        Map<Integer, Integer> result = Dijkstra.dijkstra(matrixGraph, 0);
        assertEquals(expectedDistances, result);
    }

    @Test
    public void testSingleNodeGraph() {
        int[][] adjMatrix = new int[1][1];
        adjMatrix[0][0] = 0;

        AdjMatrix matrixGraph = new AdjMatrix(1, adjMatrix);
        matrixGraph.addNode(0);

        Map<Integer, Integer> expectedDistances = new HashMap<>();
        expectedDistances.put(0, 0);

        Map<Integer, Integer> result = Dijkstra.dijkstra(matrixGraph, 0);
        assertEquals(expectedDistances, result);
    }

    @Test
    public void testNegativeWeights() {
        int INF = Integer.MAX_VALUE;
        int[][] adjMatrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(adjMatrix[i], INF);
        }
        adjMatrix[0][1] = 2;
        adjMatrix[1][2] = -5; // Peso negativo

        AdjMatrix matrixGraph = new AdjMatrix(3, adjMatrix);
        for (int i = 0; i < 3; i++) {
            matrixGraph.addNode(i);
        }

        // Esperamos que o algoritmo lance uma exceção
        assertThrows(IllegalArgumentException.class, () -> {
            Dijkstra.dijkstra(matrixGraph, 0);
        });
    }

    @Test
    public void testLargeGraph() {
        int INF = Integer.MAX_VALUE;
        int size = 100;
        int[][] adjMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(adjMatrix[i], INF);
        }
        for (int i = 0; i < size - 1; i++) {
            adjMatrix[i][i + 1] = 1;
        }

        AdjMatrix matrixGraph = new AdjMatrix(size, adjMatrix);
        for (int i = 0; i < size; i++) {
            matrixGraph.addNode(i);
        }

        Map<Integer, Integer> expectedDistances = new HashMap<>();
        for (int i = 0; i < size; i++) {
            expectedDistances.put(i, i);
        }

        Map<Integer, Integer> result = Dijkstra.dijkstra(matrixGraph, 0);
        assertEquals(expectedDistances, result);
    }

    @Test
    public void testGraphWithEqualWeights() {
        int INF = Integer.MAX_VALUE;
        int[][] adjMatrix = new int[4][4];
        for (int i = 0; i < 4; i++) {
            Arrays.fill(adjMatrix[i], INF);
        }
        adjMatrix[0][1] = 1;
        adjMatrix[0][2] = 1;
        adjMatrix[1][0] = 1;
        adjMatrix[1][2] = 1;
        adjMatrix[1][3] = 1;
        adjMatrix[2][0] = 1;
        adjMatrix[2][1] = 1;
        adjMatrix[2][3] = 1;
        adjMatrix[3][1] = 1;
        adjMatrix[3][2] = 1;

        AdjMatrix graph = new AdjMatrix(4, adjMatrix);
        for (int i = 0; i < 4; i++) {
            graph.addNode(i);
        }

        Map<Integer, Integer> expectedDistances = new HashMap<>();
        expectedDistances.put(0, 0);
        expectedDistances.put(1, 1);
        expectedDistances.put(2, 1);
        expectedDistances.put(3, 2);

        Map<Integer, Integer> result = Dijkstra.dijkstra(graph, 0);
        assertEquals(expectedDistances, result);
    }

}