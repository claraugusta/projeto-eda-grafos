package benchmark;

import java.util.*;
import graphs.*;
import searchAlgorithms.Bfs;
import searchAlgorithms.Dfs;

public class BfsBenchmark {
    private static final Random rand = new Random();
    private static final int REPETITIONS = 30; // Número de repetições

    /** Gerar um grafo em grade (labirinto) para testar o melhor caso do BFS */
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

    /** Gerar uma árvore com 2 ramos (bifurcação) a partir de um nó raiz */
    public static Graph generateDeepTreeGraph(int depth) {
        int size = depth * 2 - 1; // Ajuste o tamanho para acomodar dois ramos
        Graph graph = new AdjList(size);

        // Cria o primeiro ramo (linear)
        for (int i = 0; i < depth - 1; i++) {
            graph.addEdge(i, i + 1, 1);
        }

        // Cria o segundo ramo, bifurcando a partir do nó raiz (nó 0)
        for (int i = depth; i < size; i++) {
            graph.addEdge(0, i, 1); // Conecta o nó raiz (0) aos nós do segundo ramo
        }

        return graph;
    }

    /** Gerar um grafo denso e profundo (pior caso para ambos) */
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

    /** Método para calcular o uso de memória em MB */
    private static long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024); // Converte para MB
    }

    public static void benchmark(String testName, Graph graph, int startNode, int targetNode) {
        Dfs dfs = new Dfs();
        Bfs bfs = new Bfs();
        double totalTimeBfs = 0, totalTimeDfs = 0;
        long totalMemoryBfs = 0, totalMemoryDfs = 0;

        for (int i = 0; i < REPETITIONS; i++) {
            // Medir tempo e memória para DFS
            long startMemoryDfs = getMemoryUsage();
            long startTimeDfs = System.nanoTime();
            dfs.dfsTarget(graph, startNode, targetNode);
            long endTimeDfs = System.nanoTime();
            long endMemoryDfs = getMemoryUsage();
            totalTimeDfs += (endTimeDfs - startTimeDfs) / 1e6; // Converte para ms
            totalMemoryDfs += (endMemoryDfs - startMemoryDfs); // Memória em MB

            // Medir tempo e memória para BFS
            long startMemoryBfs = getMemoryUsage();
            long startTimeBfs = System.nanoTime();
            bfs.bfsTarget(graph, startNode, targetNode);
            long endTimeBfs = System.nanoTime();
            long endMemoryBfs = getMemoryUsage();
            totalTimeBfs += (endTimeBfs - startTimeBfs) / 1e6; // Converte para ms
            totalMemoryBfs += (endMemoryBfs - startMemoryBfs); // Memória em MB
        }

        // Calcular médias
        double avgTimeBfs = totalTimeBfs / REPETITIONS;
        double avgTimeDfs = totalTimeDfs / REPETITIONS;
        double avgMemoryBfs = (double) totalMemoryBfs / REPETITIONS;
        double avgMemoryDfs = (double) totalMemoryDfs / REPETITIONS;

        // Exibir resultados
        System.out.printf("%s | BFS: %.3f ms (%.2f MB) | DFS: %.3f ms (%.2f MB)%n",
                testName, avgTimeBfs, avgMemoryBfs, avgTimeDfs, avgMemoryDfs);
    }

    public static void main(String[] args) {
        int gridRows = 100, gridCols = 100;
        int treeDepth = 10000;
        int denseGraphSize = 2000;
        double denseGraphDensity = 0.9;

        System.out.println("===== Comparação de BFS vs DFS (30 repetições) =====");

        // Melhor caso para BFS - Labirinto (Grafo em Grade)
        Graph gridGraph = generateGridGraph(gridRows, gridCols);
        benchmark("Melhor caso BFS (Grafo em Grade)", gridGraph, 0, gridRows * gridCols - 1);

        // Melhor caso para DFS - Árvore Profunda
        Graph deepTree = generateDeepTreeGraph(treeDepth);
        benchmark("Melhor caso DFS (Árvore Profunda)", deepTree, 0, treeDepth - 1);

        // Pior caso para ambos - Grafo Denso
        Graph denseGraph = generateDenseGraph(denseGraphSize, denseGraphDensity);
        benchmark("Pior caso BFS/DFS (Grafo Denso)", denseGraph, 0, denseGraphSize - 1);
    }
}