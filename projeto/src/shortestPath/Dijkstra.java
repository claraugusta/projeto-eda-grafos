package shortestPath;

import graphs.*;
import java.util.*;

public class Dijkstra {
    private AdjMatrix graph;

    public Dijkstra(AdjMatrix graph) {
        this.graph = graph;
    }

    public List<Integer> shortestPath(final int sourceNode, final int targetNode) {
        int size = graph.getNodes();
        int[] cost = new int[size];
        int[] predecessor = new int[size];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[sourceNode] = 0;
        Arrays.fill(predecessor, -1);

        priorityQueue.offer(new int[]{0, sourceNode});

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentNode = current[1];

            if (currentNode == targetNode) break;

            for (int neighbor : graph.getAdjNodes(currentNode)) {
                int totalCost = cost[currentNode] + graph.getWeight(currentNode, neighbor);
                if (totalCost < cost[neighbor]) {
                    cost[neighbor] = totalCost;
                    predecessor[neighbor] = currentNode;
                    priorityQueue.offer(new int[]{totalCost, neighbor});
                }
            }
        }
        
        return reconstructPath(predecessor, targetNode);
    }

    private List<Integer> reconstructPath(int[] predecessor, int target) {
        List<Integer> path = new ArrayList<>();
        while (target != -1) {
            path.add(target);
            target = predecessor[target];
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        AdjMatrix graph = new AdjMatrix(6, Integer.MAX_VALUE);
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 10);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 3, 2);
        graph.addEdge(4, 5, 8);

        Dijkstra dijkstra = new Dijkstra(graph);
        System.out.println("Shortest path from 0 to 5: " + dijkstra.shortestPath(0, 5));
    }
}
