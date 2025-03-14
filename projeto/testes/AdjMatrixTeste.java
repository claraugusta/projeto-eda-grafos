import graphs.AdjMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AdjMatrixTeste {
    private AdjMatrix graph;
    private final int NULL_EDGE_VALUE = Integer.MAX_VALUE;

    @BeforeEach
    void setUp() {
        graph = new AdjMatrix(5, NULL_EDGE_VALUE);
    }

    @Test
    void testAddNode() {
        assertTrue(graph.addNode(1));
        assertTrue(graph.addNode(2));
        assertFalse(graph.isFull());
    }

    @Test
    void testAddNode_WhenFull() {
        for (int i = 0; i < 5; i++) {
            graph.addNode(i);
        }
        assertFalse(graph.addNode(6));
        assertTrue(graph.isFull());
    }

    @Test
    void testAddEdge() {
        graph.addNode(1);
        graph.addNode(2);
        assertTrue(graph.addEdge(1, 2, 10));
        assertEquals(10, graph.getWeight(1, 2));
        assertEquals(10, graph.getWeight(2, 1));
    }

    @Test
    void testAddEdge_InvalidNodes() {
        assertFalse(graph.addEdge(1, 2, 10));
    }

    @Test
    void testGetNodeIndex() {
        graph.addNode(1);
        graph.addNode(2);
        assertEquals(0, graph.getNodeIndex(1));
        assertEquals(1, graph.getNodeIndex(2));
        assertEquals(-1, graph.getNodeIndex(3));
    }

    @Test
    void testGetWeight() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 7);
        assertEquals(7, graph.getWeight(1, 2));
        assertEquals(7, graph.getWeight(2, 1));
        assertEquals(NULL_EDGE_VALUE, graph.getWeight(1, 3));
    }

    @Test
    void testGetDegree() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 15);
        assertEquals(2, graph.getDegree(1));
        assertEquals(1, graph.getDegree(2));
        assertEquals(1, graph.getDegree(3));
        assertEquals(-1, graph.getDegree(4));
    }

    @Test
    void testNumberOfNodes() {
        assertEquals(5, graph.numberOfNodes());
    }

    @Test
    void testMatrixToString() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2, 10);
        String matrixString = graph.MatrixToString();
        assertNotNull(matrixString);
        assertTrue(matrixString.contains("10"));
    }
}
