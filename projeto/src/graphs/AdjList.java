package graphs;
import java.util.*;

public class AdjList {
    public int nodes;
    public List<List<Integer>> listaAdj;

    public AdjList(int nodes) {
        this.nodes = nodes;
        listaAdj = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            listaAdj.add(new ArrayList<>());
        }
    }

    // para nao direcionado
    public void addEdge(int u, int v) {
        listaAdj.get(u).add(v);
        listaAdj.get(v).add(u);
    }

    public void ShowGrafo() {
        for (int i = 0; i < nodes; i++) {
            System.out.print("Node " + i + " -> ");
            for (Integer lado : listaAdj.get(i)) {
                System.out.print(lado + " ");
            }
            System.out.println();
        }
    }

    public void removeEdge(int u, int v) {
        listaAdj.get(u).remove(Integer.valueOf(v));
        listaAdj.get(v).remove(Integer.valueOf(u));
    }

    public boolean hasEdge(int u, int v) {
        return listaAdj.get(u).contains(v);
    }

    public int getDegree(int node) {
        return listaAdj.get(node).size();
    }

    public String bfs(int start) {
        String seq = "";
        boolean[] visitado = new boolean[nodes];
        Queue<Integer> fila = new LinkedList<>();

        visitado[start] = true;
        fila.add(start);

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            seq += atual + " ";

            for (int vizinho : listaAdj.get(atual)) {
                if (!visitado[vizinho]) {
                    visitado[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }
        return seq;
    }
}
