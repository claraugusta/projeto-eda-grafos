package searchAlgorithms;
import java.util.*;

public class DFS extends graphs.AdjList {

    public DFS(int nodes) {
        super(nodes);
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
            for (int nextNode : listaAdj.get(v)) {
                if(!visited[nextNode]) {
                    stack.push(nextNode);
                }
            }
        }
        return seq;
    }
}
