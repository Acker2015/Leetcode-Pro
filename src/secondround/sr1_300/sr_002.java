package secondround.sr1_300;

import domain.ListNode;

public class sr_002 {
    private int lenOfList(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        int len1 = lenOfList(l1);
        int len2 = lenOfList(l2);
        ListNode f1 = len1>len2 ? l1:l2;
        ListNode f2 = len1>len2 ? l2:l1;
        int carry = 0;
        ListNode preNode = l1;
        while (f1 != null) {
            int sum = carry + f1.val + (f2==null? 0:f2.val);
            carry = sum/10;
            f1.val = sum%10;
            preNode = f1;
            f1 = f1.next;
            if (f2 != null) {
                f2 = f2.next;
            }
        }
        if (carry > 0) {
            ListNode node = new ListNode(carry);
            preNode.next = node;
        }
        return len1>len2 ? l1:l2;
    }

    public static class Solution2 {
        /**
         * 直接利用原链表来改
         * @param l1
         * @param l2
         * @return
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) return null;
            ListNode head = new ListNode(0);
            head.next = l1;
            ListNode preNode = head;
            int carry = 0, sum = 0;
            while (l1 != null) {
                // 提前结束
                if (l2 == null && carry == 0) {
                    break;
                }
                sum = carry + l1.val + (l2!=null ? l2.val:0);
                carry = sum / 10;
                l1.val = sum % 10;
                preNode = l1;
                // 考虑l1比l2短的情况，直接将l2的后续部分链接到l1上
                if (l1.next == null && l2 != null) {
                    l1.next = l2.next;
                    l2 = null;
                } else {
                    if (l2 != null) {
                        l2 = l2.next;
                    }
                }
                l1 = l1.next;
            }
            if (carry != 0) {
                preNode.next = new ListNode(carry);
            }
            return head.next;

        }
    }
}
