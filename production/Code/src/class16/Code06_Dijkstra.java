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


    // 改进后的dijkstra算法，使用加强堆
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(head, 0);
        NodeRecord minNode = nodeHeap.pop();
        while (minNode != null) {
            for (Edge edge : minNode.node.edges) {
                map.put(edge.to, minNode.distance);//更新或者添加数据
                //丢入加强堆中
                nodeHeap.addOrUpdateOrIgnore(edge.to, minNode.distance);
            }
            minNode = nodeHeap.pop();
        }
        return map;
    }

    //小根加强堆
    class NodeHeap {
        private Node[] nodes;
        //反向索引表 key 某一个node， value 上面堆中的位置。v=-1表示该对象已弹出
        private HashMap<Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离,可以用NodeRecord类来取代
        private HashMap<Node, Integer> distanceMap;
        private int size;//堆当前数量

        public NodeHeap(int size) {
            this.size = size;
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (!heapIndexMap.containsKey(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size++);
                distanceMap.put(node, distance);
                heapUpper(heapIndexMap.get(node));
            }
            //如果该值没有被确认
            if (heapIndexMap.get(node) != -1) {
                distanceMap.put(node, distance);
                int index = heapIndexMap.get(node);
                heapUpper(index);
                heapLower(index);
            }
            //如果 节点被确认 就忽略这个节点
        }

        public NodeRecord pop() {
            if (size == 0) {
                return null;
            }
            Node node = nodes[0];
            heapIndexMap.put(node,-1);
            swap(0, --size);
            heapLower(0);
            return new NodeRecord(node,distanceMap.get(node));
        }

        //上升到合适的位置
        private void heapUpper(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //下拉到合适的位置
        private void heapLower(int index) {
            int left = (index + 1) * 2;
            while (left < size) {
                left = left + 1 < size && distanceMap.get(left + 1) < distanceMap.get(left) ? left + 1 : left;
                swap(index, left);
                left = (left + 1) * 2;
            }
        }

        //只交换
        private void swap(int a, int b) {
            Node node = nodes[a];
            nodes[a] = nodes[b];
            heapIndexMap.put(nodes[b], a);
            nodes[b] = node;
            heapIndexMap.put(nodes[a], b);

        }
    }

    public static void main(String[] args) {

    }
}
