import org.junit.Assert;
import org.junit.Test;
import graphs.AdjMatrix;
import graphs.Graph;
import shortestPath.Johnson;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static shortestPath.Johnson.johnson;

public class JohnsonTest {

    private static final int INF = Integer.MAX_VALUE;

    @Test
    public void testJohnsonWithPositiveEdges() {
        Graph graph = new AdjMatrix(4, new int[][] {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        });

        int[][] js = johnson(graph);
        int[][] result = {
                {0, 5, 8, 9},
                {INF, 0, 3, 4},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}
        };
        assertArrayEquals(js, result);

    }

    @Test
    public void testJohnsonWithNegativeEdges() {
        Graph graph = new AdjMatrix(4, new int[][] {
                {0, -1, 4, INF},
                {INF, 0, 3, 2},
                {INF, INF, 0, INF},
                {INF, INF, -5, 0}
        });

        int[][] js = johnson(graph);
        for(int[] i : js)
            System.out.println(Arrays.toString(i));
    }

    @Test
    public void testJohnsonWithNegativeCycle() {
        // Grafo com ciclo negativo
        Graph graph = new AdjMatrix(4, new int[][] {
                {0, 1, INF, INF},
                {INF, 0, -1, INF},
                {INF, INF, 0, -1},
                {-1, INF, INF, 0}
        });
        try {
            johnson(graph);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Ciclo negativo detectado");
        }
    }

    @Test
    public void testJohnsonWithDisconnectedGraph() {
        // Grafo desconectado
        Graph graph = new AdjMatrix(4, new int[][] {
                {0, INF, INF, INF},
                {INF, 0, INF, INF},
                {INF, INF, 0, INF},
                {INF, INF, INF, 0}
        });
        int[][] js = johnson(graph);
        int[][] result = new int[][] {
                {0, INF, INF, INF},
                {INF, 0, INF, INF},
                {INF, INF, 0, INF},
                {INF, INF, INF, 0}
        };
        assertArrayEquals(js, result);

    }

    @Test
    public void testJohnsonWithComplexGraph() {
        Graph graph = new AdjMatrix(5, new int[][] {
                {0, 6, INF, 7, INF},
                {INF, 0, 5, 8, -4},
                {INF, -2, 0, INF, INF},
                {INF, INF, -3, 0, 9},
                {2, INF, INF, INF, 0}
        });

        int[][] js = johnson(graph);

        int[][] result = {
                {0, 2, 4, 7, -2},
                {-2, 0, 2, 5, -4},
                {-4, -2, 0, 3, -6},
                {-7, -5, -3, 0, -9},
                {2, 4, 6, 9, 0}
        };
        assertArrayEquals(js, result);
    }
}