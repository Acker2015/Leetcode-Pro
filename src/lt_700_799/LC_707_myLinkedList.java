package lt_700_799;

/**
 *
 * [707] Design Linked List
 *
 MyLinkedList linkedList = new MyLinkedList();
 linkedList.addAtHead(1);
 linkedList.addAtTail(3);
 linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
 linkedList.get(1);            // returns 2
 linkedList.deleteAtIndex(1);  // now the linked list is 1->3
 linkedList.get(1);            // returns 3

 All values will be in the range of [1, 1000].
 The number of operations will be in the range of [1, 1000].
 Please do not use the built-in LinkedList library.
 */
public class LC_707_myLinkedList {
    private class Node {
        int val;
        Node next;
        //Node prev;
        Node(int val) {
            this.val = val;
            this.next = null;
            //this.prev = null;
        }
    }
    private int length;
    private Node virtualNode = new Node(-1);
    private Node tail;
    /** Initialize your data structure here. */
    public LC_707_myLinkedList() {
        tail = null;
        length = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= this.length || index < 0) return -1;
        Node pre = virtualNode;
        while (index-- >= 0) {
            pre = pre.next;
        }
        return pre.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = virtualNode.next;
        virtualNode.next = node;
        if (this.length == 0) {
            this.tail = node;
        }
        this.length++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if (this.length == 0) {
            addAtHead(val);
        } else {
            tail.next = new Node(val);
            tail = tail.next;
            this.length++;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        // test case with bug
        if (index < 0) {
            addAtHead(val);
        } else if (index <= this.length) {
            Node pre = this.virtualNode;
            while (index-- > 0) {
                pre = pre.next;
            }
            Node node = new Node(val);
            node.next = pre.next;
            pre.next = node;
            if (node.next == null) {
                this.tail = node;
            }
            this.length++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < this.length && index >= 0) {
            Node pre = this.virtualNode;
            while (index-- > 0) {
                pre = pre.next;
            }
            pre.next = pre.next.next;
            if (pre.next == null) {
                this.tail = pre;
            }
            this.length--;
        }
    }
    // ["MyLinkedList","addAtIndex","get","deleteAtIndex"]\n[[],[-1,0],[0],[-1]]
    // [null,null,0,null]
    public static void main(String ...args) {
        LC_707_myLinkedList linkedList = new LC_707_myLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
        System.out.println(linkedList.get(1));            // returns 2
        linkedList.deleteAtIndex(1);  // now the linked list is 1->3
        System.out.println(linkedList.get(1));            // returns 3
    }
}
