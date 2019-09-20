package secondround.sr1_300;

import domain.ListNode;

public class sr_021 {
    /**
     * 原链表操作
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode p = l1.val <= l2.val ? l1:l2;
        ListNode q = l1.val <= l2.val ? l2:l1;
        ListNode ans = p;
        while (q != null) {
            if (p.next == null) {
                p.next = q;
                break;
            } else if (p.next.val <= q.val) {
                p = p.next;
            } else {
                ListNode tmp = q.next;
                q.next = p.next;
                p.next = q;
                q = tmp;
            }
        }
        return ans;
    }
}


