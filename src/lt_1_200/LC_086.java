package lt_1_200;

import domain.ListNode;

public class LC_086 {
	/**
     * 遍历链表
     * 将节点值小于x的节点转移到以node2开头的链表上
     * 最后连接两个链表即可
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode node1 = new ListNode(0), node2 = new ListNode(0);
        node1.next = head;
        ListNode p = node1, q = node2;
        while (p.next != null) {
            ListNode tmpNode = p.next;
            // 节点值小于x的节点放到node2链表上
            if (tmpNode.val < x) {
                p.next = tmpNode.next;
                tmpNode.next = q.next;
                q.next = tmpNode;
                q = q.next;
            } else {
                p = p.next;
            }
        }
        // 连接两个链表
        q.next = node1.next;
        return node2.next;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
