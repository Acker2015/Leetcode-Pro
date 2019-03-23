package lt_1_200;

import domain.ListNode;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/23
 */
public class LC_160 {
    private int getLen(ListNode node) {
        ListNode p = node;
        int len = 0;
        while (p != null) {
            p = p.next;
            len++;
        }
        return len;
    }

    /**
     * 解法一： 思想很简单，让两个链表遍历的起始点的后续长度相等，就可以同步遍历
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = getLen(headA);
        int len2 = getLen(headB);
        if (len1 == 0 || len2 == 0) return null;
        ListNode shortListNode = len1<=len2 ? headA:headB;
        ListNode longListNode = len1<=len2 ? headB:headA;
        int distance = Math.abs(len1-len2);
        while (distance-- > 0) {
            longListNode = longListNode.next;
        }
        while (shortListNode!=longListNode) {
            shortListNode = shortListNode.next;
            longListNode = longListNode.next;
        }
        return longListNode;
    }

    /**
     * 解法二：
     * 利用唤醒链表的思想
     * 想让两个链表的遍历同步就是
     * 遍历headA的指针走到头之后，接着遍历headB
     * 那么两个指针走的距离都是headA+headB
     * 如果存在相同起点，那么一定会交换之后相遇
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA==null || headB==null) return null;
        ListNode p = headA, q = headB;
        while (p!=q) {
            p = (p==null ? headB:p.next);
            q = (q==null ? headA:q.next);
        }
        return p;
    }
}
