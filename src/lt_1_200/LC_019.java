package lt_1_200;

import domain.ListNode;

/**
 * 经典题目-寻找链表倒数第k个数
 * 两个指针，一个快指针，一个慢指针，让两者相差距离为k，让快指针走到最后即可
 * 注意：
 * 	1、指针的步数要先计算一下
 *  2、删除第k个数，说明慢指针要寻找到倒数第k个数的前一个节点(方便删除)
 * @author Acker
 *
 */
public class LC_019 {
	public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nullNode = new ListNode(0);
        nullNode.next = head;
        ListNode p = nullNode, q = nullNode;
        for (int i = 0; i < n; ++i) {
            p = p.next;
        }
        while(p.next != null) {
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return nullNode.next;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
