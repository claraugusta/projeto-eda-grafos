package searchAlgorithms;
import java.util.*;
import graphs.*;

public class Dfs {

    public void dfs(Graph graph, int start) {
        if (graph == null) throw new NullPointerException("Null graph, try again");

        boolean[] visited = new boolean[graph.numberOfNodes()];

        if (start > visited.length || start < 0)
            throw new NullPointerException("Invalid start node, try another number");

        Stack<Integer> stack = new Stack<Integer>();
        visited[start] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            for (int neighbors : graph.getAdj(current)) {
                if (!visited[neighbors]) {
                    visited[neighbors] = true;
                    stack.push(neighbors);
                }
            }
        }
    }


    public boolean dfsTarget(Graph graph, int start, int target) {
        if (graph == null) throw new NullPointerException("Null graph, try again");

        boolean[] visited = new boolean[graph.numberOfNodes()];

        if (start > visited.length || start < 0)
            throw new NullPointerException("Invalid start node, try another number");

        Stack<Integer> stack = new Stack<Integer>();
        visited[start] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current == target) {
                return true;
            }
            for (int neighbors : graph.getAdj(current)) {
                if (!visited[neighbors]) {
                    visited[neighbors] = true;
                    stack.push(neighbors);
                }
            }
        }
        return false;
    }

    public String dfsSeq(Graph graph, int start) {
        if (graph == null) throw new NullPointerException("Null graph, try again");

        boolean[] visited = new boolean[graph.numberOfNodes()];

        if (start > visited.length || start < 0)
            throw new NullPointerException("Invalid start node, try another number");
        String out = "";
        Stack<Integer> stack = new Stack<Integer>();
        visited[start] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            out += stack.peek() + " ";
            int current = stack.pop();

            for (int neighbors : graph.getAdj(current)) {
                if (!visited[neighbors]) {
                    visited[neighbors] = true;
                    stack.push(neighbors);
                }
            }
        }
        return out.trim();
    }
}