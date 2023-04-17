import java.util.ArrayList;
import java.util.Random;

public class Graph{
    private int numNodes;
    ArrayList<ArrayList<Integer>> graph;
    Random rand;

    public Graph(int numNodes){
        this.numNodes = numNodes;
        this.graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numNodes; i++)
        {
            this.graph.add(new ArrayList<Integer>());
        }
        rand = new Random();
    }

    public int getNumNodes(){return this.numNodes;}

    public void addEdge(int u, int v){
        boolean skip = false;
        for (Integer node: this.graph.get(u)){
            if (node == v){
                skip = true;
            }
        }
        if (!skip){
            this.graph.get(u).add(v);
        }
    }

    public void removeEdge(int u, int v){
        if (this.graph.get(u).contains(v)){
            for (int i = 0; i < this.graph.get(u).size(); i++)
            {
                if (this.graph.get(u).get(i) == v){
                    this.graph.get(u).remove(i);
                }
            }
        }
    }

    public boolean hasEdge(int u, int v){
        return this.graph.get(u).contains(v);
    }
public List<Integer> getPath(int start, int end) {
    List<Integer> path = new ArrayList<>();
    boolean[] visited = new boolean[this.numNodes];
    dfs(start, end, visited, path);
    return path;
}

private boolean dfs(int node, int end, boolean[] visited, List<Integer> path) {
    if (node == end) {
        path.add(node);
        return true;
    }
    visited[node] = true;
    for (int neighbor : this.graph.get(node)) {
        if (!visited[neighbor]) {
            if (dfs(neighbor, end, visited, path)) {
                path.add(node);
                return true;
            }
        }
    }
    return false;
}
    public Graph getSpanningTree(int start){
        Graph spanningTree = new Graph(this.numNodes);
        ArrayList<Integer> stack = new ArrayList<Integer>();
        stack.add(start);
        boolean []visited = new boolean[this.numNodes];
        while (stack.size() > 0){
            int node = stack.get(stack.size()-1);
            ArrayList<Integer> adjacent = this.graph.get(node);
            ArrayList<Integer> unvisitedNeighbores = new ArrayList<Integer>();

            for(Integer n: this.graph.get(node)){
                if (!visited[n]){
                    unvisitedNeighbores.add(n);
                }
            }
            if (unvisitedNeighbores.size() > 0){
                int index = rand.nextInt(unvisitedNeighbores.size());
                int next = unvisitedNeighbores.get(index);
                stack.add(next);
                visited[next] = true;
                spanningTree.addEdge(node, next);
                spanningTree.addEdge(next, node);
            }
            // if a node does not have any unvisited neighbores, we can remove it from the stack
            else{
                stack.remove(stack.size()-1);
            }
        }
        return spanningTree;
    }
    
}