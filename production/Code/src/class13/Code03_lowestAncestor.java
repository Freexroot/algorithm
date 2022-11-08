package class13;

import java.util.HashMap;
import java.util.HashSet;

//给一个二叉树的头节点，求二叉树里节点a，b的最低公共祖先
public class Code03_lowestAncestor {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        Boolean isLeftNode;
        Boolean isRightNode;
        Node node;

        public Info(Boolean isLeftNode, Boolean isRightNode, Node node) {
            this.isLeftNode = isLeftNode;
            this.isRightNode = isRightNode;
            this.node = node;
        }
    }

    //迭代
    public static Node lowestAncestor1(Node head, Node leftNode, Node rightNode) {
        if(leftNode==null || rightNode==null){
            return null;
        }
        return porcess(head, leftNode, rightNode).node;
    }

    private static Info porcess(Node head, Node leftNode, Node rightNode) {
        if (head == null) {
            return new Info(false, false, null);
        }

        Info leftHead = porcess(head.left, leftNode, rightNode);
        Info rightHead = porcess(head.right, leftNode, rightNode);

        Boolean isLeftNode = head == leftNode || leftHead.isLeftNode || rightHead.isLeftNode;
        Boolean isRightNode = head == rightNode || leftHead.isRightNode || rightHead.isRightNode;
        Node node = null;

        if (leftHead.node != null) {
            node = leftHead.node;
        } else if (rightHead.node != null) {
            node = rightHead.node;
        }else if(isLeftNode && isRightNode){
            node=head;
        }

        return new Info(isLeftNode, isRightNode, node);
    }

    //暴力
    public static Node lowestAncestor2(Node head, Node leftNode, Node rightNode) {
        if(leftNode==null || rightNode==null){
            return null;
        }
        if (head == leftNode || head == rightNode) {
            return head;
        }
        return porcess2(head, leftNode, rightNode);
    }

    private static Node porcess2(Node head, Node leftNode, Node rightNode) {
        if (head == null) {
            return null;
        }
        boolean b1 = isNode(head.left, leftNode);
        boolean b2 = isNode(head.left, rightNode);

        boolean b3 = isNode(head.right, leftNode);
        boolean b4 = isNode(head.right, rightNode);

        boolean b5 = head==leftNode || head==rightNode;

        Node node=null;
        //如果都在左边
        if (b1 && b2) {
            node = porcess2(head.left, leftNode, rightNode);
        } else if (b3 && b4) {//如果都在右边
            node = porcess2(head.right, leftNode, rightNode);
        } else if ((b1 && b4) || (b2 && b3) || b5 ) {//如果在左右 或自己是其中一个
            return head;
        }
        return node;
    }

    //判断head里有没有node
    public static Boolean isNode(Node head, Node node) {
        if (head == null) {
            return false;
        }
        Boolean b1 = isNode(head.left, node);
        Boolean b2 = isNode(head.right, node);

        if (b1 || b2 || head == node) {
            return true;
        } else {
            return false;
        }
    }

    //暴力，用map记录父节点，遍历两条map的交集
    public static Node lowestAncestor3(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }
    // 随机二叉树
    public static Node generateRandomBST(int maxLevel, int maxValue, Node []nodes) {
        return generate(1, maxLevel, maxValue, nodes);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue, Node []nodes) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));

        //随机选择两个节点
        if (nodes[0] == null && Math.random() < 0.3) {
            nodes[0] = head;
        } else if (nodes[1] == null && Math.random() < 0.3) {
            nodes[1] = head;
        }
        head.left = generate(level + 1, maxLevel, maxValue, nodes);
        head.right = generate(level + 1, maxLevel, maxValue, nodes);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 3;
        int maxValue = 100;
        int len = 10000;
        Node []nodes = new Node[2];
        for (int i = 0; i < len; i++) {
            nodes[0] = nodes[1] = null;//随机的两个节点
            Node head = generateRandomBST(maxLevel, maxValue, nodes);
            if (lowestAncestor1(head, nodes[0],nodes[1]) != lowestAncestor2(head, nodes[0],nodes[1])) {
                System.out.println("Error");
                lowestAncestor1(head, nodes[0],nodes[1]);
                lowestAncestor2(head, nodes[0],nodes[1]);
            }
        }
        System.out.println("OK");
    }
}
