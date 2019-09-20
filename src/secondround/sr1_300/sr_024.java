package secondround.sr1_300;

import domain.ListNode;

public class sr_024 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode virtualNode = new ListNode(0);
        ListNode p = head, q = head.next, preNode = virtualNode;

        while (p != null) {
            if (q == null) {
                preNode.next = p;
                break;
            } else {
                ListNode tmp = q.next;
                preNode.next = q;
                q.next = p;
                p.next = null;  // cut off the origin connection
                preNode = p;
                if (tmp == null) break;
                p = tmp;
                q = tmp.next;
            }
        }
        return virtualNode.next;
    }
}
