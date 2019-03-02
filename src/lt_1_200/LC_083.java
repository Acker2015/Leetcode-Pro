package lt_1_200;

import domain.ListNode;

public class LC_083 {
	/**
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode pre = head, p = head.next;
        while (p != null) {
            if (pre.val == p.val) {
                pre.next = p.next;
                p = pre.next;
            } else {
                pre = p;
                p = p.next;
            }
        }
        return head;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
