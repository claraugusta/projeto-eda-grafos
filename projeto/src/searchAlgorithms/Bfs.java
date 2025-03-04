package searchAlgorithms;
import java.util.*;
import graphs.*;

public class Bfs{

    public void bfs(Graph graph, int start) {
        boolean[] visited = new boolean[graph.numberOfNodes()];
        if (graph == null || visited == null)
            throw new NullPointerException("Null graph, try again");
        if (start > visited.length || start < 0)
            throw new NullPointerException("Invalid start node, try another number");
        
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int neighbors : graph.getAdj(current)) {
                if (!visited[neighbors]) {
                    visited[neighbors] = true;
                    q.add(neighbors);
                }
            }
        }
    }

    public boolean bfsTarget(Graph graph, int start, int target) {
        if (graph == null)
            throw new NullPointerException("Null graph, try again");
        boolean[] visited = new boolean[graph.numberOfNodes()];

        if (start > visited.length || start < 0)
            throw new NullPointerException("Invalid start node, try another number");

        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int current = q.poll();
            if (current == target)
                return true;
            for (int neighbors : graph.getAdj(current)) {
                if (!visited[neighbors]) {
                    visited[neighbors] = true;
                    q.add(neighbors);
                }
            }
        }
        return false;
    }
}