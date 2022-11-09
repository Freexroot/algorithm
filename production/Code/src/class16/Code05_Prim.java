package class16;

import class16.myGraph.Edge;
import class16.myGraph.Graph;
import class16.myGraph.Node;

import java.util.*;

//最小生成树算法之Prim
public class Code05_Prim {
    static class myComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        Queue<Edge> queue = new PriorityQueue<>(new myComparator());
        Set<Edge> edgeSet = new HashSet<>();
        Set<Node> nodeSet = new HashSet<>();

        for (Node value : graph.nodes.values()) {
            queue.addAll(value.edges);
            nodeSet.add(value);
            //如果所有节点都找到
            while (graph.nodes.size() != nodeSet.size()) {
                Edge edgeMin = queue.poll();
                if (!nodeSet.contains(edgeMin.to)) {
                    edgeSet.add(edgeMin);
                    nodeSet.add(edgeMin.to);
                    queue.addAll(edgeMin.to.edges);
                }
            }
            //如果输入参数一定不是森林，就注释break
            break;
        }
        return edgeSet;
    }

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        return 0;
    }
}
