package class10;

//给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null
public class Code01_FindFirstIntersectNode {
    public static class Node {
        Node next;
        int value;

        Node(int v) {
            value = v;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        Node loop1 = getLoopNode(head1);//null表示无环,否则为入环点
        Node loop2 = getLoopNode(head2);

        //两个单链表
        if (loop1 == null && loop2 == null) {
            return twoNoLoop(head1, head2);
        }
        //两个环链表
        if (loop1 != null && loop2 != null) {
            return twoYesLoop(head1, loop1, head2, loop2);
        }

        return null;
    }

    //两个有环链表，判断相交点,这里有3种情况
    private static Node twoYesLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (loop1 == loop2) {
            int n = 0;
            Node cur1 = head1;
            Node cur2 = head2;

            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }

            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            n = Math.abs(n);

            while (n-- != 0) {
                cur1 = cur1.next;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            if (cur1 == cur2) {
                return cur1;
            }
        } else {
            Node cur1 = loop1.next;
            while (cur1 != loop2) {
                cur1 = cur1.next;
            }
            if (cur1 == loop1) {
                return null;
            }
            return loop1;
        }

        return null;
    }

    //两个无环链表，判断相交点
    private static Node twoNoLoop(Node head1, Node head2) {
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;

        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }

        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        while (n-- != 0) {
            cur1 = cur1.next;
        }

        while (cur1 != null) {
            if (cur1 == cur2) {
                return cur1;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return null;
    }

    private static Node getLoopNode(Node head) {
        Node cur1 = head.next;
        Node cur2 = head.next.next;

        //判断是否有交点
        while (cur1 != cur2) {
            if (cur2.next == null || cur2.next.next == null) {
                return null;
            }
            cur1 = cur1.next;
            cur2 = cur2.next.next;
        }

        //找到交点
        cur2 = head;
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->9...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->9
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }


}
