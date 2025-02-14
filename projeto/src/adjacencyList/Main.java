public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo(5); 

        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.ShowGrafo();
        System.out.println();
        g.removeEdge(2, 3);
        g.ShowGrafo();
    }
}