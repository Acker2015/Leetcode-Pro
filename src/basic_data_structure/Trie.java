package basic_data_structure;



class Trie {
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
    public Trie() {
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
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
