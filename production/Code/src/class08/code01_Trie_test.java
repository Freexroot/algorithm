package class08;

public class code01_Trie_test {
    public static void main(String[] args) {
        Code01_Trie trie = new Code01_Trie();

        trie.insert("apple");
        System.out.println("null");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println("null");
        System.out.println(trie.search("app"));

    }
}
