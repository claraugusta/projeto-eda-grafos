package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//grafo direcionado e ponderado
public class DirectedAdjMatrix implements Graph{

    private int nullEdgeValue;
    private int maxNodes;
    private int qtdNodes;
    private int[] nodes;
    private int[][] adjMatrix;

    public DirectedAdjMatrix(int maxNodes, int nullEdgeValue){
        this.maxNodes = maxNodes;
        this.nullEdgeValue = nullEdgeValue;
        this.qtdNodes = 0;
        this.nodes = new int[maxNodes];
        this.adjMatrix = new int[this.maxNodes][this.maxNodes];
        for (int i = 0; i < maxNodes; i++) {
            Arrays.fill(this.adjMatrix[i], nullEdgeValue);
        }
    }

    public int getNodeIndex(int node){
        for (int i = 0; i < this.nodes.length; i++) {
            if(node == this.nodes[i])
                return i;
        }
        return -1;
    }

    public boolean isFull(){
        return this.qtdNodes == this.maxNodes;
    }

    public boolean addNode(int node){
        if(isFull())
            return false;
        this.nodes[this.qtdNodes] = node;
        this.qtdNodes++;
        return true;
    }

    public boolean addEdge(int nodeIn, int nodeOut, int weight){
        int l = getNodeIndex(nodeOut);
        int c = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return false;
        this.adjMatrix[l][c] = weight;
        return true;
    }

    public int getWeight(int nodeOut, int nodeIn){
        int c = getNodeIndex(nodeOut);
        int l = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return nullEdgeValue;
        return this.adjMatrix[l][c];
    }

    public int getOutDegree(int node){
        int l = getNodeIndex(node);
        if(l == -1)
            return -1;
        int outDegree = 0;
        for (int i = 0; i < maxNodes; i++) {
            if(this.adjMatrix[l][i] != this.nullEdgeValue)
                outDegree++;
        }
        return outDegree;
    }

    public int getInDegree(int node){
        int c = getNodeIndex(node);
        if(c == -1)
            return -1;
        int inDegree = 0;
        for (int i = 0; i < maxNodes; i++) {
            if(this.adjMatrix[i][c] != this.nullEdgeValue)
                inDegree++;
        }
        return inDegree;
    }
    public String MatrixToString(){
        String out = "";
        for (int i = 0; i < this.maxNodes; i++) {
            String l = "";
            for (int j = 0; j < this.maxNodes; j++) {
                l += this.adjMatrix[i][j] + " ";
            }
            out += l + "\n";
        }
        return out;
    }

    public String nodesToString(){
        String out = "";
        for (int i = 0; i < this.maxNodes; i++) {
            out += "pos: " + i + ", node: " + this.nodes[i] + "\n";
        }
        return out;
    }

    @Override
    public int size() {
        return this.adjMatrix.length;
    }

    public int numberOfNodes(){
        return this.nodes.length;
    }

    public List<Integer> getAdj(int node){
        List<Integer> nodesAdj = new ArrayList<>();
        for(int i = 0; i < this.maxNodes; i++){
            if(this.adjMatrix[node][i] != nullEdgeValue)
                nodesAdj.add(i);
        }
        return nodesAdj;
    }

    public static void main(String[] args) {
        // Criando um grafo com capacidade para 5 nós e valor nulo como 0
        DirectedAdjMatrix graph = new DirectedAdjMatrix(5, 0);

        // Adicionando nós
        graph.addNode(10);
        graph.addNode(20);
        graph.addNode(30);
        graph.addNode(40);
        graph.addNode(50);

        // Adicionando arestas com pesos
        graph.addEdge(10, 20, 5);
        graph.addEdge(10, 30, 2);
        graph.addEdge(20, 40, 8);
        graph.addEdge(30, 50, 3);
        graph.addEdge(40, 50, 7);

        // Exibindo a matriz de adjacência
        System.out.println("Matriz de Adjacência:");
        System.out.println(graph.MatrixToString());

        // Exibindo os nós armazenados
        System.out.println("Nós do Grafo:");
        System.out.println(graph.nodesToString());

        // Testando a recuperação de peso
        System.out.println("Peso da aresta entre 10 e 30: " + graph.getWeight(10, 30));
        System.out.println("Peso da aresta entre 20 e 40: " + graph.getWeight(20, 40));

        // Testando o grau dos nós
        System.out.println("Grau do nó 10: " + graph.getOutDegree(10));
        System.out.println("Grau do nó 50: " + graph.getInDegree(50));
    }

}
