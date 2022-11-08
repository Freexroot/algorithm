package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//二叉树最大宽度
public class Code05_TreeMaxWidth {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    //随机生成二叉树，maxLevel最大节点，maxValue最大数字
    private static Node generateRandomBST(int maxLevel, int maxValue) {
        if (maxLevel == 0) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        Queue<Node> queue = new LinkedList();
        queue.add(head);
        maxLevel--;
        while (maxLevel > 0) {
            Node n = queue.poll();
            if (Math.random() > 0.1) {
                n.left = new Node((int) (Math.random() * maxValue));
                queue.add(n.left);
                maxLevel--;
            } else {
                n.left = null;
            }

            if (maxLevel <= 0) {
                break;
            }

            if (Math.random() > 0.1) {
                n.right = new Node((int) (Math.random() * maxValue));
                queue.add(n.right);
                maxLevel--;
            } else {
                n.right = null;
            }
        }
        return head;
    }

    //用了map
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }
    //没有用map
    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node node = null;
        Node maxNode = head;//下一层最后一个
        Node nowNode = null;//当前节点
        int maxSum = 1, nowSum = 0;
        queue.add(head);

        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                nowSum++;
                nowNode = node.left;
                queue.add(node.left);
            }
            if (node.right != null) {
                nowSum++;
                nowNode = node.right;
                queue.add(node.right);
            }
            //如果现在节点是该层最后一个节点，就更新数据
            if (node == maxNode) {
                maxNode = nowNode;
                maxSum = Math.max(nowSum, maxSum);
                nowSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Node node;
        int maxLevel = 50, maxValue = 100;
        for (int i = 0; i < 1000; i++) {
            node = generateRandomBST(maxLevel, maxValue);
            if (maxWidthNoMap(node) != maxWidthUseMap(node)) {
                System.out.println("error!");
                return;
            }
        }
        System.out.println("good code");
    }
}
