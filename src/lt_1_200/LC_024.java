package lt_1_200;

import domain.ListNode;
/**
 * insert a pre Node before the head, just swap two node
 * @author Acker
 *
 */
public class LC_024 {
    public ListNode swapPairs(ListNode node) {
        ListNode virtualNode = new ListNode(0);
        ListNode pre = virtualNode;
        while (node != null && node.next!=null) {
            ListNode nextPre = node;
            ListNode p = node.next;
            // first node
            node.next = pre.next;
            pre.next = node;
            // set new node
            node = p.next;
            // second node
            p.next = pre.next;
            pre.next = p;
            // set new preNode
            pre = nextPre;
        }
        pre.next = node;
        return virtualNode.next;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
