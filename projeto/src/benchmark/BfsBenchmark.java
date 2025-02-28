package benchmark;

import java.util.*;
import graphs.*;
import searchAlgorithms.Bfs;

public class BfsBenchmark{
    public static Graph generateGraph(int size, double density) {
        Graph graph = new AdjList(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density) {
                    graph.addEdge(i, j, 1);
                }
            }
        }
        return graph;
    }
    public static void main(String[] args) {
        int[] sizes = {5,10,100,500};
        for (int i : sizes) {
            double totalTime = 0;
            for (int j = 0; j < 30; j++) {
                Bfs bfs = new Bfs(generateGraph(i, 0.5));
                long startTime = System.nanoTime();
                bfs.runBfs(i+1);
                long endTime = System.nanoTime();
                totalTime += (endTime-startTime)/ 1e6;
            }
            totalTime = totalTime / 30.0;
            System.out.println("(BFS) size: "+ i + " time: "+ totalTime + " ms");
        }
    }
}
