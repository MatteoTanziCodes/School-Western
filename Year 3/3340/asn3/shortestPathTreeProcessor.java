import java.io.*;
import java.util.LinkedList;

class shortestPathTreeProcessor {


    int vertices;
    Graph graph;


    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        shortestPathTreeProcessor instance = new shortestPathTreeProcessor();
        instance.file_reading(input);
        instance.dijkstra(instance.graph, 1);
    }
    public void dijkstra(Graph graph, int source){

        Edge[] d;
        int[] order = new int[vertices];
        int i = 0;
        d = initialize_single_source(source);
        heap Q = new heap(d, vertices);

        while (!Q.isEmpty()){
            int u = Q.min_id();
            Q.delete_min();
            order[i] = u; i++;
            for(Edge e : graph.adjacencylist[u]){
                relax(e,d);
                Q.decrease_key(e.getDestination(), d[e.getDestination()].getWeight());
            }
        }

        print_dijkstra(order,d);

    }
    public void print_dijkstra(int[]order, Edge[]d){
        for (int i = 0; i < vertices - 1; i++) {
            System.out.println("("+d[order[i]].getSource()+", "+ d[order[i]].getDestination()+") : "+d[order[i]].getWeight());
        }
    }
    public void relax(Edge e, Edge[] d){
        if (d[e.getDestination()].getWeight() > d[e.getSource()].getWeight() + e.getWeight()){
            d[e.getDestination()].setWeight(d[e.getSource()].getWeight() + e.getWeight());
        }
    }
    public Edge[] initialize_single_source(int source){
        int INFINITY = Integer.MAX_VALUE;
        Edge[] d = new Edge[vertices];
        for (int i = 2; i < vertices; i++) {
            d[i] = new Edge(source,i, INFINITY);
        }
        d[0] = new Edge(0, 0, 0);
        d[source] = new Edge(source, source, 0);
        return d;
    }

    public void file_reading(BufferedReader  input) throws IOException {

        int vertices = Integer.parseInt(input.readLine()) + 1;
        String line;
        this.vertices = vertices;
        Graph graph = new Graph(vertices);

        while ((line = input.readLine()) != null){
            line = line.trim();
            graph.addEdge(Integer.parseInt(line.split("\\s+")[0]), Integer.parseInt(line.split("\\s+")[1]), Integer.parseInt(line.split("\\s+")[2]));
        }
        this.graph = graph;
        this.graph.print_graph();

    }

}
class Graph{

    int vertices;
    LinkedList<Edge>[] adjacencylist;

    public Graph(int vertices){
        this.vertices = vertices;
        adjacencylist = new LinkedList[vertices];

        //initialize adjacency lists for all the vertices
        for (int i = 0; i <vertices ; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v, int w){
        Edge edge = new Edge(u, v, w);
        adjacencylist[u].addFirst(edge);
    }

    public void print_graph(){
        System.out.println("Total number of vertices: " + (vertices-1));
        for (int i = 1; i < adjacencylist.length; i++) {
            System.out.print(i+":");
            for (int j = adjacencylist[i].size() - 1; j >= 0; j--) {
                System.out.print("\t-> " + adjacencylist[i].get(j).getDestination() + " W:" + adjacencylist[i].get(j).getWeight());
            }
            System.out.println();
        }
    }
}

class Edge{

    int source;
    int destination;
    int weight;

    public Edge(int v, int u, int weight){
        this.source = v;
        this.destination = u;
        this.weight = weight;
    }
    public int getWeight(){return this.weight;}
    public void setWeight(int weight){
        this.weight = weight;
    }
    public int getSource(){return this.source;}
    public int getDestination(){return this.destination;}
}

