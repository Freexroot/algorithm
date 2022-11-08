package class08;

//前缀树
// 测试链接 : https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
// 提交Trie类可以直接通过
// 原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
// 这个链接上要求实现的功能和课上讲的完全一样
// 该前缀树的路用数组实现
public class Code01_Trie {

    //输入是26个字母,可以改写成任意值，用哈希表
    class Node1 {
        public int pass;//经过次数
        public int end;//结尾次数
        public Node1[] node;

        public Node1() {
            this.pass = 0;
            this.end = 0;
            node = new Node1[26];
        }
    }


    //头节点
    private Node1 root;

    Code01_Trie() {
        root = new Node1();
    }

    //插入一个数据
    void insert(String word) {
        if (word == "" || word == null) {
            return;
        }

        char arr[] = word.toCharArray();
        Node1 head = root;
        int tem = 0;

        head.pass++;//头节点
        for (int i = 0; i < arr.length; i++) {
            tem = arr[i] - 'a';
            if (head.node[tem] == null) {
                head.node[tem] = new Node1();
            }
            head = head.node[tem];
            head.pass++;
        }
        head.end++;
    }

    //删除一个数据
    public void delete(String str) {
        //如果是无效数据或者数据不存在
        if (!search(str)) {
            return;
        }

        char arr[] = str.toCharArray();
        Node1 head = root;
        int tem = 0;

        head.pass--;//头节点
        for (int i = 0; i < arr.length; i++) {
            tem = arr[i] - 'a';
            if (head.node[tem].pass - 1 == 0) {
                head.node[tem] = null;
                return;
            }
            head.node[tem].pass--;
            head = head.node[tem];
        }
        head.end--;
    }

    //查询是否存在该元素
    public boolean search(String word) {
        //无效数据
        if (word == "" || word == null) {
            return false;
        }

        char arr[] = word.toCharArray();
        Node1 head = root;
        int tem = 0;
        for (int i = 0; i < arr.length; i++) {
            tem = arr[i] - 'a';
            if (head.node[tem] == null) {
                return false;
            }
            head = head.node[tem];
        }
        return head.end!=0;
    }

    //查符合前缀数量
    public int prefixesSize(String str) {
        //无效数据
        if (str == "" || str == null) {
            return 0;
        }

        char arr[] = str.toCharArray();
        Node1 head = root;
        int tem = 0;
        for (int i = 0; i < arr.length; i++) {
            tem = arr[i] - 'a';
            if (head.node[tem] == null) {
                return 0;
            }
            head = head.node[tem];
        }
        return head.pass;
    }

    public boolean startsWith(String prefix){
        //无效数据
        if (prefix == "" || prefix == null) {
            return false;
        }

        char arr[] = prefix.toCharArray();
        Node1 head = root;
        int tem = 0;
        for (int i = 0; i < arr.length; i++) {
            tem = arr[i] - 'a';
            if (head.node[tem] == null) {
                return false;
            }
            head = head.node[tem];
        }
        return true;
    }

}
