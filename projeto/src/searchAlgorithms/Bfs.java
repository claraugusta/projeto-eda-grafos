package searchAlgorithms;
import java.util.*;
import graphs.*;

public class Bfs{
    private Graph graph;
    private int start;
    private boolean[] visitado;

    public Bfs(Graph graph, int nodeStart){
        this.graph = graph;
        this.start = nodeStart;
        this.visitado = new boolean[this.graph.getNodes()];
    }
    public Bfs(Graph graph){
        this.graph = graph;
        this.start = 0;
        this.visitado = new boolean[this.graph.getNodes()];
    }
        
    public boolean bfs() {
        if (this.graph == null || this.visitado == null) throw new NullPointerException("Null graph, try again");
        if (this.start > this.visitado.length || this.start < 0) throw new NullPointerException("Invalid start node, try another number");
        
        Queue<Integer> fila = new LinkedList<>();

        this.visitado[this.start] = true;
        fila.add(this.start);

        while (!fila.isEmpty()) {
            int atual = fila.poll();

            if(this.graph instanceof AdjList){
                for (int vizinho : ((AdjList)this.graph).getAdj(atual)) {
                    if (!this.visitado[vizinho]) {
                        this.visitado[vizinho] = true;
                        fila.add(vizinho);
                    }
                }
            } else if (this.graph instanceof AdjMatrix) {
                for (int vizinho : ((AdjMatrix)this.graph).getAdj(atual)) {
                    if (!this.visitado[vizinho]) {
                        this.visitado[vizinho] = true;
                        fila.add(vizinho);
                    }
                }
            }
        }
        return false;
        }

        public boolean bfsTarget(int target) {
            if (this.graph == null || this.visitado == null) throw new NullPointerException("Null graph, try again");
            if (this.start > this.visitado.length || this.start < 0) throw new NullPointerException("Invalid start node, try another number");
            
            Queue<Integer> fila = new LinkedList<>();
    
            this.visitado[this.start] = true;
            fila.add(this.start);
    
            while (!fila.isEmpty()) {
                int atual = fila.poll();
                if (atual==target) {
                    return true;
                }
    
                if(this.graph instanceof AdjList){
                    for (int vizinho : ((AdjList)this.graph).getAdj(atual)) {
                        if (!this.visitado[vizinho]) {
                            this.visitado[vizinho] = true;
                            fila.add(vizinho);
                        }
                    }
                } else if (this.graph instanceof AdjMatrix) {
                    for (int vizinho : ((AdjMatrix)this.graph).getAdj(atual)) {
                        if (!this.visitado[vizinho]) {
                            this.visitado[vizinho] = true;
                            fila.add(vizinho);
                        }
                    }
                }
            }
            return false;
            }
    }
