package secondround.sr1_300;

import domain.ListNode;

public class sr_025 {
    class Solution {
        private int getLen(ListNode node) {
            int len = 0;
            while (node != null) {
                len++;
                node = node.next;
            }
            return len;
        }
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k == 1) return head;
            int len = getLen(head);
            ListNode virtualNode = new ListNode(0);
            ListNode node = head, preNode = virtualNode;
            while (node != null) {
                if (len < k) {
                    preNode.next = node;
                    break;
                } else {
                    ListNode nextPreNode = node;
                    for (int i = 0; i < k; ++i) {
                        ListNode tmp = node.next;
                        // head insert
                        node.next = preNode.next;
                        preNode.next = node;
                        // keep going
                        node = tmp;
                    }
                    preNode = nextPreNode;
                }
                len -= k;
            }
            return virtualNode.next;
        }
    }
}
