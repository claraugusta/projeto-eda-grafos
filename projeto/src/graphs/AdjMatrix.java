package graphs;

import java.util.Arrays;
import java.util.List;

//grafo nao direcionado e ponderado
public class AdjMatrix implements Graph{

    private int nullEdgeValue;
    private int maxNodes;
    private int qtdNodes;
    private int[] nodes;
    private int[][] adjMatrix;

    public AdjMatrix(int maxNodes, int nullEdgeValue){
        this.maxNodes = maxNodes;
        this.nullEdgeValue = nullEdgeValue;
        this.qtdNodes = 0;
        this.nodes = new int[maxNodes];
        this.adjMatrix = new int[this.maxNodes][this.maxNodes];
    }

    public AdjMatrix(int maxNodes, int[][] matrix){
        this.maxNodes = maxNodes;
        this.qtdNodes = 0;
        this.nodes = new int[maxNodes];
        this.adjMatrix = matrix;
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

    @Override
    public boolean addNode(int node){
        if(isFull())
            return false;
        this.nodes[this.qtdNodes] = node;
        this.qtdNodes++;
        return true;
    }

    @Override
    public boolean addEdge(int nodeIn, int nodeOut, int weigth){
        int l = getNodeIndex(nodeOut);
        int c = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return false;
        //funciona pra grafos nao direcionados
        this.adjMatrix[l][c] = weigth;
        this.adjMatrix[c][l] = weigth;
        return true;
    }

    public int getNodes(){
        return this.nodes.length;
    }

    public List<Integer> getAdj(int node){
        return Arrays.stream(this.adjMatrix[getNodeIndex(node)]).boxed().toList();
    }

    @Override
    public int getWeight(int nodeOut, int nodeIn){
        int l = getNodeIndex(nodeOut);
        int c = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return -1;
        return this.adjMatrix[l][c];
    }

    public int getDegree(int node){
        int l = getNodeIndex(node);
        if(l == -1)
            return -1;
        int degree = 0;
        for (int i = 0; i < maxNodes; i++) {
            if(this.adjMatrix[l][i] != this.nullEdgeValue)
                degree++;
        }
        return degree;
    }
    public int[][] getAdjacencyMatrix(){
        return this.adjMatrix;
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

    @Override
    public int size() {
        return this.adjMatrix.length;
    }

    @Override
    public String toString(){
        String out = "";
        for (int i = 0; i < this.maxNodes; i++) {
            out += "pos: " + i + ", node: " + this.nodes[i] + "\n";
        }
        return out;
    }

}
