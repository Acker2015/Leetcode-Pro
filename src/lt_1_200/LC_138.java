package lt_1_200;

import domain.Node;

/**
 * 138-Copy List with Random Pointer
 */
public class LC_138 {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node curNode = head;
        // A->A'->B->B'->C->C'
        while (curNode != null) {
            Node tmp = curNode.next;
            Node copyNode = new Node(curNode.val);
            curNode.next = copyNode;
            copyNode.next = tmp;
            curNode = tmp;
        }
        // copy random point
        curNode = head;
        while (curNode!=null) {
            if (curNode.random != null) {
                curNode.next.random = curNode.random.next;
            }
            curNode = curNode.next.next;
        }
        // separate the origin list and copy list
        Node copyHead = head.next;
        curNode = head;
        while (curNode!=null) {
            Node tmp = curNode.next;
            curNode.next = tmp.next;
            tmp.next = curNode.next==null?null:curNode.next.next;
            curNode = curNode.next;
        }
        return copyHead;
    }
}
