package class16;

import class16.myGraph.Node;

import java.util.HashSet;
import java.util.Stack;

//深度优先遍历
public class Code02_DFS {
    // 打印
    public void dfs(Node head) {
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        stack.add(head);
        set.add(head);
        System.out.println(head.value);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            for (Node n : node.nexts) {
                if (!set.contains(n)) {
                    stack.add(node);
                    stack.add(n);
                    set.add(n);
                    System.out.println(n.value);
                    break;
                }
            }
        }

    }
}
