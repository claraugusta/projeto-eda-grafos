package graphs;

public class Main {
    public static void main(String[] args) {
        AdjList g = new AdjList(5); 

        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(4, 3);
        g.ShowGrafo();
        System.out.println();
        // g.removeEdge(2, 3);
        g.ShowGrafo();
        System.out.println(g.hasEdge(1, 3)); 
        System.out.println(g.hasEdge(1, 2)); 
        System.out.println("Degree node 1: " + g.getDegree(1));
        System.out.println(g.bfs(0));


    }
}
