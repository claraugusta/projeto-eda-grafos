package searchAlgorithms;
import java.util.*;

public class DFS {
    private int nodes;
    private List<List<Integer>> adj;

    public DFS(int nodes) {
        this.nodes = nodes;
        adj = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public void ShowGrafo() {
        for (int i = 0; i < nodes; i++) {
            System.out.print("Node " + i + " -> ");
            for (Integer lado : adj.get(i)) {
                System.out.print(lado + " ");
            }
            System.out.println();
        }
    }
    public void removeEdge(int u, int v) {
        adj.get(u).remove(Integer.valueOf(v));
        adj.get(v).remove(Integer.valueOf(u));
    }

    public String dfs(int node) {
        String seq = "";
        boolean[] visited = new boolean[nodes];
        Stack<Integer> stack = new Stack<Integer>();

        visited[node] = true;
        stack.push(node);

        while (!stack.empty()) {
            int v = stack.peek();
            seq += v + " ";
            stack.pop();
            visited[v] = true;
            for (int nextNode : adj.get(v)) {
                if(!visited[nextNode]) {
                    stack.push(nextNode);
                }
            }
        }
        return seq;
    }
}
