package class14;


import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//并查集
public class Code05_UnionFind {
    //涉及到了指针，所以要把非对象类型包装成指针
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;//值和对象的映射
        public HashMap<Node<V>, Node<V>> parents;//第一个参数是本节点，第二个参数是本节点的父亲节点。用指针表示效果一样
        public HashMap<Node<V>, Integer> sizeMap;//代表节点对应集合的节点数

        //构造函数-初始化参数
        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();

            for (V value : values) {
                Node node = new Node(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 给你一个节点，请你往上到不能再往上，把代表返回
        // 同时把本节点的所有父节点都指向代表节点
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
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
        public boolean isSameSet(V a, V b) {
            return findFather(parents.get(a))==findFather(parents.get(b));
        }

        //把两个不相交的集合合并为一个集合。
        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
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
}