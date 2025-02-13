package adjacencyMatrix;

import java.util.Arrays;

//grafo nao direcionado e ponderado
public class Graph {

    private int maxNodes;
    private int qtdNodes;
    private int[] nodes;
    private static int[][] adjacencyMatrix;

    public static void main(String[] args) {
        new Graph(10);
        System.out.println(Arrays.toString(adjacencyMatrix[0]));
    }

    public Graph(int maxNodes){
        this.maxNodes = maxNodes;
        qtdNodes = 0;
        nodes = new int[maxNodes];
        adjacencyMatrix = new int[this.maxNodes][this.maxNodes];
    }

    public int getNodeIndex(int node){
        for (int i = 0; i < nodes.length; i++) {
            if(node == nodes[i])
                return i;
        }
        return -1;
    }

    public boolean isFull(){
        return qtdNodes == maxNodes;
    }

    public boolean addNode(int node){
        if(isFull())
            return false;
        nodes[qtdNodes] = node;
        qtdNodes++;
        return true;
    }

    public boolean addEdge(int nodeIn, int nodeOut, int weigth){
        int l = getNodeIndex(nodeOut);
        int c = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return false;
        //funciona pra grafos nao direcionados
        adjacencyMatrix[l][c] = weigth;
        adjacencyMatrix[c][l] = weigth;
        return true;
    }

    public int getWeight(int nodeOut, int nodeIn){
        int l = getNodeIndex(nodeOut);
        int c = getNodeIndex(nodeIn);
        if(l == -1 || c == -1)
            return -1;
        return adjacencyMatrix[l][c];
    }

    public int getDegree(int node){
        int l = getNodeIndex(node);
        if(l == -1)
            return -1;
        int degree = 0;
        for (int i = 0; i < maxNodes; i++) {
            if(adjacencyMatrix[l][i] != 0)
                degree++;
        }
        return degree;
    }

    public String MatrixToString(){
        String out = "";
        for (int i = 0; i < maxNodes; i++) {
            String l = "";
            for (int j = 0; j < maxNodes; j++) {
                l += adjacencyMatrix[i][j] + " ";
            }
            out += l + "\n";
        }
        return out;
    }

    public String nodesToString(){
        String out = "";
        for (int i = 0; i < maxNodes; i++) {
            out += "pos: " + i + ", node: " + nodes[i] + "\n";
        }
        return out;
    }
}
