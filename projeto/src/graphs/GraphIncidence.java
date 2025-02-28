package graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphIncidence {
    private ArrayList<Integer> nodes;  
    private ArrayList<int[]> edges;  

    public GraphIncidence() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    // Adiciona um nó ao grafo

    public boolean addNode(int node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
            return true;
        }
        return false;
    }

    // Adiciona uma aresta entre dois nós

    public boolean addEdge(int node1, int node2, int weight) {
        if (nodes.contains(node1) && nodes.contains(node2)) {
            edges.add(new int[]{node1, node2});
            return true;
        }
        return false;
    }

    // Retorna a matriz como uma String
    public String getIncidenceMatrix() {
        int[][] matrix = new int[nodes.size()][edges.size()];

        for (int j = 0; j < edges.size(); j++) {
            int[] edge = edges.get(j);
            int node1 = edge[0];
            int node2 = edge[1];

            int nodeIndex1 = nodes.indexOf(node1);
            int nodeIndex2 = nodes.indexOf(node2);

            matrix[nodeIndex1][j] = 1; 
            matrix[nodeIndex2][j] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GraphIncidence graph = new GraphIncidence();

        // graph.addNode(1);
        // graph.addNode(2);
        // graph.addNode(3);
        // graph.addNode(4);
        // graph.addNode(5);

        // graph.addEdge(1, 2);
        // graph.addEdge(2, 3);
        // graph.addEdge(3, 4);
        // graph.addEdge(4, 5);
        // graph.addEdge(5, 1);

        System.out.println("Matriz de Incidência:");
        System.out.println(graph.getIncidenceMatrix());
    }
}
