import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
//Storing the netflix data as graph
public class GraphMake {

    public static Multimap<Integer,Integer> netflix_data_extraction() {
        String[] files = {"ratings_data_1.txt","ratings_data_2.txt","ratings_data_3.txt","ratings_data_4.txt"};
        Multimap<Integer, Integer> customer_ids = ArrayListMultimap.create();
        int movie_number = 0;
        for (String file_name : files) {
            try {
                Scanner scanner = new Scanner(new File(file_name));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains(",")) {
                        String[] data = line.split(",");
                        customer_ids.put(Integer.parseInt(data[0]), movie_number);
                    } else {
                        movie_number = movie_number + 1;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return customer_ids;
    }
    //Adjacency criteria used is two users have rated atleast one movie in common
    public static List<Edge> adjacency_criteria1(Multimap<Integer,Integer> customer_ids){
        List<Edge> edges = new ArrayList<>();
        int i = 0;
        for (Integer outer_key : customer_ids.keySet()){
            int j = 0;
            for(Integer inner_key : customer_ids.keySet()){
                if(outer_key != inner_key){
                    HashSet<Integer> intersection = new HashSet<>(customer_ids.get(outer_key));
                    intersection.retainAll(customer_ids.get(inner_key));
                    if(intersection.size() >= 1){
                        edges.add(new Edge(i,j,1));
                    }
                }
                j++;
            }
            i++;

        }
        return edges;
    }

    //Adjacency criteria used is two users have rated atleast 5 movie in common
    public static List<Edge> adjacency_criteria2(Multimap<Integer,Integer> customer_ids){
        List<Edge> edges = new ArrayList<>();
        int i = 0;
        for (Integer outer_key : customer_ids.keySet()){
            int j = 0;
            for(Integer inner_key : customer_ids.keySet()){
                if(outer_key != inner_key){
                    HashSet<Integer> intersection = new HashSet<>(customer_ids.get(outer_key));
                    intersection.retainAll(customer_ids.get(inner_key));
                    if(intersection.size() >= 1){
                        edges.add(new Edge(i,j,1));
                    }
                }
                j++;
            }
            i++;

        }
        return edges;
    }
}
