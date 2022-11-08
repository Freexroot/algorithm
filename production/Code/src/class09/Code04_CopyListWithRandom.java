package class09;

import java.util.HashMap;

//拷贝新的链表
// 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/

public class Code04_CopyListWithRandom {
    //方法1 用map容器
    public static Node copyLinkedList1(Node head) {
        if (head == null) {
            return head;
        }

        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node copyHead = head;
        //Node n = copyHead;

        while (copyHead != null) {
            map.put(copyHead, new Node(copyHead.val));
            copyHead = copyHead.next;
        }
        copyHead = head;

        while (copyHead != null) {
            map.get(copyHead).next = copyHead.next == null ? null : map.get(copyHead.next);
            map.get(copyHead).random = copyHead.random == null ? null : map.get(copyHead.random);

            copyHead = copyHead.next;
        }
        return map.get(head);
    }

    //方法2 复制节点 再脱离
    public static Node copyLinkedList2(Node head) {
        Node copyHead = head;
        Node tem;
        //增加新节点
        while (copyHead != null) {
            tem = new Node(copyHead.val);
            tem.next = copyHead.next;
            copyHead.next = tem;
            copyHead = copyHead.next.next;
        }
        copyHead = head;
        //同步连接
        while (copyHead != null) {
            copyHead.next.random = copyHead.random == null ? null : copyHead.random.next;
            copyHead = copyHead.next.next;
        }
        //分离新节点
        tem = copyHead = head.next;

        while (head.next.next != null) {
            head = head.next.next;
            tem.next = head.next;
            tem = tem.next;//通过新的指向，移动到下一个新节点
        }
        return copyHead;
    }

    //打印链表
    private static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(3);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next.next = new Node(4);
        head1.next.next.next.next.next.next.next.next = new Node(4);
        printLinkedList(head1);
        //head1 = copyLinkedList1(head1);
        head1 = copyLinkedList2(head1);
        printLinkedList(head1);

    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
