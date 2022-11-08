package class09;

/*给定一个单链表的头节点head，请判断该链表是否为回文结构。
 */
public class Code02_IsPalindromeList {
    public static class Node {
        public Node next;
        public int value;

        public Node(int v) {
            this.value = v;
        }
    }

    //回文判断
    public static boolean isPalindrome(Node head) {
        if(head.next==null || head==null){
            return true;
        }

        Node head1 = head;
        Node head2 = pointerReverse(head);//尾节点

        while (head1 != null && head2 != null) {
            if(head1.value!= head2.value){
                return false;
            }
            head1=head1.next;
            head2=head2.next;
        }

        return true;
    }

    //指针逆转
    public static Node pointerReverse(Node head) {
        Node n1 = head;
        Node n2 = head; //中点
        Node n3;//辅助指针

        //偶数的中下点，奇数的中点
        while (n1 != null && n1.next != null) {
            n1 = n1.next.next;
            n2 = n2.next;
        }
        n1 = n2.next;//记录中点位置


        n2.next = null;//中点结尾为空
        while (n1 != null) {//指针翻转
            n3 = n1.next;
            n1.next = n2;
            n2 = n1;
            n1 = n3;
        }

        return n2;
    }
    //


    public static void main(String[] args) {
        Node head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(3);
//        head.next.next.next = new Node(2);
//        head.next.next.next.next = new Node(1);
        System.out.println(isPalindrome(head));

    }
}
