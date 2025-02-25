package searchAlgorithms;
import java.util.*;
import graphs.AdjList;

public class Bfs{
    private AdjList graph;
    private int start;
    private boolean[] visitado;

    public Bfs(AdjList adjlist, int nodeStart){
        this.graph = adjlist;
        this.start = nodeStart;
        this.visitado = new boolean[graph.getNodes()];
    }
    public Bfs(AdjList adjlist){
        this.graph = adjlist;
        this.start = 0;
        this.visitado = new boolean[graph.getNodes()];
    }
        
    public boolean runBfs(int target) {
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

            for (int vizinho : this.graph.getAdj(atual)) {
                if (!this.visitado[vizinho]) {
                    this.visitado[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }
        return false;
        }
    }
