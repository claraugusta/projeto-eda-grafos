import graphs.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static shortestPath.FloydWarshall.floydWarshall;

import java.util.*;
class FloydWarshallTest {

    private static final int INF = Integer.MAX_VALUE;

    @Test
    public void testPositiveEdgeGraph() {
       AdjMatrix graph = new AdjMatrix(4, new int[][] {
               {0, 5, INF, 10},
               {INF, 0, 3, INF},
               {INF, INF, 0, 1},
               {INF, INF, INF, 0}
       });

        int[][] expected = {
                {0, 5, 8, 9},
                {INF, 0, 3, 4},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        };

        int[][] result = floydWarshall(graph);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testNegativeCicle(){
        Graph graph = new AdjMatrix(4, new int[][] {
                {0, -1, 4, INF},
                {INF, 0, 3, 2},
                {INF, INF, 0, INF},
                {INF, INF, -5, 0}
        });

        int[][] result = floydWarshall(graph);
        for(int[] i : result)
            System.out.println(Arrays.toString(i));
    }

    @Test
    public void testWithPositiveWeights() {
        Graph graph = new AdjMatrix(4, new int[][] {
                {0, 3, INF, 7},
                {8, 0, 2, INF},
                {5, INF, 0, 1},
                {2, INF, INF, 0}
        });

        int[][] expected = {
                {0, 3, 5, 6},
                {5, 0, 2, 3},
                {3, 6, 0, 1},
                {2, 5, 7, 0}
        };

        int[][] result = floydWarshall(graph);
        assertArrayEquals(expected, result);
    }

    //Rever
    @Test
    public void testWithNegativeEdges() {
        int INF = Integer.MAX_VALUE;
        Graph graph = new DirectedAdjMatrix(4, INF);
        for (int i = 0; i < 4; i++) {
            graph.addNode(i);
    }
            graph.addEdge(0, 1, 4);
            graph.addEdge(0, 2, 1);
            graph.addEdge(2, 1, -2);
            graph.addEdge(1, 3, 2);
            graph.addEdge(2, 3, 5);

        int[][] expected = {
                {0, -1, 1, 1},
                {INF, 0, INF, 2},
                {INF, -2, 0, 0},
                {INF, INF, INF, 0}
        };


        int[][] result = floydWarshall(graph);
            assertArrayEquals(expected, result);
        }

    @Test
    public void testWithDisconnectedGraph() {
        Graph graph = new AdjMatrix(3, new int[][] {
                {0, INF, INF},
                {INF, 0, INF},
                {INF, INF, 0}
        });

        int[][] expected = {
                {0, INF, INF},
                {INF, 0, INF},
                {INF, INF, 0}
        };

        int[][] result = floydWarshall(graph);
        assertArrayEquals(expected, result);
    }

}
