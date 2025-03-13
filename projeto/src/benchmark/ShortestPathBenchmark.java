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

        int[] sizes = {5,10,100};
        Random rand = new Random();
        for (int i : sizes) {

            double totalTimeBellmanFord = 0;
            double totalTimeDijkstra = 0;

            for (int j = 0; j < 30; j++) {
                int busca = rand.nextInt(i-1);
                Graph randGraph = g.generateAdjMatrixWeight(i, INF, 0.1, 20);

                long startTimeBellmanFord = System.nanoTime();
                b.bellmanFord(randGraph, 0, randGraph.numberOfNodes());
                long endTime = System.nanoTime();
                totalTimeBellmanFord += (endTime-startTimeBellmanFord)/ 1e6;

                long startTimeDijkstra = System.nanoTime();
                d.dijkstra(randGraph, 0);
                long endTimeDijkstra = System.nanoTime();
                totalTimeDijkstra += (endTimeDijkstra-startTimeDijkstra)/ 1e6;
            }
            totalTimeBellmanFord = totalTimeBellmanFord / 30.0;
            totalTimeDijkstra = totalTimeDijkstra / 30.0;
            System.out.println("(BellmanFord) size:"+ i + " time: "+ totalTimeBellmanFord + " ms");
            System.out.println("(Dijkstra) size:"+ i + " time: "+ totalTimeDijkstra + " ms");
            System.out.println();
        }
    }
}
