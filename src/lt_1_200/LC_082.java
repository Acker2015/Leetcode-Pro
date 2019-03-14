package lt_1_200;

import domain.ListNode;

public class LC_082 {
	/**
	 * 遇到重复数字选择跳过，注意记录前驱节点即可
	 * Remove Duplicates from Sorted List II
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead, p;
        while (pre != null && pre.next != null) {
            p = pre.next;
            while (p.next != null && p.val == p.next.val) p = p.next;
            if (pre.next == p) {
                pre = p;
            } else {
                pre.next = p.next;
            } 
        }
        return preHead.next;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
