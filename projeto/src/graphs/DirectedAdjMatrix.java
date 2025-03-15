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

    public boolean addEdge(int nodeIn, int nodeOut, int weigth){
        int c = getNodeIndex(nodeOut);
        int l = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return false;
        this.adjMatrix[l][c] = weigth;
        return true;
    }

    public int getWeight(int nodeOut, int nodeIn){
        int c = getNodeIndex(nodeOut);
        int l = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return nullEdgeValue;
        return this.adjMatrix[c][l];
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
    @Override
    public String toString(){
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

}
