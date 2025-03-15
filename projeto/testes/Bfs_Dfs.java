import graphs.AdjList;
import graphs.AdjMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import searchAlgorithms.Bfs;
import searchAlgorithms.Dfs;

import static org.junit.jupiter.api.Assertions.*;

public class Bfs_Dfs {
    private AdjMatrix graphMatrix;
    private AdjList graphList;
    private Bfs bfs;
    private Dfs dfs;
    private final int NULL_EDGE_VALUE = Integer.MAX_VALUE;

    @BeforeEach
    void setUp() {
        graphMatrix = new AdjMatrix(6, NULL_EDGE_VALUE);
        graphList = new AdjList(6);
        bfs = new Bfs();
        dfs = new Dfs();

        graphList.addEdge(0, 1);
        graphList.addEdge(0, 2);
        graphList.addEdge(0, 3);
        graphList.addEdge(3, 4);
        graphList.addEdge(1, 5);

        graphMatrix.addNode(0);
        graphMatrix.addNode(1);
        graphMatrix.addNode(2);
        graphMatrix.addNode(3);
        graphMatrix.addNode(4);
        graphMatrix.addNode(5);

        graphMatrix.addEdge(0, 3, 1);
        graphMatrix.addEdge(0, 2, 1);
        graphMatrix.addEdge(0, 1, 1);
        graphMatrix.addEdge(3, 4, 1);
        graphMatrix.addEdge(1, 5, 1);
    }

    @Test
    void testBfsValidStart() {
        assertDoesNotThrow(() -> bfs.bfs(graphMatrix, 0));
        assertDoesNotThrow(() -> bfs.bfs(graphList, 0));
    }

    @Test
    void testBfsNullGraph() {
        assertThrows(NullPointerException.class, () -> bfs.bfs(null, 0));
    }

    @Test
    void testBfsInvalidStart() {
        assertThrows(NullPointerException.class, () -> bfs.bfs(graphMatrix, 10));
        assertThrows(NullPointerException.class, () -> bfs.bfs(graphList, 10));

    }

    @Test
    void testBfsTargetFound() {
        assertTrue(bfs.bfsTarget(graphMatrix, 0, 3));
        assertTrue(bfs.bfsTarget(graphList, 0, 3));
    }

    @Test
    void testBfsTargetNotFound() {
        assertFalse(bfs.bfsTarget(graphMatrix, 0, 6));
        assertFalse(bfs.bfsTarget(graphList, 0, 6));
    }

    @Test
    void testDfsValidStart() {
        assertDoesNotThrow(() -> dfs.dfs(graphMatrix, 0));
        assertDoesNotThrow(() -> dfs.dfs(graphList, 0));
    }

    @Test
    void testDfsNullGraph() {
        assertThrows(NullPointerException.class, () -> dfs.dfs(null, 0));
    }

    @Test
    void testDfsInvalidStart() {
        assertThrows(NullPointerException.class, () -> dfs.dfs(graphMatrix, 10));
        assertThrows(NullPointerException.class, () -> dfs.dfs(graphList, 10));
    }

    @Test
    void testDfsTargetFound() {
        assertTrue(dfs.dfsTarget(graphMatrix, 0, 3));
        assertTrue(dfs.dfsTarget(graphList, 0, 3));
    }

    @Test
    void testDfsTargetNotFound() {
        assertFalse(dfs.dfsTarget(graphMatrix, 0, 6));
        assertFalse(dfs.dfsTarget(graphList, 0, 6));
    }
    @Test
    void testBfsSequence() {
        assertEquals("2 0 1 3 5 4", bfs.bfsSeq(graphMatrix, 2));
        assertEquals("2 0 1 3 5 4", bfs.bfsSeq(graphList, 2));
    }
    @Test
    void testDfsSequence() {
        assertEquals("2 0 3 4 1 5", dfs.dfsSeq(graphMatrix, 2));
        assertEquals("2 0 3 4 1 5", dfs.dfsSeq(graphList, 2));
    }
}
