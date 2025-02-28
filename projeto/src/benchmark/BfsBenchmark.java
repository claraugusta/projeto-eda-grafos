package benchmark;

import java.util.*;
import graphs.*;

import searchAlgorithms.Bfs;
import searchAlgorithms.Dfs;

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
        int[] sizes = {5,10,100,500,1000,2000};
        Random rand = new Random();
        for (int i : sizes) {

            double totalTimeBfs = 0;
            double totalTimeDfs = 0;
            
            for (int j = 0; j < 30; j++) {
                int busca = rand.nextInt(i-1);
                Graph randGraph = generateGraph(i, 0.5);

                Bfs bfs = new Bfs(randGraph);
                long startTimeBfs = System.nanoTime();
                bfs.runBfstarget(busca);
                long endTime = System.nanoTime();
                totalTimeBfs += (endTime-startTimeBfs)/ 1e6;
                
                Dfs dfs = new Dfs(randGraph);
                long startTimeDfs = System.nanoTime();
                dfs.runDfstarget(busca);
                long endTimeDfs = System.nanoTime();
                totalTimeDfs += (endTimeDfs-startTimeDfs)/ 1e6;
            }
            totalTimeBfs = totalTimeBfs / 30.0;
            totalTimeDfs = totalTimeDfs / 30.0;
            System.out.println("(BFS) size:"+ i + " time: "+ totalTimeBfs + " ms");
            System.out.println("(DFS) size:"+ i + " time: "+ totalTimeDfs + " ms");
            
            System.out.println();
        }
    }
}
