package class12;

import java.util.ArrayList;

//搜索二叉树
public class Code02_IsBST {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        Boolean isBst;
        int min;
        int max;

        Info(Boolean isBst, int min, int max) {
            this.isBst = isBst;
            this.min = min;
            this.max = max;
        }
    }

    public static Boolean isBst(Node head) {
        if (head == null) {
            return true;
        }
        return porcess(head).isBst;
    }

    private static Info porcess(Node node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = porcess(node.left);
        Info rightInfo = porcess(node.right);

        Boolean isBst = true;
        int min = node.value;
        int max = node.value;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        if (leftInfo != null && !leftInfo.isBst) {
            isBst = false;
        }
        if (rightInfo != null && !rightInfo.isBst) {
            isBst = false;
        }


        if (leftInfo != null && node.value <= leftInfo.max) {
            isBst = false;
        }
        if (rightInfo != null && node.value >= rightInfo.min) {
            isBst = false;
        }
        return new Info(isBst, min, max);
    }

    //暴力-抄
    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
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
            if (isBst(head) != isBST1(head)) {
                System.out.println("Error");
                isBst(head);
            }
        }
        System.out.println("OK");
    }

}
