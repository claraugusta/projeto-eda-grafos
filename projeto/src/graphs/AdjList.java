package graphs;
import java.util.*;

public class AdjList implements Graph{

    public int nodes;
    public List<List<Integer>> adjList;

    public AdjList(int nodes) {
        this.nodes = nodes;
        adjList = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    @Override
    public int size() {
        return this.adjList.size();
    }

    @Override
    public boolean addNode(int node) {
        if (!adjList.contains(node)) {
            adjList.add(node, new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(int u, int v, int weight) {
        // Para um grafo não ponderado, ignoramos o peso
        return addEdge(u, v);
    }

    // para nao direcionado

    public boolean addEdge(int u, int v) {
        if(u < nodes && v < nodes){
            adjList.get(u).add(v);
            adjList.get(v).add(u);
            return true;
        }
        return false;
    }

    public void ShowGrafo() {
        for (int i = 0; i < nodes; i++) {
            System.out.print("Node " + i + " -> ");
            for (Integer lado : adjList.get(i)) {
                System.out.print(lado + " ");
            }
            System.out.println();
        }
    }
    public List<Integer> getAdj(int node){
        return this.adjList.get(node);
    }

    public void removeEdge(int u, int v) {
        adjList.get(u).remove(Integer.valueOf(v));
        adjList.get(v).remove(Integer.valueOf(u));
    }

    public boolean hasEdge(int u, int v) {
        return adjList.get(u).contains(v);
    }

    @Override
    public int getWeight(int u, int v) {
        return 1;
    }

    public int getDegree(int node) {
        return adjList.get(node).size();
    }
    public int getNodes() {
        return nodes;
    }

}
