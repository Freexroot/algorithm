package class16;

import class16.myGraph.Edge;
import class16.myGraph.Graph;
import class16.myGraph.Node;

//图的二维表示 转 自己的图结构
public class GraphGenerator {
    //{[权重，起点，终点],[...],...}
    public Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Node from = graph.nodes.get(matrix[i][1]);//起点
            Node to = graph.nodes.get(matrix[i][2]);//终点

            if (from == null) {
                from = new Node(matrix[i][1]);
            }
            if (to == null) {
                to = new Node(matrix[i][2]);
            }
            Edge edge = new Edge(matrix[i][0], from, to);

            from.out++;
            to.in++;

            from.edges.add(edge);
            from.nexts.add(to);

            graph.nodes.put(from.value, from);
            graph.nodes.put(to.value, to);
        }
        return graph;
    }
}
