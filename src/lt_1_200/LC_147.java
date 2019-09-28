package lt_1_200;


import domain.ListNode;

/**
 * [147] Insertion Sort List
 *
 * 普通插入排序
 */
public class LC_147 {

    // 寻找插入位置
    private ListNode findPreValue(ListNode node, int val) {
        while (node.next != null && node.next.val < val) {
            node = node.next;
        }
        return node;
    }
    public ListNode insertionSortList(ListNode head) {
        ListNode virNode1 = new ListNode(Integer.MIN_VALUE);
        while (head != null) {
            ListNode tmp = head.next;

            ListNode ans = findPreValue(virNode1, head.val);
            head.next = ans.next;
            ans.next = head;
            head = tmp;
        }
        return virNode1.next;
    }
}
