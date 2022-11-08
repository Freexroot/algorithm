package class11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//序列化和反序列化,二叉树-先序-后序-按层
/*
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 以下代码全部实现了。
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * 比如如下两棵树
 *         __2
 *        /
 *       1
 *       和
 *       1__
 *          \
 *           2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 *
 * */
public class Code02_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

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
            if (Math.random() > 0.5) {
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

    //先序序列化
    static Queue preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    //迭代
    public static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
            return;
        }
        ans.add(String.valueOf(head.value));
        pres(head.left, ans);
        pres(head.right, ans);
    }

    //先序反序列化
    private static Node buildByPreQueue(Queue<String> ans) {
        if (ans == null || ans.isEmpty()) {
            return null;
        }
        return preb(ans);
    }

    private static Node preb(Queue<String> ans) {
        Node head;
        if (ans.peek() == null) {
            ans.poll();
            return null;
        }
        head = new Node(Integer.valueOf(ans.poll()));
        head.left = preb(ans);
        head.right = preb(ans);
        return head;
    }

    //后序序列化
    private static Queue<String> posSerial(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> ans = new LinkedList<>();
        poss(head, ans);
        return ans;
    }

    private static void poss(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
            return;
        }
        poss(head.left, ans);
        poss(head.right, ans);
        ans.add(String.valueOf(head.value));
    }

    //后序反序列化
    private static Node buildByPosQueue(Queue<String> poslist) {
        if (poslist.isEmpty() || poslist == null) {
            return null;
        }
        // 左右中  ->  stack(中右左)
        Stack<String> posstack = new Stack();
        while (!poslist.isEmpty()) {
            posstack.push(poslist.poll());
        }
        return posb(posstack);
    }

    private static Node posb(Stack<String> posstack) {
        if (null == (posstack.peek())) {
            posstack.pop();
            return null;
        }
        Node head = new Node(Integer.valueOf(posstack.pop()));
        head.right = posb(posstack);
        head.left = posb(posstack);
        return head;
    }

    //按层序列化
    private static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            return null;
        } else {
            Queue<Node> queue = new LinkedList<>();
            Node n;
            queue.add(head);
            while (!queue.isEmpty()) {
                n = queue.poll();
                if (n != null) {
                    queue.add(n.left);
                    queue.add(n.right);
                    ans.add(String.valueOf(n.value));
                } else {
                    ans.add(null);
                }
            }
            return ans;
        }
    }

    //按层反序列化
    private static Node buildByLevelQueue(Queue<String> level) {
        if (level == null || level.isEmpty()) {
            return null;
        }
        Node head = new Node(Integer.valueOf(level.poll()));
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(level.poll());
            node.right = generateNode(level.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }
    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }
    //判断两个二叉树是否相等。
    private static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    //打印二叉树
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }
    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);

            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
                printTree(preBuild);
                System.out.println("=================================");
                printTree(posBuild);
                System.out.println("=================================");
                printTree(levelBuild);
                return;
            }
        }
        System.out.println("test finish!");
    }


}
