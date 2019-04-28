package lt_400_499;


import domain.ListNode;

import java.util.Stack;

/**
 * [445] Add Two Numbers II
 */
public class LC_445 {
    private void transferToStack(Stack<Integer> s, ListNode node) {
        while (node!=null) {
            s.push(node.val);
            node = node.next;
        }
    }
    /**
     * solution1 使用栈实现
     * time O(n)
     * space O(m+n)
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1==null?l2:l1;
        }
        ListNode virNode = new ListNode(0);
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        transferToStack(s1, l1);
        transferToStack(s2, l2);
        int remain = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || remain > 0) {
            int sum = remain;
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }
            remain = sum/10;
            // insert node as head
            ListNode node = new ListNode(sum%10);
            node.next = virNode.next;
            virNode.next = node;
        }
        return virNode.next;
    }

    private int getListLen(ListNode node) {
        int len = 0;
        while (node!=null) {
            len++;
            node = node.next;
        }
        return len;
    }

    /**
     * solution2
     * time  O(n)
     * space O(1) 除了返回结果的新链表占用空间
     *
     * 5 -> 2 -> 4 -> 3
     *      3 -> 6 -> 4
     *
     * loop1:   7 -> 10 -> 5 -> 5
     * loop2:   7
     *          0 -> 7 (remain=1)
     *          6 -> 0 -> 7 (remain=0)
     *          5 -> 6 -> 0 -> 7
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1==null?l2:l1;
        }
        int len1 = getListLen(l1);
        int len2 = getListLen(l2);
        ListNode virNode = new ListNode(-1);
        ListNode cur1 = l1, cur2 = l2;
        while (len1 > 0 || len2 > 0) {
            int sum = 0;
            if (len1 == len2) {
                sum += cur1.val + cur2.val;
                cur1 = cur1.next;
                cur2 = cur2.next;
                len1--;
                len2--;
            } else if (len1 > len2) {
                sum += cur1.val;
                cur1 = cur1.next;
                len1--;
            } else {
                sum += cur2.val;
                cur2 = cur2.next;
                len2--;
            }
            ListNode node = new ListNode(sum);
            node.next = virNode.next;
            virNode.next = node;
        }
        ListNode retVirNode = new ListNode(-1);
        int remain = 0;
        while (virNode.next != null) {
            ListNode tmp = virNode.next;
            virNode.next = tmp.next;
            int sum = tmp.val + remain;
            remain = sum/10;
            tmp.val = sum%10;
            tmp.next = retVirNode.next;
            retVirNode.next = tmp;
        }
        if (remain > 0) {
            ListNode node = new ListNode(remain);
            node.next = retVirNode.next;
            retVirNode.next = node;
        }
        return retVirNode.next;
    }
}
