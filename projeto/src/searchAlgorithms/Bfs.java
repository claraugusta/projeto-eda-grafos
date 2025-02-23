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
        
    public String runBfs() {
        if (this.graph == null || this.visitado == null) throw new NullPointerException("Null graph, try again");
        if (this.start > this.visitado.length || this.start < 0) throw new NullPointerException("Invalid start node, try another number");
        
        String seq = "";
        
        Queue<Integer> fila = new LinkedList<>();

        this.visitado[this.start] = true;
        fila.add(this.start);

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            seq += atual + " ";

            for (int vizinho : this.graph.getAdj(atual)) {
                if (!this.visitado[vizinho]) {
                    this.visitado[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }
        return seq;
        }
    }
