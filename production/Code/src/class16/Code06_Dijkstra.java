package class16;

import class16.myGraph.Edge;
import class16.myGraph.Node;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

//最短路径算法-Dijkstra算法
public class Code06_Dijkstra {
    static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    class myComparator implements Comparator<NodeRecord> {

        @Override
        public int compare(NodeRecord o1, NodeRecord o2) {
            return o1.distance - o2.distance;
        }
    }

    //堆实现，堆是无法更新值的，只能写额外的方法来实现数据更新。或者改用加强堆
    public HashMap<Node, Integer> dijkstra1(Node from) {
        if (from == null) {
            return null;
        }
        Queue<NodeRecord> queue = new PriorityQueue<>(new myComparator());//模拟堆,记录表
        HashMap<Node, Integer> nodeMap = new HashMap<>();

        nodeMap.put(from, 0);
        queue.add(new NodeRecord(from, 0));//在里面说明值被确认了
        NodeRecord minNode = queue.poll();
        while (minNode != null) {
            for (Edge edge : minNode.node.edges) {
                if (!nodeMap.containsKey(edge.to)) {//如果从来没有到达过这个点
                    nodeMap.put(edge.to, minNode.distance + edge.weight);
                    queue.add(new NodeRecord(edge.to, nodeMap.get(edge.to)));//添加值
                } else if (minNode.distance + edge.weight < nodeMap.get(edge.to)) {//如果这个值存在，并且边长更短，就更新值
                    nodeMap.put(edge.to, minNode.distance + edge.weight);
                    upQueue(queue, edge.to, minNode.distance + edge.weight);//更新值
                }
            }
            minNode = queue.poll();
        }

        return nodeMap;
    }
    //更新Queue的to节点值为vue
    public void upQueue(Queue<NodeRecord> queue, Node to, int vue) {
        for (NodeRecord nodeRecord : queue) {
            if (nodeRecord.node == to) {
                nodeRecord.distance = vue;
            }
        }
    }

}
