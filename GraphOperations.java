import java.util.*;

public class GraphOperations {
    //Performing Depth First Search for an element v
    public static void DFS(int v, boolean[] seen, Graph graph)
    {
        //Making seen for v true
        seen[v] = true;
        //Print the connectedComponents
        System.out.print(v + " ");
        //Fetching the Edgelist for this node
        for (Edge edge : graph.adjList.get(v)) {
            //Checking if the destination for each edge from v is seen or not
            if (!seen[edge.dest])
                //If the destination is not seen then recursively call DFS till all nodes from v are seen
                DFS(edge.dest, seen, graph);
        }
    }

    //Connected Components of Graph
    public static void connected_components(Graph graph, int N)
    {
        //creating a boolean array,seen of size N

        boolean[] seen = new boolean[N];
        //To find and print all connected components
        for (int v = 0; v < N; ++v) {
            if (!seen[v]) {
                DFS(v, seen, graph);
                System.out.println();
            }
        }
    }

    //printGraph print the graph in the format souce -> Destinations
    static void printGraph(Graph g, int N)
    {
        for (int i = 0; i < N; i++) {
            System.out.println("\nAdjacency list of vertex" + i);
            System.out.print("head");
            for (Edge edge : g.adjList.get(i)) {
                System.out.print(" -> "+ edge.dest);
            }
            System.out.println();
        }
    }
    //Function one_cycle to return List of nodes in a cycle if cycle is present
    public static List<Integer> one_cycle(Graph graph, int N) {
        boolean[] visited = new boolean[N];
        //Creating a ArrayList called cycle
        List<Integer> cycle = new ArrayList<>();
        //Return a cycle in the form of the List
        for (int i = 0; i <N ; i++) {
            cycle = new ArrayList<>();
            if(visited[i]==false){
                if(CycleUntil(i, visited, -1, cycle, graph)){
                    return cycle;
                }
            }
        }
        return cycle;
    }

    public static boolean CycleUntil(int currVertex, boolean [] visited, int parent ,List<Integer> stack, Graph graph){
        //Make currVertex as visited
        visited[currVertex] = true;
        //Fetching the edges associated with the currVertex
        for (Edge edge : graph.adjList.get(currVertex)){
            int vertex = edge.dest;
            if(vertex!=parent) {
                if(visited[vertex]){
                    if(stack.size() < 2 || stack.get(0).intValue() != stack.get(stack.size()-1).intValue()){
                        stack.add(vertex);
                    }
                    return true;
                }
                else{
                    if (CycleUntil(vertex, visited, currVertex, stack, graph)) {
                        if(stack.size() < 2 || stack.get(0).intValue() != stack.get(stack.size()-1).intValue()){
                            stack.add(vertex);
                        }
                        return true;
                    }
                }
            }
        }
        stack.remove(Integer.valueOf(currVertex));
        return false;
    }

    private static void getRoute(int[] prev, int i, List<Integer> route)
    {
        if (i >= 0) {
            getRoute(prev, prev[i], route);
            route.add(i);
        }
    }

    public static void shortest_paths(Graph graph, int source, int N)
    {
        //Creating minHeap PQ and adding source node into it
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        minHeap.add(new Node(source, 0));

        //Creating a Arraylist of N size
        //Setting initial distance to every node as INFINITY
        List<Integer> dist = new ArrayList<>(Collections.nCopies(N, Integer.MAX_VALUE));
        //Setting distance to source itself as 0
        dist.set(source, 0);

		/*Creating a boolean array to keep track of nodes
		for which the shortest distance to node is already found*/
        boolean[] done = new boolean[N];
        done[source] = true;

        //Creating a integer array to store parent of each node
        int[] prev = new int[N];
        prev[source] = -1;


        List<Integer> route = new ArrayList<>();
        //The while loop will run till the minheap has elements
        while (!minHeap.isEmpty())
        {
            //Retrive best vertex
            Node node = minHeap.poll();
            //Get the vertex number of the node
            int u = node.vertex;

            //For every neighbour of u
            for (Edge edge: graph.adjList.get(u))
            {
                //v is the neighbour of u
                int v = edge.dest;
                //storing the edge weight of u-v in weight
                int weight = edge.weight;
                //Checking if the shortest path to v is found and smallest route to v from u
                if (!done[v] && (dist.get(u) + weight) < dist.get(v))
                {
                    //Updating the distance of v by adding weight of u and edge distance between u and v
                    dist.set(v, dist.get(u) + weight);
                    //Updating previous of u
                    prev[v] = u;
                    //adding v to the minheap
                    minHeap.add(new Node(v, dist.get(v)));
                }
            }
			/*Shortest path of u is found so updating done of u
			so as to avoid it from getting picked again*/
            done[u] = true;
        }
        //Print the minimum cost and shortest route to all vertices from the source
        for (int i = 1; i < N; ++i)
        {
            if (i != source && dist.get(i) != Integer.MAX_VALUE) {
                getRoute(prev, i, route);
                System.out.printf("Path (%d -> %d): Minimum Cost = %d, Route = %s\n", source, i, dist.get(i), route);
                route.clear();
            }
        }
    }
}
