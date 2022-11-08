package class16;

import java.util.*;

// OJ链接：https://www.lintcode.com/problem/topological-sorting
//拓扑排序，入度，类似广度优先搜索
public class Code03_TopologicalOrderBFS {

    // Definition for Directed graph
    static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    //入度0的在前
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        //统计所有节点的入度
        HashMap<DirectedGraphNode, Integer> inMap = new HashMap<>();
        //初始化
        for (DirectedGraphNode node : graph) {
            inMap.put(node, 0);
        }
        //统计入度
        for (DirectedGraphNode from : graph) {
            for (DirectedGraphNode to : from.neighbors) {
                inMap.put(to, inMap.get(to) + 1);
            }
        }
        //记录第一批0入度节点
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode dir : inMap.keySet()) {
            if (inMap.get(dir) == 0) {
                queue.add(dir);
            }
        }
        //排序
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            ans.add(node);
            for (DirectedGraphNode n : node.neighbors) {
                inMap.put(n, inMap.get(n) - 1);//选择该0入度节点后，把所链接的对象节点入度减1
                if (inMap.get(n) == 0) {//如果产生新的0入度点，就加入队列
                    queue.add(n);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ArrayList<DirectedGraphNode> a = new ArrayList<>();
        a.add(new DirectedGraphNode(1));
        a.add(new DirectedGraphNode(2));
        a.add(new DirectedGraphNode(3));
        ArrayList<DirectedGraphNode> directedGraphNodes = topSort(a);
        for (DirectedGraphNode directedGraphNode : directedGraphNodes) {
            System.out.println(directedGraphNode.label);
        }
    }
}
