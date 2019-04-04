package lt_200_299;

import domain.ListNode;

public class LC_203 {
    /**
     * [203] Remove Linked List Elements
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode virtualHead = new ListNode(0), pre = virtualHead;
        virtualHead.next = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next; // delete
            } else {
                pre = pre.next;
            }
        }
        return virtualHead.next;
    }
}
