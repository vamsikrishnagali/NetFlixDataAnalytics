import java.util.ArrayList;
import java.util.List;

//Graph class represents the Graph object
class Graph
{
    List<List<Edge>> adjList = null;
    //Parameterised Constructor
    Graph(List<Edge> edges, int N)
    {
        //Creating ArrayList called adjList
        adjList = new ArrayList<>();

        //Creating new Arraylist to every node in the OuterList
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }
        //Adding a list of edges to every node
        for (Edge edge: edges) {
            //adding same edges in both source and destination nodes for undirected graph representation
            adjList.get(edge.source).add(edge);
            Edge reverse = new Edge(edge.dest, edge.source, edge.weight);
            adjList.get(edge.dest).add(reverse);
        }
    }
}