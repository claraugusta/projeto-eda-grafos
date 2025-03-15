package graphs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphIncidenceTeste {
    private GraphIncidence graph;

    @BeforeEach
    void setUp() {
        graph = new GraphIncidence();
    }

    @Test
    void testAddNode() {
        assertTrue(graph.addNode(1));
        assertFalse(graph.addNode(1));
    }

    @Test
    void testAddMultipleNodes() {
        assertTrue(graph.addNode(1));
        assertTrue(graph.addNode(2));
        assertTrue(graph.addNode(3));
        assertEquals(3, graph.getIncidenceMatrix().split("\n").length);
    }

    @Test
    void testAddEdgeValid() {
        graph.addNode(1);
        graph.addNode(2);
        assertTrue(graph.addEdge(1, 2, 1));
    }

    @Test
    void testAddEdgeInvalid() {
        assertFalse(graph.addEdge(1, 2, 1));
    }

    @Test
    void testIncidenceMatrix() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);

        String expectedMatrix = "[1, 0]\n[1, 1]\n[0, 1]\n";
        assertEquals(expectedMatrix, graph.getIncidenceMatrix());
    }

    @Test
    void testEmptyGraphIncidenceMatrix() {
        assertEquals("", graph.getIncidenceMatrix());
    }

    @Test
    void testGraphWithIsolatedNodes() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addEdge(1, 2, 1);

        System.out.println(graph.getIncidenceMatrix());

        assertFalse(graph.getIncidenceMatrix().isEmpty());
    }
}