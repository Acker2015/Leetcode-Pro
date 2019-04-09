package lt_1_200;


import domain.ListNode;

import java.util.LinkedList;
import java.util.List;

public class LC_143 {
    // 获取链表长度
    private int getListLen(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
    // 将node为起点的链表翻转 4->5 => 5->4
    private ListNode reverseListNode(ListNode node) {
        ListNode virNode = new ListNode(0);
        while (node != null) {
            ListNode tmp = node.next;
            node.next = virNode.next;
            virNode.next = node;
            node = tmp;
        }
        return virNode.next;
    }

    /**
     * [143] Reorder List
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next==null) return;
        int len = getListLen(head);
        ListNode pre = head;
        int step = len/2;
        while (step-- > 0) {
            pre = pre.next;
        }
        // 后半部分链表翻转
        ListNode lastHead = reverseListNode(pre.next);
        pre.next = null;
        pre = head;
        // 前半部分与后半部分组合
        while (lastHead!=null) {
            ListNode ansNode = lastHead.next;
            lastHead.next = pre.next;
            pre.next = lastHead;
            lastHead = ansNode;
            pre = pre.next.next;
        }
    }

    public ListNode createNode(int[] arr) {
        ListNode virNode = new ListNode(0), pre = virNode;
        for (int a: arr) {
            ListNode node = new ListNode(a);
            pre.next = node;
            pre = node;
        }
        return virNode.next;
    }
    public void printNode(ListNode node) {
        List<String> list = new LinkedList<>();
        while(node!=null) {
            list.add(String.valueOf(node.val));
            node = node.next;
        }
        System.out.println(String.join("->", list));
    }
    public static void main(String ...args) {
        int[] arr = {1,2};
        LC_143 lc_143 = new LC_143();
        ListNode head = lc_143.createNode(arr);
        lc_143.printNode(head);
        lc_143.reorderList(head);
        lc_143.printNode(head);
    }
}
