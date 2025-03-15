import graphs.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static shortestPath.BellmanFord.bellmanFord;

public class testBellmanFord {

    private AdjListWeighted adjListNegativeEdges;
    private AdjListWeighted adjListPositiveEdges;
    private Graph adjMatrixNegativeEdges;
    private Graph adjMatrixPositiveEdges;
    private static final int INF = Integer.MAX_VALUE;

    // Grafos aleatórios gerados pelos métodos da classe GenerateGraphs
    // Os testes tem no máximo 5 nós para facilitar a visualização.
    @Before
    public void setUp(){
        this.adjMatrixPositiveEdges = new DirectedAdjMatrix(5, INF);
        int[][] matrix = {
                {INF, 5, INF, 16, 7},
                {INF, INF, INF, 9, 19},
                {3, INF, INF, INF, 16},
                {14, INF, 3, INF, 19},
                {INF, INF, INF, 15, INF}
        };
        for (int i = 0; i < 5; i++)
            adjMatrixPositiveEdges.addNode(i);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int weight = matrix[i][j];
                if (weight != INF)
                    adjMatrixPositiveEdges.addEdge(i, j, weight);
            }
        }

        this.adjMatrixNegativeEdges = new DirectedAdjMatrix(5, INF);
        int[][] matrix2 = {
                {0, -1, 4, INF, INF},
                {INF, 0, 3, 2, 2},
                {INF, INF, 0, INF, INF},
                {INF, INF, INF, 0, INF},
                {INF, INF, INF, -1, 0},
        };
        for (int k = 0; k < 5; k++)
            adjMatrixNegativeEdges.addNode(k);
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                int weight = matrix2[i][j];
                if (weight != INF)
                    adjMatrixNegativeEdges.addEdge(i, j, weight);
            }
        }


        this.adjListNegativeEdges = new AdjListWeighted(5);
        adjListNegativeEdges.addEdge(0, 2, 9);
        adjListNegativeEdges.addEdge(0, 4, 7);
        adjListNegativeEdges.addEdge(1, 0, 2);
        adjListNegativeEdges.addEdge(1, 3, 6);
        adjListNegativeEdges.addEdge(2, 0, 8);
        adjListNegativeEdges.addEdge(2, 1, -5);
        adjListNegativeEdges.addEdge(2, 3, 9);
        adjListNegativeEdges.addEdge(2, 4, 6);
        adjListNegativeEdges.addEdge(3, 1, -3);
        adjListNegativeEdges.addEdge(4, 0, 8);
        adjListNegativeEdges.addEdge(4, 1, 6);

        this.adjListPositiveEdges = new AdjListWeighted(5);
        adjListPositiveEdges.addEdge(0, 1, 11);
        adjListPositiveEdges.addEdge(0, 2, 10);
        adjListPositiveEdges.addEdge(0, 3, 19);
        adjListPositiveEdges.addEdge(0, 4, 9);
        adjListPositiveEdges.addEdge(1, 0, 18);
        adjListPositiveEdges.addEdge(1, 2, 16);
        adjListPositiveEdges.addEdge(1, 3, 3);
        adjListPositiveEdges.addEdge(1, 4, 15);
        adjListPositiveEdges.addEdge(2, 0, 4);
        adjListPositiveEdges.addEdge(2, 1, 8);
        adjListPositiveEdges.addEdge(3, 0, 18);
        adjListPositiveEdges.addEdge(3, 4, 13);
        adjListPositiveEdges.addEdge(4, 0, 5);
        adjListPositiveEdges.addEdge(4, 2, 18);


    }

    // testes confirmados manualmente através da visualização de desenhos
    @Test
    public void test(){
        int[] bf = bellmanFord(adjMatrixNegativeEdges, 0, 5);
        assertTrue(Arrays.equals(new int[]{0, -1, 2, 0, 1}, bf));

        int[] bf1 = bellmanFord(adjListPositiveEdges, 0);
        assertTrue(Arrays.equals(new int[]{0, 11, 10, 14, 9}, bf1));

        int[] bf2 = bellmanFord(adjMatrixPositiveEdges, 0, adjMatrixPositiveEdges.size());
        assertTrue(Arrays.equals(new int[]{0, 5, 17, 14, 7}, bf2));

        int[] bf3 = bellmanFord(adjListNegativeEdges, 0);
        assertTrue(Arrays.equals(new int[]{0, 4, 9, 10, 7}, bf3));
    }
}
