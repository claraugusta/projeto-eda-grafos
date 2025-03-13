package shortestPath;

import graphs.*;

public class FloydWarshall {
    static final int INF = Integer.MAX_VALUE;

    public static int[][] floydWarshall(Graph graph) {
        int V = graph.size();
        int[][] dist = new int[V][V];
    
        // Inicializa a matriz de distâncias com os valores do grafo
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                int weight = graph.getWeight(i, j);
                if (i == j) {
                    dist[i][j] = 0; 
                } else if (weight != INF) {
                    dist[i][j] = weight; 
                } else {
                    dist[i][j] = INF;  
                }
            }
        }
    
        // Algoritmo de Floyd-Warshall
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // Verifica se a distância de i a j pode ser reduzida através do nó k
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];  
                        System.out.println("Atualizando dist[" + i + "][" + j + "] para " + dist[i][j]);
                    }
                }
            }
        }
        
        // // Verificação de ciclos negativos 
        // for (int i = 0; i < V; i++) {
        //     if (dist[i][i] < 0) {
        //         System.out.println("Ciclo negativo detectado!");
        //         return null; // Ou lançar uma exceção ou retornar algum valor especial
        //     }
        // }
        
        return dist;
    }

    public static void main(String[] args) {
        Graph graph = new DirectedAdjMatrix(5, INF);

        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);

        // graph.addEdge(1, 4, 5);  // Aresta de 1 para 4 com peso 5
        // graph.addEdge(1, 2, 3);  // Aresta de 1 para 2 com peso 3
        // graph.addEdge(2, 1, 2);  // Aresta de 2 para 1 com peso 2
        // graph.addEdge(4, 3, 2);  // Aresta de 4 para 3 com peso 2
        // graph.addEdge(3, 2, 6);  // Aresta de 3 para 2 com peso 6
        // graph.addEdge(2, 4, 4);  // Aresta de 2 para 4 com peso 4

       // Se tiver arestas negativas, pode adicionar mais aqui

        graph.addEdge(0, 1, 10);  // Aresta de 0 para 1 com peso 10
        graph.addEdge(1, 2, 5);   // Aresta de 1 para 2 com peso 5
        graph.addEdge(2, 3, 2);   // Aresta de 2 para 3 com peso 2
        graph.addEdge(3, 4, 3);   // Aresta de 3 para 4 com peso 3
        graph.addEdge(4, 0, 7);  

        int[][] dist = floydWarshall(graph);
        
        if (dist != null) {
            // Exibindo a matriz de distâncias mínimas
            System.out.println("Matriz de Menores Distâncias:");
            for (int i = 0; i < dist.length; i++) {
                for (int j = 0; j < dist[i].length; j++) {
                    System.out.print((dist[i][j] == INF ? "INF" : dist[i][j]) + "\t");
                }
                System.out.println();
            }
        }
    }
}
