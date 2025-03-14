package benchmark;

import java.util.*;
import graphs.*;
import searchAlgorithms.Bfs;
import searchAlgorithms.Dfs;

public class BfsDfsBenchmark {
    private static Random rand = new Random();
    private static int REPETITIONS = 30;

    public static Graph generateGridGraph(int rows, int cols) {
        int size = rows * cols;
        Graph graph = new AdjList(size);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int node = i * cols + j;
                if (j < cols - 1) graph.addEdge(node, node + 1, 1);
                if (i < rows - 1) graph.addEdge(node, node + cols, 1);
            }
        }
        return graph;
    }

    public static Graph generateDeepTreeGraph(int depth) {
        int size = depth * 2 - 1;
        Graph graph = new AdjList(size);

        for (int i = 0; i < depth - 1; i++) {
            graph.addEdge(i, i + 1, 1);
        }

        for (int i = depth; i < size; i++) {
            graph.addEdge(0, i, 1);
        }

        return graph;
    }

    public static Graph generateDenseGraph(int size, double density) {
        Graph graph = new AdjList(size);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density) {
                    graph.addEdge(i, j, 1);
                }
            }
        }
        return graph;
    }

    private static long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / (1024);
    }

    public static void benchmark(String testName, Graph graph, int startNode, int targetNode) {
        Dfs dfs = new Dfs();
        Bfs bfs = new Bfs();
        double totalTimeBfs = 0, totalTimeDfs = 0;
        long totalMemoryBfs = 0, totalMemoryDfs = 0;

        for (int i = 0; i < REPETITIONS; i++) {
            long startMemoryDfs = getMemoryUsage();
            long startTimeDfs = System.nanoTime();
            dfs.dfsTarget(graph, startNode, targetNode);
            long endTimeDfs = System.nanoTime();
            long endMemoryDfs = getMemoryUsage();
            totalTimeDfs += (endTimeDfs - startTimeDfs) / 1e6;
            totalMemoryDfs += (endMemoryDfs - startMemoryDfs);

            long startMemoryBfs = getMemoryUsage();
            long startTimeBfs = System.nanoTime();
            bfs.bfsTarget(graph, startNode, targetNode);
            long endTimeBfs = System.nanoTime();
            long endMemoryBfs = getMemoryUsage();
            totalTimeBfs += (endTimeBfs - startTimeBfs) / 1e6;
            totalMemoryBfs += (endMemoryBfs - startMemoryBfs);
        }

        double avgTimeBfs = totalTimeBfs / REPETITIONS;
        double avgTimeDfs = totalTimeDfs / REPETITIONS;
        double avgMemoryBfs = (double) totalMemoryBfs / REPETITIONS;
        double avgMemoryDfs = (double) totalMemoryDfs / REPETITIONS;

        System.out.printf("%s | BFS: %.3f ms (%.2f KB) | DFS: %.3f ms (%.2f KB)%n",
                testName, avgTimeBfs, avgMemoryBfs, avgTimeDfs, avgMemoryDfs);
    }

    public static void main(String[] args) {
        int gridRows = 100, gridCols = 100;
        int treeDepth = 10000;
        int denseGraphSize = 2000;
        double denseGraphDensity = 0.9;

        System.out.println("===== Comparação de BFS vs DFS (30 repetições) =====");

        Graph gridGraph = generateGridGraph(gridRows, gridCols);
        benchmark(" (Grafo em Grade)", gridGraph, 0, gridRows * gridCols - 1);

        Graph deepTree = generateDeepTreeGraph(treeDepth);
        benchmark(" (Árvore Profunda)", deepTree, 0, treeDepth - 1);

        Graph denseGraph = generateDenseGraph(denseGraphSize, denseGraphDensity);
        benchmark(" (Dense Graph Non-existent Node)", denseGraph, 0, denseGraphSize+1);

        Graph denseGraph1way = generateDenseGraph(denseGraphSize, denseGraphDensity);
        benchmark(" (Dense Graph Existent Node)", denseGraph1way, 0, denseGraphSize-1);
    }
}