package secondround.sr1_300;

import domain.ListNode;

public class sr_019 {
    class Solution {
        /**
         * 快慢指针
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode virtualNode = new ListNode(0);
            virtualNode.next = head;
            ListNode p = virtualNode, q = virtualNode;
            while (n-- > 0) {
                q = q.next;
            }
            while (q.next != null) {
                q = q.next;
                p = p.next;
            }
            p.next = p.next.next;
            return virtualNode.next;
        }
    }
}
