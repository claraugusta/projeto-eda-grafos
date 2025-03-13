package benchmark;

import graphs.*;

import java.util.Random;

public class GenerateGraphs {
    public static Graph generateAdjListGraph(int size, double density) {
        Graph graph = new AdjList(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density)
                    graph.addEdge(i, j, 1);
            }
        }
        return graph;
    }

    public static Graph generateAdjMatrixGraph(int size, int nullEdgeValue, double density){
        Graph graph = new AdjMatrix(size, nullEdgeValue);
        Random rand = new Random();
        for (int k = 0; k < size; k++)
            graph.addNode(k);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density)
                    graph.addEdge(i, j, 1);
            }
        }
        return graph;
    }

    public static Graph generateDirectedAdjMatrix(int size, int nullEdgeValue, double density) {
        Graph graph = new DirectedAdjMatrix(size, nullEdgeValue);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j && rand.nextDouble() < density)
                    graph.addEdge(i, j, 1);
            }
        }
        return graph;
    }

    public static Graph generateDirectedAdjMatrixNegativeEdges(
            int size, int nullEdgeValue, double density, double negativeProb, int maxWeight) {

        Graph graph = new DirectedAdjMatrix(size, nullEdgeValue);

        for (int i = 0; i < size; i++)
            graph.addNode(i);

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j && rand.nextDouble() < density) {
                    int weight = rand.nextInt(maxWeight) + 1;
                    if (rand.nextDouble() < negativeProb)
                        weight = -weight;
                    graph.addEdge(i, j, weight);
                }
            }
        }
        return graph;
    }

    public static Graph generateTreeDirectedAdjMatrix(int size, int nullEdgeValue) {
        Graph graph = new DirectedAdjMatrix(size, nullEdgeValue);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 1; i < size; i++) {
            int parent = rand.nextInt(i);
            graph.addEdge(parent, i, 1);
        }
        return graph;
    }

    public static Graph generateAdjMatrix(int size, int nullEdgeValue) {
        Graph graph = new AdjMatrix(size, nullEdgeValue);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 1; i < size; i++) {
            int parent = rand.nextInt(i);
            graph.addEdge(parent, i, 1);
        }
        return graph;
    }

    public static Graph generateTreeAdjMatrix(int size, int nullEdgeValue, int maxWeight) {
        Graph graph = new AdjMatrix(size, nullEdgeValue);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 1; i < size; i++) {
            int parent = rand.nextInt(i);
            int weight = rand.nextInt(maxWeight) + 1;
            graph.addEdge(parent, i, weight);
        }
        return graph;
    }

    public static void main(String[] args) {
        int size = 5;
        int nullEdgeValue = 0;
        double density = 0.5;

        Graph graph = generateDirectedAdjMatrix(size, nullEdgeValue, density);

        if (graph instanceof DirectedAdjMatrix) {
            DirectedAdjMatrix directedGraph = (DirectedAdjMatrix) graph;
            System.out.println("Matriz de Adjacência do Grafo Direcionado:");
            System.out.println(directedGraph.MatrixToString());
        }

        size = 5;
        nullEdgeValue = 0;
        density = 0.7;
        double negativeProb = 0.4;
        int maxWeight = 10;

        graph = generateDirectedAdjMatrixNegativeEdges(
                size, nullEdgeValue, density, negativeProb, maxWeight);

        if (graph instanceof DirectedAdjMatrix) {
            DirectedAdjMatrix directedGraph = (DirectedAdjMatrix) graph;
            System.out.println("Matriz de Adjacência do Grafo Direcionado com Arestas Negativas:");
            System.out.println(directedGraph.MatrixToString());
        }

        Graph tree = generateTreeAdjMatrix(size, nullEdgeValue, maxWeight);

        if (tree instanceof AdjMatrix) {
            AdjMatrix tree1 = (AdjMatrix) tree;
            System.out.println("Matriz de Adjacência da Árvore:");
            System.out.println(tree1.MatrixToString());
        }
    }
}

