package graphs;
import searchAlgorithms.*;

public class Main {
    public static void main(String[] args) {
        AdjList g = new AdjList(5);

        //Dfs g1 = new Dfs(8);

        Bfs bf = new Bfs(g, 0);
        g.addEdge(0, 1,1);
        g.addEdge(0, 4,1);
        g.addEdge(1, 2,1);
        g.addEdge(1, 3,1);
        g.addEdge(4, 3,1);
         g.ShowGrafo();
        System.out.println(g.hasEdge(1, 3));
        System.out.println(g.hasEdge(0, 2));
        System.out.println("Degree node 1: " + g.getDegree(1));
//        System.out.println(bf.runBfs(3));
//        System.out.println(bf.runBfs(5));

        System.out.println();
//        g1.addEdge(0, 1);
//        g1.addEdge(0, 2);
//        g1.addEdge(1, 3);
//        g1.addEdge(3, 4);
//        g1.addEdge(3, 6);
//        g1.addEdge(2, 5);
//        g1.addEdge(5, 7);
//
//        g1.ShowGrafo();
        //System.out.println(g1.dfs(0));


    }
}