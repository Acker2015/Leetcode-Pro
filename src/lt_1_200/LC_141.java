package lt_1_200;

import domain.ListNode;

public class LC_141 {

	/**
     * two pointers 快慢指针 O(n+k)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode p1 = head, p2 = head.next;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
