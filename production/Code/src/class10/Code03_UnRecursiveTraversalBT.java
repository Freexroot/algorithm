package class10;

import java.util.Stack;

//非递归打印二叉树
public class Code03_UnRecursiveTraversalBT {
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

    //先序
    private static void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            //左右树的压栈要相反
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    //中序
    private static void in(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        while (head!=null||!stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head=head.right;
            }
        }
    }

    //后序
    private static void pos(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        Stack<Node> stack1 = new Stack<Node>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            stack1.push(head);
            if (head.left != null) {
                stack.push(head.left);
            }
            if (head.right != null) {
                stack.push(head.right);
            }
        }
        while (!stack1.isEmpty()) {
            System.out.print(stack1.pop().value + " ");
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.print("先序：");
        pre(head);
        System.out.print("\n中序：");
        in(head);
        System.out.print("\n后序：");
        pos(head);
    }


}
