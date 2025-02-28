package searchAlgorithms;
import java.util.*;
import graphs.*;

public class Dfs{
    private Graph graph;
    private int start;
    private boolean[] visitado;

    public Dfs(Graph graph, int nodeStart){
        this.graph = graph;
        this.start = nodeStart;
        this.visitado = new boolean[this.graph.getNodes()];
    }
    public Dfs(Graph graph){
        this.graph = graph;
        this.start = 0;
        this.visitado = new boolean[this.graph.getNodes()];
    }
        
    public boolean runDfstarget(int target) {
        if (this.graph == null || this.visitado == null) throw new NullPointerException("Null graph, try again");
        if (this.start > this.visitado.length || this.start < 0) throw new NullPointerException("Invalid start node, try another number");
        
        Stack<Integer> pilha = new Stack<Integer>();

        this.visitado[this.start] = true;
        pilha.push(this.start);

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
                for (int vizinho : ((AdjMatrix)this.graph).getAdj(atual)) {
                    if (!this.visitado[vizinho]) {
                        this.visitado[vizinho] = true;
                        pilha.push(vizinho);
                    }
                }
            }
        }
        return false;
        }
        public boolean runDfstarget() {
            if (this.graph == null || this.visitado == null) throw new NullPointerException("Null graph, try again");
            if (this.start > this.visitado.length || this.start < 0) throw new NullPointerException("Invalid start node, try another number");
            
            Stack<Integer> pilha = new Stack<Integer>();
    
            this.visitado[this.start] = true;
            pilha.push(this.start);
    
            while (!pilha.isEmpty()) {
                int atual = pilha.pop();
    
                if(this.graph instanceof AdjList){
                    for (int vizinho : ((AdjList)this.graph).getAdj(atual)) {
                        if (!this.visitado[vizinho]) {
                            this.visitado[vizinho] = true;
                            pilha.push(vizinho);
                        }
                    }
                } else if (this.graph instanceof AdjMatrix) {
                    for (int vizinho : ((AdjMatrix)this.graph).getAdj(atual)) {
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
