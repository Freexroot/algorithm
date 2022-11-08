package class16.myGraph;

import java.util.HashMap;
import java.util.HashSet;

//适配器-图
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
