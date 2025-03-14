package benchmark;

import graphs.Graph;

import java.io.FileWriter;
import java.io.IOException;

import static shortestPath.BellmanFord.bellmanFord;
import static shortestPath.Dijkstra.dijkstra;
import static shortestPath.Johnson.johnson;

public class ShortestPathBenchmark {

    private static final int INF = Integer.MAX_VALUE;
    private static final GenerateGraphs g = new GenerateGraphs();



    public static void main(String[] args) {
        int[] sizes = {5, 10, 100};
        double[] densities = {0.1, 0.3, 0.5, 0.7};
        double[] negativeEdgeProbs = {0.1, 0.3, 0.6};
        System.out.println("Grafos com arestas negativas");
        for (int size : sizes) {
            for (double density : densities) {
                for (double negProb : negativeEdgeProbs) {
                    Graph randGraph = g.generateDirectedAdjMatrixNegativeEdges(size, INF, density, negProb, 20);
                    runBenchmarkNegativeEdges(size, density, negProb, randGraph);
                }
            }
        }

        // Comparação entre matriz e lista de adjacência
        System.out.println("Comparação entre Matriz e Lista de Adjacência");
        for (int size : sizes) {
            double density = 0.5;
            Graph matrixGraph = g.generateAdjMatrix(size, INF, density);
            Graph listGraph = g.generateTreeAdjList(size, INF, 20);
            runBenchmark(size, density, 0, matrixGraph);
            runBenchmark(size, density, 0, listGraph);
        }

        // Teste: Grafo Completamente Conectado
        System.out.println("Teste: Grafo Completamente Conectado");
        for (int size : sizes) {
            Graph completeGraph = g.generateAdjMatrix(size, INF, 1.0);
            runBenchmark(size, 1.0, 0, completeGraph);
        }

        // Teste: Grafo com Componentes Desconexos
        System.out.println("Teste: Grafo com Componentes Desconexos");
        for (int size : sizes) {
            Graph disconnectedGraph = g.generateAdjMatrix(size, INF, 0.05);
            runBenchmark(size, 0.05, 0, disconnectedGraph);
        }

        // Teste: Grafo Direcionado Denso com Arestas Negativas
        System.out.println("Teste: Grafo Direcionado Denso com Arestas Negativas");
        for (int size : sizes) {
            double density = 0.9;
            double negProb = 0.5;
            Graph graph = g.generateDirectedAdjMatrixNegativeEdges(size, INF, density, negProb, 20);
            runBenchmarkNegativeEdges(size, density, negProb, graph);
        }

        System.out.println("Teste: Grafo Muito Grande e Esparso");
            int size = 500;
            double density = 0.01;
            Graph graph = g.generateAdjMatrix(size, INF, density);
            runBenchmark(size, density, 0, graph);
    }

    private static void runBenchmark(int size, double density, double negProb, Graph graph) {
        double totalTimeBellmanFord = 0;
        double totalTimeDijkstra = 0;
        double totalTimeJohnson = 0;
        long totalMemoryBellmanFord = 0;
        long totalMemoryDijkstra = 0;
        long totalMemoryJohnson = 0;
        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i < 30; i++) {
            runtime.gc();
            long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.nanoTime();

            try {
                bellmanFord(graph, 0, graph.numberOfNodes());
            } catch (Exception e) {
            }

            long endTime = System.nanoTime();
            long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryBellmanFord += (memoryAfter - memoryBefore) / 1024;
            totalTimeBellmanFord += (endTime - startTime) / 1e6;

            runtime.gc();
            memoryBefore = runtime.totalMemory() - runtime.freeMemory();
            startTime = System.nanoTime();

            dijkstra(graph, 0);

            endTime = System.nanoTime();
            memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryDijkstra += (memoryAfter - memoryBefore) / 1024;
            totalTimeDijkstra += (endTime - startTime) / 1e6;

            runtime.gc();
            memoryBefore = runtime.totalMemory() - runtime.freeMemory();

            long startTimeJohnson = System.nanoTime();
            johnson(graph);
            long endTimeJohnson = System.nanoTime();
            memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryJohnson += (memoryAfter - memoryBefore) / 1024;
            totalTimeJohnson += (endTimeJohnson - startTimeJohnson) / 1e6;
        }

        totalTimeBellmanFord /= 30.0;
        totalTimeDijkstra /= 30.0;
        totalTimeJohnson /= 30.0;
        totalMemoryBellmanFord /= 30.0;
        totalMemoryDijkstra /= 30.0;
        totalMemoryJohnson /= 30.0;

        System.out.println("Size: " + size + ", Density: " + density + ", NegProb: " + negProb);
        System.out.println("(Bellman-Ford) Time: " + totalTimeBellmanFord + " ms, Memory: " + totalMemoryBellmanFord + " KB");
        System.out.println("(Dijkstra) Time: " + totalTimeDijkstra + " ms, Memory: " + totalMemoryDijkstra + " KB");
        System.out.println("(Johnson) Time: " + totalTimeJohnson + " ms, Memory: " + totalMemoryJohnson + " KB");
        System.out.println();
    }

    public static void runBenchmarkNegativeEdges(int size, double density, double negProb, Graph graph) {
        double totalTimeBellmanFord = 0;
        double totalTimeJohnson = 0;
        long totalMemoryBellmanFord = 0;
        long totalMemoryJohnson = 0;

        for (int j = 0; j < 30; j++) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

            long startTimeBellmanFord = System.nanoTime();
            try {
                bellmanFord(graph, 0, graph.numberOfNodes());
            } catch (Exception e) {
            }
            long endTimeBellmanFord = System.nanoTime();
            long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryBellmanFord += (memoryAfter - memoryBefore) / 1024;
            totalTimeBellmanFord += (endTimeBellmanFord - startTimeBellmanFord) / 1e6;

            runtime.gc();
            memoryBefore = runtime.totalMemory() - runtime.freeMemory();

            long startTimeJohnson = System.nanoTime();
            johnson(graph);
            long endTimeJohnson = System.nanoTime();
            memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryJohnson += (memoryAfter - memoryBefore) / 1024;
            totalTimeJohnson += (endTimeJohnson - startTimeJohnson) / 1e6;
        }

        totalTimeBellmanFord /= 30.0;
        totalTimeJohnson /= 30.0;
        totalMemoryBellmanFord /= 30.0;
        totalMemoryJohnson /= 30.0;

        System.out.println("Size: " + size + ", Density: " + density + ", NegProb: " + negProb);
        System.out.println("(Bellman-Ford) Time: " + totalTimeBellmanFord + " ms, Memory: " + totalMemoryBellmanFord + " KB");
        System.out.println("(Johnson) Time: " + totalTimeJohnson + " ms, Memory: " + totalMemoryJohnson + " KB");
        System.out.println();
    }
}
