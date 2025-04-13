import graphs.*;
import org.junit.jupiter.api.Test;
import shortestPath.NearestNeighbor;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


class NearestNeighborTest {

    @Test
    public void testNearestNeighborAdjMatrix() {
        AdjMatrix graph = new AdjMatrix(5, Integer.MAX_VALUE);
        for (int i = 0; i < 5; i++) {
            graph.addNode(i);
        }

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 8);
        graph.addEdge(3, 4, 7);


        int[] expectedPath = {0, 2, 1, 3, 4};
        int[] result = NearestNeighbor.nearestNeighbor(graph, 0);
        assertArrayEquals(expectedPath, result);
    }

    @Test
    public void testDisconnectedGraph() {

        AdjMatrix graph = new AdjMatrix(4, Integer.MAX_VALUE);
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);

        graph.addEdge(0,1,4);
        graph.addEdge(1, 2, 6);

        assertThrows(IllegalArgumentException.class, () -> {
            NearestNeighbor.nearestNeighbor(graph, 0);
        });
    }

    @Test
    public void testSingleNodeGraph() {
        AdjMatrix graph = new AdjMatrix(1, Integer.MAX_VALUE);
        graph.addNode(0);
        //graph.addEdge(0,0,2);

        int[] expectedPath = {0};
        int[] result = NearestNeighbor.nearestNeighbor(graph, 0);
        assertArrayEquals(expectedPath, result);
    }

    @Test
    public void testGraphWithEqualWeight() {
        AdjMatrix graph = new AdjMatrix(4, Integer.MAX_VALUE);
        for (int i = 0; i < 4; i++) {
            graph.addNode(i);
        }
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 1);

        int[] expectedPath = {0, 1, 2, 3};
        int[] result = NearestNeighbor.nearestNeighbor(graph, 0);
        assertArrayEquals(expectedPath, result);
    }

    @Test
    public void testLargeGraphs() {
        AdjMatrix graph = new AdjMatrix(100, Integer.MAX_VALUE);
        for (int i = 0; i < 100; i++) {
            graph.addNode(i);
        }
        for (int i = 0; i < 100 - 1; i++) {
            graph.addEdge(i,i + 1,1);
        }

        int[] path = NearestNeighbor.nearestNeighbor(graph, 0);
        assertEquals(graph.size(), path.length);
        }

}


