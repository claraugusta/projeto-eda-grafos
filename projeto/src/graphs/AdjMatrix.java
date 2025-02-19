package graphs;

import java.util.Arrays;

//grafo nao direcionado e ponderado
public class AdjMatrix {

    private int nullEdgeValue;
    private int maxNodes;
    private int qtdNodes;
    private int[] nodes;
    private int[][] adjacencyMatrix;

    public AdjMatrix(int maxNodes, int nullEdgeValue){
        this.maxNodes = maxNodes;
        this.nullEdgeValue = nullEdgeValue;
        this.qtdNodes = 0;
        this.nodes = new int[maxNodes];
        this.adjacencyMatrix = new int[this.maxNodes][this.maxNodes];
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

    public boolean addEdge(int nodeIn, int nodeOut, int weigth){
        int l = getNodeIndex(nodeOut);
        int c = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return false;
        //funciona pra grafos nao direcionados
        this.adjacencyMatrix[l][c] = weigth;
        this.adjacencyMatrix[c][l] = weigth;
        return true;
    }

    public int getWeight(int nodeOut, int nodeIn){
        int l = getNodeIndex(nodeOut);
        int c = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return -1;
        return this.adjacencyMatrix[l][c];
    }

    public int getDegree(int node){
        int l = getNodeIndex(node);
        if(l == -1)
            return -1;
        int degree = 0;
        for (int i = 0; i < maxNodes; i++) {
            if(this.adjacencyMatrix[l][i] != this.nullEdgeValue)
                degree++;
        }
        return degree;
    }

    public String MatrixToString(){
        String out = "";
        for (int i = 0; i < this.maxNodes; i++) {
            String l = "";
            for (int j = 0; j < this.maxNodes; j++) {
                l += this.adjacencyMatrix[i][j] + " ";
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

}
