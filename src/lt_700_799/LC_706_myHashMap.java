package lt_700_799;

/**
 * [706] Design HashMap
 *
 * 链地址法（拉链法）
 */
public class LC_706_myHashMap {
    public class Node {
        Node next;
        int key, val;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    private int len = 4096;
    private Node[] array;
    /** Initialize your data structure here. */
    public LC_706_myHashMap() {
        array = new Node[len];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int loc = hash(key);
        Node node = getNode(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            newNode.next = array[loc];
            array[loc] = newNode;
        } else {
            node.val = value;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        Node node = getNode(key);
        return node == null ? -1 : node.val;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int loc = hash(key);
        Node virNode = new Node(0,0), p = virNode;
        virNode.next = array[loc];
        while (p.next != null && p.next.key != key) {
            p = p.next;
        }
        if (p.next != null) {
            p.next = p.next.next;
        }
        array[loc] = virNode.next;
    }

    private Node getNode(int key) {
        int loc = hash(key);
        Node node = array[loc];
        while (node != null && node.key != key) {
            node = node.next;
        }
        return node;

    }
    private int hash(int key) {
        return key%len;
    }
}
