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

    public static void main(String[] args) {
        // Exemplo de uso
        int size = 5; // Número de vértices
        int nullEdgeValue = 0; // Valor que representa a ausência de arestas
        double density = 0.5; // Densidade do grafo (50% de chance de existir uma aresta)

        // Gera o grafo direcionado
        Graph graph = generateDirectedAdjMatrix(size, nullEdgeValue, density);

        // Exibe a matriz de adjacência
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

        // Exibe a matriz de adjacência
        if (graph instanceof DirectedAdjMatrix) {
            DirectedAdjMatrix directedGraph = (DirectedAdjMatrix) graph;
            System.out.println("Matriz de Adjacência do Grafo Direcionado com Arestas Negativas:");
            System.out.println(directedGraph.MatrixToString());
        }
    }
}

