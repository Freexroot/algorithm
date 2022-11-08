package class12;

import java.util.LinkedList;
import java.util.Queue;

//判断二叉树是否是完全二叉树
public class Code01_IsCBT {
    public static class Node {
        int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    //非递归 按层遍历
    public static Boolean isCompleteBinaryTree1(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        Node node;
        int t;
        boolean f = false;
        queue.add(head);

        while (!queue.isEmpty()) {
            t = 0;
            node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                t++;
                if (f) {
                    return false;
                }
            }
            if (node.right != null) {
                queue.add(node.right);
                t++;
                if (f || node.left == null) {
                    return false;
                }
            }

            if (t != 2) {
                f = true;
            }
        }
        return true;
    }

    public static class Info {
        public Boolean isFBT;//满二叉树
        public Boolean isCBT;//完全二叉树
        public int height;//高度


        public Info(Boolean isFBT, Boolean isCBT, int height) {
            this.isFBT = isFBT;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    //递归
    public static Boolean isCompleteBinaryTree2(Node head) {
        return porcess(head).isCBT;
    }
    public static Info porcess(Node node) {
        if (node == null) {
            return new Info(true, true, 0);
        }

        Info left = porcess(node.left);
        Info right = porcess(node.right);

        Boolean isFBT = false;//满二叉树
        Boolean isCBT = false;//完全二叉树
        int height = Math.max(left.height, right.height) + 1;//高度
        //左右都是满并且等高
        if (left.isFBT && right.isFBT && (left.height == right.height)) {
            isFBT = true;
            isCBT = true;
        } else if (left.isCBT && right.isFBT && (left.height - 1 == right.height)) {
            isCBT = true;
        } else if (left.isFBT && right.isFBT && (left.height - 1 == right.height)) {
            isCBT = true;
        } else if (left.isFBT && right.isCBT && (left.height == right.height)) {
            isCBT = true;
        }
        return new Info(isFBT, isCBT, height);
    }


    // 随机二叉树
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 100;
        int maxValue = 100;
        int len = 10000;

        for (int i = 0; i < len; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCompleteBinaryTree1(head) != isCompleteBinaryTree2(head)) {
                System.out.println("Error");
                isCompleteBinaryTree2(head);
            }
        }
        System.out.println("OK");
    }
}
