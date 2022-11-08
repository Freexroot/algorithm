package class16.myGraph;

import java.util.ArrayList;

//适配器-点
public class Node {
    public int value;
    public int in; //入度
    public int out; // 出度
    public ArrayList<Node> nexts;  //可到达的点
    public ArrayList<Edge> edges;  //可通过的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
