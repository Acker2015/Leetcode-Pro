package lt_1_100;

import domain.ListNode;
/**
 * insert a pre Node before the head, just swap two node
 * @author Acker
 *
 */
public class LC_024 {
	public ListNode swapPairs(ListNode head) {
        ListNode preNode = new ListNode(0);
        preNode.next = head;
        ListNode p = preNode;
        while (p!=null && p.next!=null && p.next.next != null) {
            ListNode tmpNode = p.next.next;
            p.next.next = tmpNode.next;
            tmpNode.next = p.next;
            p.next = tmpNode;
            p = p.next.next;
        }
        return preNode.next;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
