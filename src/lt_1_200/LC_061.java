package lt_1_200;

import domain.ListNode;

/*
 * @lc app=leetcode id=61 lang=java
 *
 * [61] Rotate List
 *
 * https://leetcode.com/problems/rotate-list/description/
 *
 * algorithms
 * Medium (26.50%)
 * Total Accepted:    178.5K
 * Total Submissions: 673.2K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * Given a linked list, rotate the list to the right by k places, where k is
 * non-negative.
 * 
 * Example 1:
 * 
 * 
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 * 
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class LC_061 {
    public int getLength(ListNode node) {
        if (node == null) return 0;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
    public ListNode rotateRight(ListNode head, int k) {
        int len = getLength(head);
        if (len == 0 || k % len == 0) return head;
        k = k % len;
        ListNode nullNode = new ListNode(0), pre = nullNode, last = nullNode;
        nullNode.next = head;
        for (int i = 0; i < k; ++i) {
            last = last.next;
        }
        while (last.next != null) {
            last = last.next;
            pre = pre.next;
        }
        // 截断
        ListNode sNode = pre.next;
        pre.next = null;
        // 将sNode->...->last一段节点插入头部
        last.next = nullNode.next;
        nullNode.next = sNode;
        return nullNode.next;
    }
}
