package benchmark;

import graphs.*;

import java.util.Random;

public class GenerateGraphs {
    private Random rand = new Random();
    
    public  Graph generateAdjList(int size, double density) {
        Graph graph = new AdjList(size);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density)
                    graph.addEdge(i, j, 1);
            }
        }
        return graph;
    }

    public  Graph generateAdjMatrix(int size, int nullEdgeValue, double density){
        Graph graph = new AdjMatrix(size, nullEdgeValue);
        Random rand = new Random();
        for (int k = 0; k < size; k++)
            graph.addNode(k);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density)
                    graph.addEdge(i, j, 1);
            }
        }
        return graph;
    }

    public Graph generateAdjMatrixWeight(int size, int nullEdgeValue, double density, int maxWeight){
        Graph graph = new AdjMatrix(size, nullEdgeValue);
        Random rand = new Random();
        for (int k = 0; k < size; k++)
            graph.addNode(k);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density){
                    int weight = rand.nextInt(maxWeight) + 1;
                    graph.addEdge(i, j, weight);
                }
            }
        }
        return graph;
    }

    public  Graph generateDirectedAdjMatrix(int size, int nullEdgeValue, double density) {
        Graph graph = new DirectedAdjMatrix(size, nullEdgeValue);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j && rand.nextDouble() < density)
                    graph.addEdge(i, j, 1);
            }
        }
        return graph;
    }

    public  Graph generateDirectedAdjMatrixNegativeEdges(
            int size, int nullEdgeValue, double density, double negativeProb, int maxWeight) {

        Graph graph = new DirectedAdjMatrix(size, nullEdgeValue);

        for (int i = 0; i < size; i++)
            graph.addNode(i);

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j && rand.nextDouble() < density) {
                    int weight = rand.nextInt(maxWeight) + 1;
                    if (rand.nextDouble() < negativeProb)
                        weight = -weight;
                    graph.addEdge(i, j, weight);
                }
            }
        }
        return graph;
    }

    public  Graph generateTreeDirectedAdjMatrix(int size, int nullEdgeValue) {
        Graph graph = new DirectedAdjMatrix(size, nullEdgeValue);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 1; i < size; i++) {
            int parent = rand.nextInt(i);
            graph.addEdge(parent, i, 1);
        }
        return graph;
    }

    public  Graph generateAdjMatrix(int size, int nullEdgeValue) {
        Graph graph = new AdjMatrix(size, nullEdgeValue);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 1; i < size; i++) {
            int parent = rand.nextInt(i);
            graph.addEdge(parent, i, 1);
        }
        return graph;
    }

    public  Graph generateTreeAdjMatrix(int size, int nullEdgeValue, int maxWeight) {
        Graph graph = new AdjMatrix(size, nullEdgeValue);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 1; i < size; i++) {
            int parent = rand.nextInt(i);
            int weight = rand.nextInt(maxWeight) + 1;
            graph.addEdge(parent, i, weight);
        }
        return graph;
    }

    public  Graph generateTreeAdjList(int size, int nullEdgeValue, int maxWeight) {
        Graph graph = new AdjList(size);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 1; i < size; i++) {
            int parent = rand.nextInt(i);
            int weight = rand.nextInt(maxWeight) + 1;
            graph.addEdge(parent, i, weight);
        }
        return graph;
    }

    public Graph generateTreeAdjList(int size, int nullEdgeValue) {
        Graph graph = new AdjList(size);
        for (int i = 0; i < size; i++)
            graph.addNode(i);
        Random rand = new Random();
        for (int i = 1; i < size; i++) {
            int parent = rand.nextInt(i);
            graph.addEdge(parent, i, 1);
        }
        return graph;
    }
    public Graph generateGridGraph(int rows, int cols) {
        int size = rows * cols;
        Graph graph = new AdjList(size);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int node = i * cols + j;
                if (j < cols - 1) graph.addEdge(node, node + 1, 1);
                if (i < rows - 1) graph.addEdge(node, node + cols, 1);
            }
        }
        return graph;
    }

    public  Graph generateDeepTreeGraph(int depth) {
        int size = depth * 2 - 1;
        Graph graph = new AdjList(size);

        for (int i = 0; i < depth - 1; i++) {
            graph.addEdge(i, i + 1, 1);
        }

        for (int i = depth; i < size; i++) {
            graph.addEdge(0, i, 1);
        }

        return graph;
    }

    public Graph generateDenseGraph(int size, double density) {
        Graph graph = new AdjList(size);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (rand.nextDouble() < density) {
                    graph.addEdge(i, j, 1);
                }
            }
        }
        return graph;
    }
    
    
}

