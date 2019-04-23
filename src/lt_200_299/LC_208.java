package lt_200_299;

/**
 * 字典树
 * [208] Implement Trie (Prefix Tree)
 */
public class LC_208 {
    private class Node {
        boolean isTail;
        char c;
        Node[] next;
        public Node(char c) {
            this.c = c;
            this.isTail = false;
            next = new Node[26];
        }
    }

    private Node root;
    /** Initialize your data structure here. */
    public LC_208() {
        root = new Node('0');
    }
    private Node find(String prefix) {
        char[] chs = prefix.toCharArray();
        Node curNode = root;
        int i = 0;
        while (curNode!=null && i < chs.length) {
            curNode = curNode.next[chs[i++]-'a'];
        }
        return curNode;
    }
    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chs = word.toCharArray();
        Node curNode = root;
        for (char c: chs) {
            int idx = c-'a';
            if (curNode.next[idx] == null) {
                curNode.next[idx] = new Node(c);
            }
            curNode = curNode.next[idx];
        }
        curNode.isTail = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node ansNode = find(word);
        return ansNode!=null && ansNode.isTail;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node ansNode = find(prefix);
        return ansNode!=null;
    }

    /**
     *
     * trie.insert("apple");
     trie.search("apple");   // returns true
     trie.search("app");     // returns false
     trie.startsWith("app"); // returns true
     trie.insert("app");
     trie.search("app");     // returns true
     * @param args
     */
    public static void main(String ...args) {
        LC_208 trie = new LC_208();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}
