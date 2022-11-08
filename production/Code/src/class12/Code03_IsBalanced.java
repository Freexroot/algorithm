package class12;

//判断是不是平衡二叉树
public class Code03_IsBalanced {
    public static class Node {
        int value;
        Node left;
        Node right;

        Node(int v) {
            value = v;
        }
    }

    public static class Info {
        int height;
        Boolean isBalanced;

        Info(int h, Boolean isB) {
            height = h;
            isBalanced = isB;
        }
    }

    //递归-套路
    public static Boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(0, true);
        }
        Info left = process(node.left);
        Info right = process(node.right);

        int height;
        Boolean isBalanced;

        if (!left.isBalanced || !right.isBalanced) {
            isBalanced = false;
        } else if (Math.abs(left.height - right.height) > 1) {
            isBalanced = false;
        } else {
            isBalanced = true;
        }
        height = Math.max(left.height, right.height) + 1;

        return new Info(height, isBalanced);
    }

    //递归-抄的
    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
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
            if (isBalanced(head) != isBalanced1(head)) {
                System.out.println("Error");
//                isBalanced(head);
            }
        }
        System.out.println("OK");
    }
}
