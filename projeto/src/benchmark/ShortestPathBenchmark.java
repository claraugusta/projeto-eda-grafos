package benchmark;

import graphs.*;
import shortestPath.*;

import java.util.Random;

public class ShortestPathBenchmark {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        GenerateGraphs g = new GenerateGraphs();
        BellmanFord b = new BellmanFord();
        Dijkstra d = new Dijkstra();

        int[] sizes = {5, 10, 100};
        double[] densities = {0.1, 0.3, 0.5, 0.7};
        double[] negativeEdgeProbs = {0.1, 0.3, 0.6};

        Random rand = new Random();
        for (int i : sizes) {

            double totalTimeBellmanFord = 0;
            double totalTimeDijkstra = 0;

            for (int j = 0; j < 30; j++) {
                int busca = rand.nextInt(i - 1);
                Graph randGraph = g.generateAdjMatrixWeight(i, INF, 0.1, 20);

                long startTimeBellmanFord = System.nanoTime();
                b.bellmanFord(randGraph, 0, randGraph.numberOfNodes());
                long endTime = System.nanoTime();
                totalTimeBellmanFord += (endTime - startTimeBellmanFord) / 1e6;

                long startTimeDijkstra = System.nanoTime();
                d.dijkstra(randGraph, 0);
                long endTimeDijkstra = System.nanoTime();
                totalTimeDijkstra += (endTimeDijkstra - startTimeDijkstra) / 1e6;
            }
            totalTimeBellmanFord = totalTimeBellmanFord / 30.0;
            totalTimeDijkstra = totalTimeDijkstra / 30.0;
            System.out.println("(BellmanFord) size:" + i + " time: " + totalTimeBellmanFord + " ms");
            System.out.println("(Dijkstra) size:" + i + " time: " + totalTimeDijkstra + " ms");
            System.out.println();
        }

        // NOVOS TESTES
        for (int size : sizes) {
            for (double density : densities) {
                for (double negProb : negativeEdgeProbs) {

                    double totalTimeBellmanFord = 0;
                    double totalTimeDijkstra = 0;
                    long totalMemoryBellmanFord = 0;
                    long totalMemoryDijkstra = 0;

                    for (int j = 0; j < 30; j++) {
                        int busca = rand.nextInt(size - 1);

                        Graph randGraph = g.generateDirectedAdjMatrixNegativeEdges(size, INF, density, negProb, 20);

                        // Medindo uso de memória antes
                        Runtime runtime = Runtime.getRuntime();
                        runtime.gc();
                        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

                        long startTimeBellmanFord = System.nanoTime();
                        b.bellmanFord(randGraph, 0, randGraph.numberOfNodes());
                        long endTime = System.nanoTime();

                        // Memória após a execução
                        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        totalMemoryBellmanFord += (memoryAfter - memoryBefore) / 1024;

                        totalTimeBellmanFord += (endTime - startTimeBellmanFord) / 1e6;

                        // Medindo uso de memória para Dijkstra
                        runtime.gc();
                        memoryBefore = runtime.totalMemory() - runtime.freeMemory();

                        long startTimeDijkstra = System.nanoTime();
                        d.dijkstra(randGraph, 0);
                        long endTimeDijkstra = System.nanoTime();

                        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        totalMemoryDijkstra += (memoryAfter - memoryBefore) / 1024;

                        totalTimeDijkstra += (endTimeDijkstra - startTimeDijkstra) / 1e6;
                    }

                    totalTimeBellmanFord /= 30.0;
                    totalTimeDijkstra /= 30.0;
                    totalMemoryBellmanFord /= 30.0;
                    totalMemoryDijkstra /= 30.0;

                    System.out.println("Size: " + size + ", Density: " + density + ", NegProb: " + negProb);
                    System.out.println("(Bellman-Ford) Time: " + totalTimeBellmanFord + " ms, Memory: " + totalMemoryBellmanFord + " KB");
                    System.out.println("(Dijkstra) Time: " + totalTimeDijkstra + " ms, Memory: " + totalMemoryDijkstra + " KB");
                    System.out.println();
                }
            }
        }

        // Testando a diferença entre matriz de adjacência e lista de adjacência
        System.out.println("Comparação entre Matriz e Lista de Adjacência");

        for (int size : sizes) {
            double density = 0.5;  // Mantemos a densidade fixa para essa comparação

            double totalTimeMatrix = 0;
            double totalTimeList = 0;

            for (int j = 0; j < 30; j++) {
                Graph matrixGraph = g.generateAdjMatrix(size, INF, density);
                Graph listGraph = g.generateTreeAdjList(size, INF, 20);

                long startMatrix = System.nanoTime();
                d.dijkstra(matrixGraph, 0);
                long endMatrix = System.nanoTime();
                totalTimeMatrix += (endMatrix - startMatrix) / 1e6;

                long startList = System.nanoTime();
                d.dijkstra(listGraph, 0);
                long endList = System.nanoTime();
                totalTimeList += (endList - startList) / 1e6;
            }

            totalTimeMatrix /= 30.0;
            totalTimeList /= 30.0;

            System.out.println("Size: " + size);
            System.out.println("(AdjMatrix) Dijkstra Time: " + totalTimeMatrix + " ms");
            System.out.println("(AdjList) Dijkstra Time: " + totalTimeList + " ms");
            System.out.println();
        }

        // Teste: Grafo de Grade (Grid)
        System.out.println("Teste: Grafo em Forma de Grade (Grid)");
        for (int size : sizes) {
            double totalTimeBellmanFord = 0;
            double totalTimeDijkstra = 0;

            for (int j = 0; j < 30; j++) {
                Graph gridGraph = g.generateAdjMatrix(size * size, INF, 4.0 / (size * size)); // Aproximadamente 4 conexões por nó

                long startTimeBF = System.nanoTime();
                b.bellmanFord(gridGraph, 0, gridGraph.numberOfNodes());
                long endTimeBF = System.nanoTime();
                totalTimeBellmanFord += (endTimeBF - startTimeBF) / 1e6;

                long startTimeDijkstra = System.nanoTime();
                d.dijkstra(gridGraph, 0);
                long endTimeDijkstra = System.nanoTime();
                totalTimeDijkstra += (endTimeDijkstra - startTimeDijkstra) / 1e6;
            }

            System.out.println("Size: " + size + "x" + size + " | Bellman-Ford: " + (totalTimeBellmanFord / 30.0) + " ms | Dijkstra: " + (totalTimeDijkstra / 30.0) + " ms");
        }
        System.out.println();

        // Teste: Grafo Completamente Conectado
        System.out.println("Teste: Grafo Completamente Conectado");
        for (int size : sizes) {
            double totalTimeBellmanFord = 0;
            double totalTimeDijkstra = 0;

            for (int j = 0; j < 30; j++) {
                Graph completeGraph = g.generateAdjMatrix(size, INF, 1.0); // 100% de conectividade

                long startTimeBF = System.nanoTime();
                b.bellmanFord(completeGraph, 0, completeGraph.numberOfNodes());
                long endTimeBF = System.nanoTime();
                totalTimeBellmanFord += (endTimeBF - startTimeBF) / 1e6;

                long startTimeDijkstra = System.nanoTime();
                d.dijkstra(completeGraph, 0);
                long endTimeDijkstra = System.nanoTime();
                totalTimeDijkstra += (endTimeDijkstra - startTimeDijkstra) / 1e6;
            }

            System.out.println("Size: " + size + " | Bellman-Ford: " + (totalTimeBellmanFord / 30.0) + " ms | Dijkstra: " + (totalTimeDijkstra / 30.0) + " ms");
        }
        System.out.println();

        // Teste: Grafo com Componentes Desconexos
        System.out.println("Teste: Grafo com Componentes Desconexos");
        for (int size : sizes) {
            double totalTimeBellmanFord = 0;
            double totalTimeDijkstra = 0;

            for (int j = 0; j < 30; j++) {
                Graph disconnectedGraph = g.generateAdjMatrix(size, INF, 0.05); // Baixa conectividade

                long startTimeBF = System.nanoTime();
                b.bellmanFord(disconnectedGraph, 0, disconnectedGraph.numberOfNodes());
                long endTimeBF = System.nanoTime();
                totalTimeBellmanFord += (endTimeBF - startTimeBF) / 1e6;

                long startTimeDijkstra = System.nanoTime();
                d.dijkstra(disconnectedGraph, 0);
                long endTimeDijkstra = System.nanoTime();
                totalTimeDijkstra += (endTimeDijkstra - startTimeDijkstra) / 1e6;
            }

            System.out.println("Size: " + size + " | Bellman-Ford: " + (totalTimeBellmanFord / 30.0) + " ms | Dijkstra: " + (totalTimeDijkstra / 30.0) + " ms");
        }
        System.out.println();

        // Teste: Grafo Direcionado Denso com Arestas Negativas
        System.out.println("Teste: Grafo Direcionado Denso com Arestas Negativas");
        for (int size : sizes) {
            double density = 0.9;
            double negProb = 0.5;

            double totalTimeBellmanFord = 0;
            double totalTimeDijkstra = 0;

            for (int j = 0; j < 30; j++) {
                Graph graph = g.generateDirectedAdjMatrixNegativeEdges(size, INF, density, negProb, 20);

                long startTimeBF = System.nanoTime();
                b.bellmanFord(graph, 0, graph.numberOfNodes());
                long endTimeBF = System.nanoTime();
                totalTimeBellmanFord += (endTimeBF - startTimeBF) / 1e6;

                long startTimeDijkstra = System.nanoTime();
                d.dijkstra(graph, 0);
                long endTimeDijkstra = System.nanoTime();
                totalTimeDijkstra += (endTimeDijkstra - startTimeDijkstra) / 1e6;
            }

            System.out.println("Size: " + size + " | Bellman-Ford: " + (totalTimeBellmanFord / 30.0) + " ms | Dijkstra: " + (totalTimeDijkstra / 30.0) + " ms");
        }

        System.out.println();

        // Teste: Grafo Esparso e Grande
        System.out.println("Teste: Grafo Muito Grande e Esparso");
        int largeSize = 500;
        double sparseDensity = 0.01;
        double totalTimeBellmanFord = 0;
        double totalTimeDijkstra = 0;

        for (int j = 0; j < 10; j++) {  // Reduzindo para 10 execuções devido ao tamanho
            Graph graph = g.generateAdjMatrix(largeSize, INF, sparseDensity);

            long startTimeBF = System.nanoTime();
            b.bellmanFord(graph, 0, graph.numberOfNodes());
            long endTimeBF = System.nanoTime();
            totalTimeBellmanFord += (endTimeBF - startTimeBF) / 1e6;

            long startTimeDijkstra = System.nanoTime();
            d.dijkstra(graph, 0);
            long endTimeDijkstra = System.nanoTime();
            totalTimeDijkstra += (endTimeDijkstra - startTimeDijkstra) / 1e6;
        }

            System.out.println("Size: " + largeSize + " | Bellman-Ford: " + (totalTimeBellmanFord / 10.0) + " ms | Dijkstra: " + (totalTimeDijkstra / 10.0) + " ms");
    }
}
