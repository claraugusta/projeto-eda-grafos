package searchAlgorithms;
import java.util.*;
import graphs.*;

public class Bfs{
    private Graph graph;
    private int start;
    private boolean[] visited;

    public Bfs(Graph graph, int nodeStart){
        this.graph = graph;
        this.start = nodeStart;
        this.visited = new boolean[this.graph.getNodes()];
    }
    public Bfs(Graph graph){
        this.graph = graph;
        this.start = 0;
        this.visited = new boolean[this.graph.getNodes()];
    }
        
    public boolean bfs() {
        if (this.graph == null || this.visited == null) throw new NullPointerException("Null graph, try again");
        if (this.start > this.visited.length || this.start < 0) throw new NullPointerException("Invalid start node, try another number");
        
        Queue<Integer> q = new LinkedList<>();

        this.visited[this.start] = true;
        q.add(this.start);

        while (!q.isEmpty()) {
            int current = q.poll();

            if(this.graph instanceof AdjList){
                for (int neighbors : ((AdjList)this.graph).getAdj(current)) {
                    if (!this.visited[neighbors]) {
                        this.visited[neighbors] = true;
                        q.add(neighbors);
                    }
                }
            } else if (this.graph instanceof AdjMatrix) {
                for (int neighbors : ((AdjMatrix)this.graph).getAdjNodes(current)) {
                    if (neighbors != 0 && !this.visited[neighbors]) {
                        this.visited[neighbors] = true;
                        q.add(neighbors);
                    }
                }
            }
        }
        return false;
        }

        public boolean bfsTarget(int target) {
            if (this.graph == null || this.visited == null) throw new NullPointerException("Null graph, try again");
            if (this.start > this.visited.length || this.start < 0) throw new NullPointerException("Invalid start node, try another number");
            
            Queue<Integer> q = new LinkedList<>();
    
            this.visited[this.start] = true;
            q.add(this.start);
    
            while (!q.isEmpty()) {
                int current = q.poll();
                if (current==target) {
                    return true;
                }
    
                if(this.graph instanceof AdjList){
                    for (int neighbors : ((AdjList)this.graph).getAdj(current)) {
                        if (!this.visited[neighbors]) {
                            this.visited[neighbors] = true;
                            q.add(neighbors);
                        }
                    }
                } else if (this.graph instanceof AdjMatrix) {
                    for (int neighbors : ((AdjMatrix)this.graph).getAdjNodes(current)) {
                        if (!this.visited[neighbors]) {
                            this.visited[neighbors] = true;
                            q.add(neighbors);
                        }
                    }
                }
            }
            return false;
            }
    }
