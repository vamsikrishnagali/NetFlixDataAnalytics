import java.util.ArrayList;
import java.util.List;

public class GraphSimulator {
    //Creating a list of edges to pass to the graph function to test n_cycle condition
    public static List<Edge> n_cycle(int N){
        List<Edge> edges = new ArrayList<>();
        //Creating a edge with i and j
        //i represents source
        for(int i=0 ; i < N-1;i++){
            //j represents destination
            for(int j=i;j < N;j++){
                //Creating an edge between i and j when following condition satisfies
                //Condition |i-j|=1 and |i-j|=N-1
                if(Math.abs(i-j) == 1 || Math.abs(i-j) == N-1){
                    edges.add(new Edge(i, j, 1));
                }
            }
        }
        return edges;
    }

    //Creating a list of edges to pass to the graph function to test complete_cycle
    public static List<Edge> complete_cycle(int N){
        List<Edge> edges = new ArrayList<>();
        for(int i=0 ; i < N-1;i++){
            for(int j=i;j < N;j++){
                //Creating edge between every unique pair of nodes
                if(i != j ){
                    edges.add(new Edge(i, j, 1));
                }
            }
        }
        return edges;
    }

    //Creating a list of edges to pass to the graph function to test equivalence_cycle
    public static List<Edge> equivalence_cycle(int N, int K){
        List<Edge> edges = new ArrayList<>();
        for(int i = 0 ; i < N-1; i++){
            for(int j = i+1;j < N; j++){
                //Creating an edge if for any k<n , k evenly divides (i-j)
                if((i-j) % K == 0 ){
                    edges.add(new Edge(i, j, 1));
                }
            }
        }
        return edges;
    }
}
