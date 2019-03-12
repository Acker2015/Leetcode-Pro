package lt_1_200;

import domain.ListNode;

public class LC_142 {
	/**
     * 可以推倒 
     * P1: 使用快慢指针如果可以相遇，那么说明存在环
     * P2: 相遇之后使得一个指针从头开始走，慢指针同时跟着走。肯定可以在环开始的地方相遇
     * 
     * https://www.cnblogs.com/xudong-bupt/p/3667729.html
     * 系列问题
     * 1. 链表是否存在环
     * 2. 求环的长度
     * 3. 求链表头到环入口的长度
     * 4. 求链表的总长度(其中元素的数量)
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (slow != fast) return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
