package class09;

/*
锻炼链表边界能力，关键点在于快指针只能在相邻两个节点判断，用两个条件锁定范围就可以了
快慢指针
1)输入链表头节点，奇数长度返回中点，偶数长度返回上中点
2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
4)输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
*/
public class Code01_LinkedListMid {
    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    //1)输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node n1 = head, n2 = head;
        //快指针到达最后两个节点就跳出,n2 != null表示偶数条件，n2.next.next != null是奇数条件
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        return n1;
    }

    //2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node n1 = head, n2 = head;
        //快指针到达最后两个节点就跳出,n2 != null表示偶数条件，n2.next != null是奇数条件
        while (n2 != null && n2.next != null ) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        return n1;
    }

    //3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node midOrUpMidPreNode(Node head) {
        //两个节点不满足要求，特殊处理
        if (head == null || head.next == null || head.next.next==null) {
            return head;
        }

        Node n1 = head, n2 = head;
        //快指针到达最后两个节点就跳出,n2.next.next.next.next != null表示偶数条件，n2.next.next.next != null是奇数条件
        //用辅助节点也可以完成
        while (n2.next.next.next != null && n2.next.next.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        return n1;
    }

    //4)输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node n1 = head, n2 = head;
        //快指针到达最后两个节点就跳出,n2.next.next != null表示偶数条件，n2.next.next.next != null是奇数条件
        while (n2.next.next != null && n2.next.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        return n1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        System.out.println(midOrUpMidNode(head).value + " " + midOrDownMidNode(head).value + " " + midOrUpMidPreNode(head).value + " " + midOrDownMidPreNode(head).value);

        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        System.out.println(midOrUpMidNode(head1).value + " " + midOrDownMidNode(head1).value + " " + midOrUpMidPreNode(head1).value + " " + midOrDownMidPreNode(head1).value);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        System.out.println(midOrUpMidNode(head2).value + " " + midOrDownMidNode(head2).value + " " + midOrDownMidPreNode(head2).value + " " + midOrUpMidPreNode(head2).value);
    }
}
