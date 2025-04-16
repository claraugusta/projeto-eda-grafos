package benchmark;

import graphs.Graph;
import static shortestPath.BellmanFord.bellmanFord;
import static shortestPath.Dijkstra.dijkstra;
import static shortestPath.Johnson.johnson;
import static shortestPath.FloydWarshall.floydWarshall;
import static shortestPath.NearestNeighbor.nearestNeighbor;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class ShortestPathBenchmark {

    private static final int INF = Integer.MAX_VALUE;
    private static final GenerateGraphs g = new GenerateGraphs();


    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int[] sizes = {5, 10, 100};
        double[] densities = {0.3};
        double[] negativeEdgeProbs = {0.3};

        try (PrintWriter writer = new PrintWriter(new FileWriter("data/benchmark_Shortest_path.csv"))) {
            writer.println("Titulo,Cenario,Algoritmo,Tempo(ms),Memoria(KB)");

            String title = "Teste: Grafos com arestas negativas";
            System.out.println(title);
            for (int size : sizes) {
                for (double density : densities) {
                    for (double negProb : negativeEdgeProbs) {
                        Graph randGraph = g.generateDirectedAdjMatrixNegativeEdges(size, INF, density, negProb, 50);
                        runBenchmarkNegativeEdges(writer,title, size, density, negProb, randGraph);
                    }
                }
            }


            title = "Teste Comparação Bellman Ford: Matriz e Lista de Adjacência";
            System.out.println(title);
            for(int size : sizes){
                double density = 0.5;
                double negProb = 0.2;
                Graph adjMatrix = g.generateDirectedAdjMatrixNegativeEdges(size, INF, density, negProb, 50);
                Graph adjListWeighted = g.generateAdjListWeighted(size, density, negProb, 50);
                runBenchmarkBellman(writer, title, size, density, negProb, adjMatrix, adjListWeighted);
            }

            title = "Teste: Grafo Completamente Conectado";
            System.out.println(title);
            for (int size : sizes) {
                Graph completeGraph = g.generateAdjMatrix(size, INF, 1.0);
                runBenchmark(writer,title, size, 1.0, 0, completeGraph);
            }

            title = "Teste: Grafo com Componentes Desconexos";
            System.out.println(title);
            for (int size : sizes) {
                Graph disconnectedGraph = g.generateAdjMatrix(size, INF, 0.05);
                runBenchmark(writer,title, size, 0.05, 0, disconnectedGraph);
            }
            title = "Teste: Grafo Direcionado Denso com Arestas Negativas";
            System.out.println(title);
            for (int size : sizes) {
                double density = 0.9;
                double negProb = 0.5;
                Graph graph = g.generateDirectedAdjMatrixNegativeEdges(size, INF, density, negProb, 20);
                runBenchmarkNegativeEdges(writer,title, size, density, negProb, graph);
            }
            title = "Teste: Grafo Muito Grande e Esparso";
            System.out.println(title);
            int size = 500;
            double density = 0.01;
            Graph graph = g.generateAdjMatrix(size, INF, density);
            runBenchmark(writer, title, size, density, 0, graph);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmark(PrintWriter writer,String title, int size, double density, double negProb, Graph graph) {
        double totalTimeBellmanFord = 0;
        double totalTimeDijkstra = 0;
        double totalTimeJohnson = 0;
        double totalTimeFloydWarshall = 0;
        double totalTimeNearestNeighbor = 0;
        long totalMemoryBellmanFord = 0;
        long totalMemoryDijkstra = 0;
        long totalMemoryJohnson = 0;
        long totalMemoryFloydWarshall = 0;
        long totalMemoryNearestNeighbor = 0;
        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i < 30; i++) {
            runtime.gc();
            long memoryBeforeBellmanFord = runtime.totalMemory() - runtime.freeMemory();
            long startTimeBellmanFord = System.nanoTime();

            try {
                bellmanFord(graph, 0, graph.numberOfNodes());
            } catch (Exception e) {
            }

            long endTimeBellmanFord = System.nanoTime();
            long memoryAfterBellmanFord = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryBellmanFord += (memoryAfterBellmanFord - memoryBeforeBellmanFord) / 1024;
            totalTimeBellmanFord += (endTimeBellmanFord - startTimeBellmanFord) / 1e6;

            runtime.gc();
            long memoryBeforeDijkstra = runtime.totalMemory() - runtime.freeMemory();
            long startTimeDijkstra = System.nanoTime();

            dijkstra(graph, 0);

            long endTimeDijkstra = System.nanoTime();
            long memoryAfterDijkstra = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryDijkstra += (memoryAfterDijkstra - memoryBeforeDijkstra) / 1024;
            totalTimeDijkstra += (endTimeDijkstra - startTimeDijkstra) / 1e6;

            runtime.gc();
            long memoryBeforeJonhson = runtime.totalMemory() - runtime.freeMemory();

            long startTimeJohnson = System.nanoTime();
            try {
                johnson(graph);
            } catch (Exception e) {
            }
            long endTimeJohnson = System.nanoTime();
            long memoryAfterJonhson = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryJohnson += (memoryAfterJonhson - memoryBeforeJonhson) / 1024;
            totalTimeJohnson += (endTimeJohnson - startTimeJohnson) / 1e6;

            runtime.gc();
            long memoryBeforeFloydWarshall = runtime.totalMemory() - runtime.freeMemory();

            long startTimeFloydWarshall = System.nanoTime();
            try {
                floydWarshall(graph);
            } catch (Exception e) {
            }
            long endTimeFloydWarshall = System.nanoTime();
            long memoryAfterFloydWarshall = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryFloydWarshall += (memoryAfterFloydWarshall - memoryBeforeFloydWarshall) / 1024;
            totalTimeFloydWarshall += (endTimeFloydWarshall - startTimeFloydWarshall) / 1e6;
        }



        totalTimeBellmanFord /= 30.0;
        totalTimeDijkstra /= 30.0;
        totalTimeJohnson /= 30.0;
        totalMemoryFloydWarshall /= 30.0;
        totalMemoryBellmanFord /= 30.0;
        totalMemoryDijkstra /= 30.0;
        totalMemoryJohnson /= 30.0;

        String cenario = String.format("Size: %d", size, density, negProb);
        System.out.println(cenario);
        System.out.println("(Bellman-Ford) Time: " + totalTimeBellmanFord + " ms, Memory: " + totalMemoryBellmanFord + " KB");
        System.out.println("(Dijkstra) Time: " + totalTimeDijkstra + " ms, Memory: " + totalMemoryDijkstra + " KB");
        System.out.println("(Johnson) Time: " + totalTimeJohnson + " ms, Memory: " + totalMemoryJohnson + " KB");
        System.out.println("(FloydWarshall) Time: " + totalTimeFloydWarshall + " ms, Memory: " + totalMemoryFloydWarshall + " KB");
        writer.printf("\"%s\",\"%s\",Bellman-Ford,%.3f,%d\n",title, cenario, totalTimeBellmanFord, totalMemoryBellmanFord);
        writer.printf("\"%s\",\"%s\",Dijkstra,%.3f,%d\n",title, cenario, totalTimeDijkstra, totalMemoryDijkstra);
        writer.printf("\"%s\",\"%s\",Johnson,%.3f,%d\n",title, cenario, totalTimeJohnson, totalMemoryJohnson);
        writer.printf("\"%s\",\"%s\",FloydWarshall,%.3f,%d\n",title, cenario, totalTimeFloydWarshall, totalMemoryFloydWarshall);
        System.out.println();
    }

    public static void runBenchmarkNegativeEdges(PrintWriter writer, String title, int size, double density, double negProb, Graph graph) {
        double totalTimeBellmanFord = 0;
        double totalTimeFloydWarshall = 0;
        double totalTimeJohnson = 0;
        long totalMemoryBellmanFord = 0;
        long totalMemoryJohnson = 0;
        long totalMemoryFloydWarshall = 0;

        for (int j = 0; j < 30; j++) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            long memoryBeforeBellmanFord = runtime.totalMemory() - runtime.freeMemory();

            long startTimeBellmanFord = System.nanoTime();
            try {
                bellmanFord(graph, 0, graph.numberOfNodes());
            } catch (Exception e) {
            }
            long endTimeBellmanFord = System.nanoTime();
            long memoryAfterBellmanFord = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryBellmanFord += (memoryAfterBellmanFord - memoryBeforeBellmanFord) / 1024;
            totalTimeBellmanFord += (endTimeBellmanFord - startTimeBellmanFord) / 1e6;

            runtime.gc();
            long memoryBeforeJonhson = runtime.totalMemory() - runtime.freeMemory();
            long startTimeJohnson = System.nanoTime();

            try {
                johnson(graph);
            } catch (Exception e) {
            }
            long endTimeJohnson = System.nanoTime();
            long memoryAfterJonhson = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryJohnson += (memoryAfterJonhson - memoryBeforeJonhson) / 1024;
            totalTimeJohnson += (endTimeJohnson - startTimeJohnson) / 1e6;

            runtime.gc();
            long memoryBeforeFloydWarshall = runtime.totalMemory() - runtime.freeMemory();
            long startTimeFloydWarshall = System.nanoTime();

            try{
                floydWarshall(graph);
            } catch (Exception e){
            }

            long endTimeFloydWarshall = System.nanoTime();
            long memoryAfterFloydWarshall = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryFloydWarshall += (memoryAfterFloydWarshall - memoryBeforeFloydWarshall) / 1024;
            totalTimeFloydWarshall += (endTimeFloydWarshall - startTimeFloydWarshall) / 1e6;

        }

        totalTimeBellmanFord /= 30.0;
        totalTimeJohnson /= 30.0;
        totalTimeFloydWarshall /= 30.0;
        totalMemoryBellmanFord /= 30.0;
        totalMemoryJohnson /= 30.0;
        totalMemoryFloydWarshall /= 30.0;

        String cenario = String.format("Size: %d", size, density, negProb);
        System.out.println(cenario);
        System.out.println("(Bellman-Ford) Time: " + totalTimeBellmanFord + " ms, Memory: " + totalMemoryBellmanFord + " KB");
        System.out.println("(Johnson) Time: " + totalTimeJohnson + " ms, Memory: " + totalMemoryJohnson + " KB");
        System.out.println("(FloydWarshall) Time: " + totalTimeFloydWarshall + " ms, Memory: " + totalMemoryFloydWarshall + " KB");
        writer.printf("\"%s\",\"%s\",Bellman-Ford,%.3f,%d\n",title, cenario, totalTimeBellmanFord,  totalMemoryBellmanFord);
        writer.printf("\"%s\",\"%s\",Johnson,%.3f,%d\n",title, cenario, totalTimeJohnson, totalMemoryJohnson);
        writer.printf("\"%s\",\"%s\",FloydWarshall,%.3f,%d\n",title, cenario, totalTimeFloydWarshall, totalMemoryFloydWarshall);
        System.out.println();
    }

    public static void runBenchmarkBellman(PrintWriter writer,String title, int size, double density, double negProb, Graph adjMatrix, Graph adjList) {
        double totalTimeAdjMatrix = 0;
        double totalTimeAdjList = 0;
        long totalMemoryAdjMatrix = 0;
        long totalMemoryAdjList = 0;

        for (int j = 0; j < 30; j++) {
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

            long startTimeAdjMatrix = System.nanoTime();
            try {
                bellmanFord(adjMatrix, 0, adjMatrix.numberOfNodes());
            } catch (Exception e) {
            }
            long endTimeAdjMatrix = System.nanoTime();
            long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryAdjMatrix += (memoryAfter - memoryBefore) / 1024;
            totalTimeAdjMatrix += (endTimeAdjMatrix - startTimeAdjMatrix) / 1e6;

            runtime.gc();
            memoryBefore = runtime.totalMemory() - runtime.freeMemory();

            long startTimeAdjList = System.nanoTime();
            try {
                bellmanFord(adjList, 0, adjList.numberOfNodes());
            } catch (Exception e) {
            }
            long endTimeAdjList = System.nanoTime();
            memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            totalMemoryAdjList += (memoryAfter - memoryBefore) / 1024;
            totalTimeAdjList += (endTimeAdjList - startTimeAdjList) / 1e6;
        }

        totalTimeAdjMatrix /= 30.0;
        totalTimeAdjList /= 30.0;
        totalMemoryAdjMatrix /= 30.0;
        totalMemoryAdjList /= 30.0;

        String cenario = String.format("Size: %d", size, density, negProb);
        System.out.println(cenario);
        System.out.println("(Bellman-Ford: Adjacency Matrix) Time: " + totalTimeAdjMatrix + " ms, Memory: " + totalMemoryAdjMatrix + " KB");
        System.out.println("(Bellman-Ford: Adjacency List) Time: " + totalTimeAdjList + " ms, Memory: " + totalMemoryAdjList + " KB");
        writer.printf("\"%s\",\"%s\",Matrix,%.3f,%d\n",title, cenario, totalTimeAdjMatrix, totalMemoryAdjMatrix);
        writer.printf("\"%s\",\"%s\",List,%.3f,%d\n",title, cenario, totalTimeAdjList, totalMemoryAdjList);
        System.out.println();
    }

}
