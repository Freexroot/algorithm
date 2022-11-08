package class09;

import java.util.ArrayList;

//把链表安装某值分为小于，等于，大于区域
public class Code03_SmallerEqualBigger {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //打印链表
    private static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    //方法1；放入数组，之后用partition
    private static Node listPartition1(Node head, int num) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Node n = head;
        while (n != null) {
            arr.add(n.value);
            n = n.next;
        }

        int t1 = -1, t2 = arr.size();//边界
        int index = 0;//下标

        while (index > t1 && index < t2) {
            if (arr.get(index) < num) {
                swap(arr, index, t1 + 1);
                index++;
                t1++;
            } else if (arr.get(index) == num) {
                index++;
            } else {
                swap(arr, index, t2 - 1);
                t2--;
            }
        }
        n = head;
        for (int i = 0; i < arr.size(); i++) {
            n.value = arr.get(i);
            n = n.next;
        }

        return head;
    }

    public static void swap(ArrayList<Integer> arr, int a, int b) {
        if (a == b) {
            return;
        }
        int num = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, num);
    }

    //方法2：链表分段，之后汇合
    private static Node listPartition2(Node head, int num) {
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node mH = null; // big head
        Node mT = null; // big tail
        Node next = null; // save next node

        //3段链表
        while (head != null) {
            if (head.value < num) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.value == num) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            } else {
                if (mH == null) {
                    mH = head;
                    mT = head;
                } else {
                    mT.next = head;
                    mT = mT.next;
                }
            }
            head = head.next;
        }
        sT.next = null;
        eT.next = null;
        mT.next = null;

        //合并链表
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) { // 如果有小于区域
            sT.next = eH;
            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (eT != null) { // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
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
        head1 = listPartition1(head1, 4);
        //head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }


}
