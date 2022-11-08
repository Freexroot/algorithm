package class11;

import java.util.List;

// 本题测试链接：https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
//将N叉树编码为二叉树
public class Code03_EncodeNaryTreeToBinaryTree {
    // 提交时不要提交这个类，多叉树节点
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 提交时不要提交这个类，二叉树节点
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //提交这个类
    class Codec {
        // Encodes an n-ary tree to a binary tree.多叉树转二叉树
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = en(root.children);
            return head;
        }

        public TreeNode en(List<Node> children) {
            TreeNode head = null, cur = null;
            for (Node chil : children) {
                TreeNode tNode = new TreeNode(chil.val);
                if (head == null) {
                    cur = head = tNode;
                } else {
                    cur.right = tNode;
                    cur = cur.right;
                }
                cur.left = en(chil.children);
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.二叉树树转多叉树
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            Node head = new Node(root.val);
            head.children = de(root.left);
            return head;
        }

        private List<Node> de(TreeNode left) {
            List<Node> head = null;//多叉树节点
            Node cur = null;
            while (left != null) {
                cur = new Node(left.val);
                head.add(cur);
                cur.children = de(left.left);
                left = left.right;
            }
            return head;
        }
    }
}
