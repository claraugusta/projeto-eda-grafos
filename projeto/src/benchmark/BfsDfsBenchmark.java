package benchmark;

import java.io.FileWriter;
import java.io.IOException;
import graphs.*;
import searchAlgorithms.Bfs;
import searchAlgorithms.Dfs;

public class BfsDfsBenchmark {
    private static int REPETITIONS = 30;
    private static FileWriter csvWriter;

    private static long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / 1024;
    }

    public static void benchmark(String testName, Graph graph, int startNode, int targetNode) {
        Dfs dfs = new Dfs();
        Bfs bfs = new Bfs();
        double totalTimeBfs = 0, totalTimeDfs = 0;
        long maxMemoryBfs = 0, maxMemoryDfs = 0;

        System.gc();

        for (int i = 0; i < REPETITIONS; i++) {
            long beforeMemoryDfs = getMemoryUsage();
            long startTimeDfs = System.nanoTime();
            dfs.dfsTarget(graph, startNode, targetNode);
            long endTimeDfs = System.nanoTime();
            long afterMemoryDfs = getMemoryUsage();

            totalTimeDfs += (endTimeDfs - startTimeDfs) / 1e6;
            long usedMemoryDfs = afterMemoryDfs - beforeMemoryDfs;
            if (usedMemoryDfs > 0) maxMemoryDfs = Math.max(maxMemoryDfs, usedMemoryDfs);

            long beforeMemoryBfs = getMemoryUsage();
            long startTimeBfs = System.nanoTime();
            bfs.bfsTarget(graph, startNode, targetNode);
            long endTimeBfs = System.nanoTime();
            long afterMemoryBfs = getMemoryUsage();

            totalTimeBfs += (endTimeBfs - startTimeBfs) / 1e6;
            long usedMemoryBfs = afterMemoryBfs - beforeMemoryBfs;
            if (usedMemoryBfs > 0) maxMemoryBfs = Math.max(maxMemoryBfs, usedMemoryBfs);
        }

        double avgTimeBfs = totalTimeBfs / REPETITIONS;
        double avgTimeDfs = totalTimeDfs / REPETITIONS;

        System.out.printf("%s\n", testName);
        System.out.printf("BFS | Tempo: %.3f ms | Memória: %.2f KB\n", avgTimeBfs, (double) maxMemoryBfs);
        System.out.printf("DFS | Tempo: %.3f ms | Memória: %.2f KB\n", avgTimeDfs, (double) maxMemoryDfs);
        System.out.println("-------------------------------------------------");

        // Escreve no CSV
        try {
            csvWriter.write(String.format("\"%s\",BFS,%.3f,%.2f\n", testName, avgTimeBfs, (double) maxMemoryBfs));
            csvWriter.write(String.format("\"%s\",DFS,%.3f,%.2f\n", testName, avgTimeDfs, (double) maxMemoryDfs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            csvWriter = new FileWriter("data/benchmarkBFSDFS.csv");
            csvWriter.write("cenario,algoritmo,tempo,memoria\n");

            GenerateGraphs generator = new GenerateGraphs();
            int gridRows = 200, gridCols = 200;
            int treeDepth = 20000;
            int denseGraphSize = 2000;
            double denseGraphDensity = 0.9;

            System.out.println("===== Comparação de BFS vs DFS (30 repetições) =====");

            Graph gridGraph = generator.generateGridGraph(gridRows, gridCols);
            benchmark("Grafo em Grade", gridGraph, 0, gridRows * gridCols - 1);

            Graph deepTree = generator.generateDeepTreeGraph(treeDepth);
            benchmark("Árvore Profunda", deepTree, 0, treeDepth - 1);

            Graph denseGraph = generator.generateDenseGraph(denseGraphSize, denseGraphDensity);
            benchmark("Dense Graph (Nó Inexistente)", denseGraph, 0, denseGraphSize + 1);

            Graph denseGraph1way = generator.generateDenseGraph(denseGraphSize, denseGraphDensity);
            benchmark("Dense Graph (Nó Existente)", denseGraph1way, 0, denseGraphSize - 1);

            csvWriter.close();
            System.out.println("Resultados salvos em benchmarkBFSDFS.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Process process = Runtime.getRuntime().exec("python3 gerar_grafico.py");
            process.waitFor();
            System.out.println("Gráfico gerado com Python.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}