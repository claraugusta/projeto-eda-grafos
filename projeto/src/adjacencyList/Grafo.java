import java.util.ArrayList;
import java.util.List;

class Grafo {
    private int nodes; 
    private List<List<Integer>> listaAdj; 

    public Grafo(int nodes) {
        this.nodes = nodes;
        listaAdj = new ArrayList<>();
        
        for (int i = 0; i < nodes; i++) {
            listaAdj.add(new ArrayList<>());
        }
    }

    //para nao direcionado
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
}