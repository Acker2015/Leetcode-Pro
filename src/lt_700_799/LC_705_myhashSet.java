package lt_700_799;

import java.util.List;

/**
 *
 * [705] Design HashSet
 *
 * All values will be in the range of [0, 1000000].
 The number of operations will be in the range of [1, 10000].
 Please do not use the built-in HashSet library.
 */
public class LC_705_myhashSet {
    private class Node {
        Node next;
        int val;
        Node(int val) {
            this.val = val;
        }
    }
    private static final int LEN = 2048 * 2;  // 2^12
    private Node[] hashArray;
    /** Initialize your data structure here. */
    public LC_705_myhashSet() {
        hashArray = new Node[LEN];
    }


    public void add(int key) {
        if (!contains(key)) {
            int loc = hash(key);
            Node node = new Node(key);
            node.next = hashArray[loc];
            hashArray[loc] = node;
        }
    }

    public void remove(int key) {
        int loc = hash(key);
        Node virHead = new Node(-1);
        virHead.next = hashArray[loc];
        Node pre = virHead, cur = virHead.next;
        while (cur != null && cur.val != key) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) {
            pre.next = cur.next;
        }
        hashArray[loc] = virHead.next;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int loc = hash(key);
        Node node = hashArray[loc];
        while (node != null && node.val != key) {
            node = node.next;
        }
        return node!=null;
    }

    private int hash(int key) {
        return key % LEN;
    }

    public static void main(String...args) {
        LC_705_myhashSet myhashSet = new LC_705_myhashSet();
        myhashSet.add(1);
        myhashSet.add(2);
        System.out.println(myhashSet.contains(1));
        System.out.println(myhashSet.contains(3));
        myhashSet.add(2);
        System.out.println(myhashSet.contains(2));
        myhashSet.remove(2);
        System.out.println(myhashSet.contains(2));

    }
    /**
     * MyHashSet hashSet = new MyHashSet();
     hashSet.add(1);
     hashSet.add(2);
     hashSet.contains(1);    // returns true
     hashSet.contains(3);    // returns false (not found)
     hashSet.add(2);
     hashSet.contains(2);    // returns true
     hashSet.remove(2);
     hashSet.contains(2);    // returns false (already removed)
     *
     *
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */
}
