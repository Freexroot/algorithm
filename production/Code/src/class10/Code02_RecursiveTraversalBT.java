package class10;

//递归打印二叉树
public class Code02_RecursiveTraversalBT {
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


    public void f(Node head) {
        if (head == null) {
            return;
        }
        //1先序
        f(head.left);
        //2中序
        f(head.right);
        //3后序
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value+" ");
        pre(head.left);
        pre(head.right);

    }

    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.value+" ");
        in(head.right);
    }

    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.value+" ");
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
