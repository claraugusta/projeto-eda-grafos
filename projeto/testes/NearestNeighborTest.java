import graphs.*;
import org.junit.jupiter.api.Test;
import shortestPath.NearestNeighbor;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


class NearestNeighborTest {

//    @Test
//    public void testNearestNeighborAdjMatrix() {
//        int INF = Integer.MAX_VALUE;
//        int[][] adjMatrix = new int[5][5];
//        for (int i = 0; i < 5; i++) {
//            Arrays.fill(adjMatrix[i], INF);
//        }
//        adjMatrix[0][1] = 10;
//        adjMatrix[0][2] = 3;
//        adjMatrix[1][2] = 1;
//        adjMatrix[1][3] = 2;
//        adjMatrix[2][3] = 8;
//        adjMatrix[3][4] = 7;
//
//        AdjMatrix matrixGraph = new AdjMatrix(5, adjMatrix);
//        for (int i = 0; i < 5; i++) {
//            matrixGraph.addNode(i);
//        }
//
//        int[] expectedPath = {0, 2, 1, 3, 4};
//        int[] result = NearestNeighbor.nearestNeighbor(matrixGraph, 0);
//        assertArrayEquals(expectedPath, result);
//    }

    @Test
    public void testDisconnectedGraph() {
        int INF = Integer.MAX_VALUE;
        int[][] adjMatrix = new int[4][4];
        for (int i = 0; i < 4; i++) {
            Arrays.fill(adjMatrix[i], INF);
        }
        adjMatrix[0][1] = 4;
        adjMatrix[1][2] = 6;
        // O nó 3 está desconectado

        AdjMatrix matrixGraph = new AdjMatrix(4, adjMatrix);
        for (int i = 0; i < 4; i++) {
            matrixGraph.addNode(i);
        }

        try {
            NearestNeighbor.nearestNeighbor(matrixGraph, 0);
        } catch (IllegalArgumentException e) {
            fail("Expected no exception, but got: " + e.getMessage());
        }

    }

    @Test
    public void testSingleNodeGraph() {
        int[][] adjMatrix = new int[1][1];
        adjMatrix[0][0] = 0;

        AdjMatrix matrixGraph = new AdjMatrix(1, adjMatrix);
        matrixGraph.addNode(0);

        int[] expectedPath = {0};
        int[] result = NearestNeighbor.nearestNeighbor(matrixGraph, 0);
        assertArrayEquals(expectedPath, result);
    }

    @Test
    public void testGraphWithEqualWeight() {
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

        int[] expectedPath = {0, 1, 2, 3};
        int[] result = NearestNeighbor.nearestNeighbor(graph, 0);
        assertArrayEquals(expectedPath, result);
    }

    @Test
    public void testLargeGraphs() {
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

        int[] expectedPath = new int[size];
        for (int i = 0; i < size; i++) {
            expectedPath[i] = i;
        }

        int[] result = NearestNeighbor.nearestNeighbor(matrixGraph, 0);
        assertArrayEquals(expectedPath, result);
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

            int[] expectedPath = {0, 1, 2, 3};
            int[] result = NearestNeighbor.nearestNeighbor(graph, 0);
            assertEquals(expectedPath, result);
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

        int[] expectedPath = new int[size];
        for (int i = 0; i < size; i++) {
            expectedPath[i] = i;
        }

        int[] result = NearestNeighbor.nearestNeighbor(matrixGraph, 0);
        assertArrayEquals(expectedPath, result);
    }

}


