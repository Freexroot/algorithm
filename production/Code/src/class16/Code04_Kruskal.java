package class16;

import class16.myGraph.Edge;
import class16.myGraph.Graph;
import class16.myGraph.Node;

import java.util.*;

//最小生成树算法之Kruskal,并查集
public class Code04_Kruskal {
    public class UnionFind {
        public HashMap<Node, Node> parents;//第一个参数是本节点，第二个参数是本节点的父亲节点。用指针表示效果一样
        public HashMap<Node, Integer> sizeMap;//代表节点对应集合的节点数

        //构造函数-初始化参数
        public UnionFind(Graph values) {
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for (Node value : values.nodes.values()) {
                parents.put(value, value);
                sizeMap.put(value, 1);
            }
        }

        // 给你一个节点，请你往上到不能再往上，把代表返回
        // 同时把本节点的所有父节点都指向代表节点
        public Node findFather(Node cur) {
            Stack<Node> stack = new Stack<>();
            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        //查询两个元素是否在同一个集合中。
        public boolean isSameSet(Node a, Node b) {
            return findFather(parents.get(a)) == findFather(parents.get(b));
        }

        //把两个不相交的集合合并为一个集合。
        public void union(Node a, Node b) {
            Node aHead = findFather(a);
            Node bHead = findFather(b);
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node big = aSetSize >= bSetSize ? aHead : bHead;
                Node small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        //集合数
        public int sets() {
            return sizeMap.size();
        }
    }

    //返回最小生成树的边集
    public Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind(graph);
        Queue<Edge> queue = new PriorityQueue(new myComparator());//边从小大排序

        queue.addAll(graph.edges);
        HashSet<Edge> set = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                unionFind.union(edge.from, edge.to);
                set.add(edge);
            }
        }
        return set;
    }

    public class myComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
