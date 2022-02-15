import com.google.common.collect.Multimap;
import java.util.List;
//Testing the graph operations on netflix data
public class RealTest {
    public static void main(String[] args)
    {
        Multimap<Integer,Integer> customer_ids = GraphMake.netflix_data_extraction();
        int N = customer_ids.size();
        List<Edge> edges = GraphMake.adjacency_criteria2(customer_ids);
        Graph graph = new Graph(edges, N);
        System.out.println("Following are the Connected Graph Components:");
        GraphOperations.connected_components(graph, N);
    }
}
