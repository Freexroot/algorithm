package class16;

import class16.myGraph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//宽度优先遍历
public class Code01_BFS {
    //按照宽度优先遍历打印，node是起点
    public void bfs(Node head) {
        if (head == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();//避免反复打印
        queue.add(head);
        set.add(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            for (Node n : node.nexts) {
                if(!set.contains(n)){
                    queue.add(n);
                    set.add(head);
                }
            }
        }
    }
}
