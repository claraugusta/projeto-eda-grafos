package shortestPath;

import java.security.InvalidParameterException;
import java.util.*;

public class Dijkstra {

    private int vertices[][];

    public Dijkstra(final int numVertices) {
        vertices = new int[numVertices][numVertices];
    }

    public void createEdge(final int sourceNode, final int destinationNode, final int weight) {
        // Dijkstra only works with positive weights
        if (weight >= 1) {
            vertices[sourceNode][destinationNode] = weight;
            vertices[destinationNode][sourceNode] = weight;
        } else {
            throw new InvalidParameterException(
                "The weight from source node [" + sourceNode + "] to destination node [" + destinationNode + "] cannot be negative"
            );
        }
    }

    public int getClosestNode(final int costList[], final Set<Integer> unvisitedNodes) {
        int minDistance = Integer.MAX_VALUE;
        int closestNode = -1;

        for (Integer i : unvisitedNodes) {
            if (costList[i] < minDistance) {
                minDistance = costList[i];
                closestNode = i;
            }
        }

        return closestNode;
    }

    public List<Integer> getNeighbors(final int node) {
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[node][i] > 0) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    public int getCost(final int sourceNode, final int destinationNode) {
        return vertices[sourceNode][destinationNode];
    }

    public List<Integer> shortestPath(final int sourceNode, final int destinationNode) {
        int cost[] = new int[vertices.length];
        int predecessor[] = new int[vertices.length];
        Set<Integer> unvisitedNodes = new HashSet<>();

        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[sourceNode] = 0;
        
        Arrays.fill(predecessor, -1);

        for (int v = 0; v < vertices.length; v++) {
            unvisitedNodes.add(v);
        }

        while (!unvisitedNodes.isEmpty()) {
            int closestNode = getClosestNode(cost, unvisitedNodes);
            if (closestNode == -1) break; // Avoid infinite loop

            unvisitedNodes.remove(closestNode);

            for (Integer neighbor : getNeighbors(closestNode)) {
                int totalCost = cost[closestNode] + getCost(closestNode, neighbor);

                if (totalCost < cost[neighbor]) {
                    cost[neighbor] = totalCost;
                    predecessor[neighbor] = closestNode;
                }
            }

            if (closestNode == destinationNode) {
                return buildPath(predecessor, destinationNode);
            }
        }
        
        return Collections.emptyList(); // Return an empty list if no path exists
    }

    public List<Integer> buildPath(final int predecessor[], int closestNode) {
        List<Integer> path = new ArrayList<>();
        while (closestNode != -1) {
            path.add(closestNode);
            closestNode = predecessor[closestNode];
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Dijkstra graph = new Dijkstra(6);
        graph.createEdge(0, 1, 4);
        graph.createEdge(0, 2, 2);
        graph.createEdge(1, 2, 5);
        graph.createEdge(1, 3, 10);
        graph.createEdge(2, 4, 3);
        graph.createEdge(3, 5, 6);
        graph.createEdge(4, 3, 2);
        graph.createEdge(4, 5, 8);

        System.out.println("Shortest path from 0 to 5: " + graph.shortestPath(0, 5));
    }
}
