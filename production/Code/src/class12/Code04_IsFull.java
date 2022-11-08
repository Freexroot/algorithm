package class12;

//满二叉树
public class Code04_IsFull {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //递归-套路
    // 第一种方法
    // 收集整棵树的高度h，和节点数n
    // 只有满二叉树满足 : 2 ^ h - 1 == n
    public static class Info {
        int height;
        int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Boolean isFull(Node head) {
        Info porcess = porcess(head);
        if (Math.pow(2, (double) porcess.height) - 1 == porcess.nodes) {
            return true;
        } else {
            return false;
        }
    }

    private static Info porcess(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }

        Info left = porcess(head.left);
        Info right = porcess(head.right);
        int height;
        int nodes;

        height = Math.max(left.height, right.height) + 1;
        nodes = left.nodes + right.nodes + 1;
        return new Info(height, nodes);
    }

    // 第二种方法
    // 收集子树是否是满二叉树
    // 收集子树的高度
    // 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的
    public static class Info2 {
        public boolean isFull;
        public int height;

        public Info2(boolean f, int h) {
            isFull = f;
            height = h;
        }
    }

    public static boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        return process2(head).isFull;
    }

    public static Info2 process2(Node h) {
        if (h == null) {
            return new Info2(true, 0);
        }
        Info2 leftInfo = process2(h.left);
        Info2 rightInfo = process2(h.right);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info2(isFull, height);
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
            if (isFull(head) != isFull2(head)) {
                System.out.println("Error");
//                isFull(head);
            }
        }
        System.out.println("OK");
    }
}

