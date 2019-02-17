package lt_1_100;

import java.awt.print.Printable;

import domain.ListNode;

/**
 * sort list
 * related: merge two list
 * 1. cut the list into two halves
 * 2. sort each half
 * 3. merge two sorted sub-half-list
 * @author Acker
 *
 */
public class LC_148 {
	/**
	 * merge two list using recursion
	 * @param node1
	 * @param node2
	 * @return
	 */
	private ListNode mergeRecursive(ListNode node1, ListNode node2) {
		if (node1 == null) return node2;
		if (node2 == null) return node1;
		if (node1.val <= node2.val) {
			node1.next = mergeRecursive(node1.next, node2);
			return node1;
		} else {
			node2.next = mergeRecursive(node1, node2.next);
			return node2;
		}
	}
	/**
	 * merge two list
	 * @param node1
	 * @param node2
	 * @return
	 */
	private ListNode mergeList(ListNode node1, ListNode node2) {
		ListNode head = new ListNode(0), p = head;
		while (node1 != null && node2 != null) {
			if (node1.val <= node2.val) {
				p.next = node1;
				node1 = node1.next;
			} else {
				p.next = node2;
				node2 = node2.next;
			}
			p = p.next;
			p.next = null;
		}
		p.next = node1==null ? node2 : node1;
		return head.next;
	}
	/**
	 * sort function
	 * @param head
	 * @return
	 */
	public ListNode sortList(ListNode head) {
		if (head==null || head.next==null) return head;
		ListNode slow = head, fast = head, prev = null;
		while(fast!=null && fast.next!=null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next=null;
		ListNode node1 = sortList(head);
		ListNode node2 = sortList(slow);
		return mergeList(node1, node2);
	}
	public void print(ListNode head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	public static void main(String[] args) {
		ListNode head = new ListNode(0), pre = head;
		int[] arr = {-1,5,3,4,0,7};
		for (int a: arr) {
			ListNode node = new ListNode(a);
			pre.next = node;
			pre = node;
		}
		LC_148 lc_148 = new LC_148();
		ListNode newHead = lc_148.sortList(head.next);
		lc_148.print(newHead);

	}

}
