package searchAlgorithms;
import java.util.*;
import graphs.*;

public class Dfs{
    private Graph graph;
    private boolean[] visitado;

    public Dfs(Graph graph){
        this.graph = graph;
        this.visitado = new boolean[this.graph.getNodes()];
    }

    public void dfs(int start) {
        if (this.graph == null || this.visitado == null) throw new NullPointerException("Null graph, try again");
        if (start > this.visitado.length || start < 0) throw new NullPointerException("Invalid start node, try another number");
        System.out.println(graph.toString());
        Stack<Integer> pilha = new Stack<Integer>();
        this.visitado[start] = true;
        pilha.push(start);
        while (!pilha.isEmpty()) {
            int current = pilha.pop();
            if(this.graph instanceof AdjList){
                for (int neighbors : ((AdjList)this.graph).getAdj(current)) {
                    if (!this.visitado[neighbors]) {
                        this.visitado[neighbors] = true;
                        pilha.push(neighbors);
                    }
                }
            } else if (this.graph instanceof AdjMatrix) {
                for (int vizinho : ((AdjMatrix)this.graph).getAdjNodes(current)) {
                    if (vizinho != 0 && !this.visitado[vizinho]) {
                        this.visitado[vizinho] = true;
                        pilha.push(vizinho);
                    }
                }
            }
        }
    }

    public boolean dfsTarget(int start, int target) {
        if (this.graph == null || this.visitado == null) throw new NullPointerException("Null graph, try again");
        if (start > this.visitado.length || start < 0) throw new NullPointerException("Invalid start node, try another number");

        Stack<Integer> pilha = new Stack<Integer>();

        this.visitado[start] = true;
        pilha.push(start);

        while (!pilha.isEmpty()) {
            int atual = pilha.pop();
            if (atual==target) {
                return true;
            }

            if(this.graph instanceof AdjList){
                for (int vizinho : ((AdjList)this.graph).getAdj(atual)) {
                    if (!this.visitado[vizinho]) {
                        this.visitado[vizinho] = true;
                        pilha.push(vizinho);
                    }
                }
            } else if (this.graph instanceof AdjMatrix) {
                for (int vizinho : ((AdjMatrix)this.graph).getAdjNodes(atual)) {
                    if (!this.visitado[vizinho]) {
                        this.visitado[vizinho] = true;
                        pilha.push(vizinho);
                    }
                }
            }
        }
        return false;
    }

}