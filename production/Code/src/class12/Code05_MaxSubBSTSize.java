package class12;

import java.util.ArrayList;

//找到二叉树里最大二叉搜索树
//返回最大二叉树的节点数
public class Code05_MaxSubBSTSize {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //递归-套路
    public static class Info {
        int maxBSTSubtreeSize;//最大节点数
        Boolean isBST;//是否是搜索二叉树
        int min;
        int max;

        public Info(int maxBSTSubtreeSize, Boolean isBST, int min, int max) {
            this.maxBSTSubtreeSize = maxBSTSubtreeSize;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static int maxSubBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }

        Info left = process(node.left);
        Info right = process(node.right);

        int maxBSTSubtreeSize = 1;
        Boolean isBST = true;
        int min = node.value;
        int max = node.value;

        //判断最大最小值
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }
        //判断是否平衡二叉树
        if (left != null && node.value <= left.max) {
            isBST = false;
        }
        if (right != null && node.value >= right.min) {
            isBST = false;
        }
        if (left != null && !left.isBST) {
            isBST = false;
        }
        if (right != null && !right.isBST) {
            isBST = false;
        }

        //节点数
        if (isBST && left != null) {
            maxBSTSubtreeSize += left.maxBSTSubtreeSize;
        }
        if (isBST && right != null) {
            maxBSTSubtreeSize += right.maxBSTSubtreeSize;
        }
        if (isBST && left != null && right != null) {
            maxBSTSubtreeSize = left.maxBSTSubtreeSize + right.maxBSTSubtreeSize + 1;
        }

        if (!isBST && left != null) {
            maxBSTSubtreeSize = Math.max(maxBSTSubtreeSize, left.maxBSTSubtreeSize);
        }
        if (!isBST && right != null) {
            maxBSTSubtreeSize = Math.max(maxBSTSubtreeSize, right.maxBSTSubtreeSize);
        }
        return new Info(maxBSTSubtreeSize, isBST, min, max);
    }

    // 为了验证
    // 对数器方法
    public static int right(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
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
        int maxLevel = 30;
        int maxValue = 100;
        int len = 10000;

        for (int i = 0; i < len; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize(head) != right(head)) {
                System.out.println("Error");
                maxSubBSTSize(head);
            }
        }
        System.out.println("OK");
    }
}
