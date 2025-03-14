package testes;

import graphs.AdjList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class AdjListTeste {
    private AdjList graph;

    @Before
    public void setUp() {
        graph = new AdjList(5); // Cria um grafo com 5 nós
    }

    @Test
    public void testSize() {
        assertEquals(5, graph.size());
    }

    @Test
    public void testAddNode() {
        assertTrue(graph.addNode(5)); // Adiciona um novo nó
        assertEquals(6, graph.size());
        assertFalse(graph.addNode(5)); // Tenta adicionar o mesmo nó novamente
    }

    @Test
    public void testAddEdge() {
        assertTrue(graph.addEdge(0, 1)); // Adiciona uma aresta entre os nós 0 e 1
        assertTrue(graph.hasEdge(0, 1)); // Verifica se a aresta foi adicionada
        assertTrue(graph.hasEdge(1, 0)); // Verifica a aresta no sentido inverso (grafo não direcionado)
        assertFalse(graph.addEdge(0, 6)); // Tenta adicionar uma aresta com um nó inválido
    }

    @Test
    public void testRemoveEdge() {
        graph.addEdge(0, 1);
        graph.removeEdge(0, 1);
        assertFalse(graph.hasEdge(0, 1)); // Verifica se a aresta foi removida
        assertFalse(graph.hasEdge(1, 0)); // Verifica a aresta no sentido inverso
    }

    @Test
    public void testHasEdge() {
        graph.addEdge(0, 1);
        assertTrue(graph.hasEdge(0, 1));
        assertFalse(graph.hasEdge(0, 2)); // Verifica uma aresta que não existe
    }

    @Test
    public void testGetDegree() {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        assertEquals(2, graph.getDegree(0)); // Verifica o grau do nó 0
        assertEquals(1, graph.getDegree(1)); // Verifica o grau do nó 1
    }

//    @Test
//    public void testGetAdj() {
//        graph.addEdge(0, 1);
//        graph.addEdge(0, 2);
//        List<Integer> adj = graph.getAdj(0);
//        assertTrue(adj.contains(1));
//        assertTrue(adj.contains(2));
//        assertEquals(2, adj.size());
//    }

    @Test
    public void testNumberOfNodes() {
        assertEquals(5, graph.numberOfNodes());
        graph.addNode(5);
        assertEquals(6, graph.numberOfNodes());
    }

    @Test
    public void testToString() {
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        String expected = "Node 0 -> 1 2 \n" +
                "Node 1 -> 0 \n" +
                "Node 2 -> 0 \n" +
                "Node 3 -> \n" +
                "Node 4 -> \n";
        assertEquals(expected, graph.toString());
    }
}