package lt_300_399;


import domain.ListNode;

public class LC_328 {
    /**
     * 1.将奇数节点保留在原链表-并记录有效尾节点
     * 2.将偶数节点连接到新的链表
     * 3.最后将两个链表连接
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode virtualEvenNode = new ListNode(0), evenPre = virtualEvenNode;
        ListNode oddPre = head;
        while (oddPre.next != null) {
            ListNode tmp = oddPre.next;
            oddPre.next = oddPre.next.next;
            // 保证oddPre有效性
            if (oddPre.next != null) {
                oddPre = oddPre.next;   // 保留有效尾节点,用于连接偶数索引做成的链表
            }
            // insert tmp node into even list
            tmp.next = evenPre.next;
            evenPre.next = tmp;
            evenPre = evenPre.next;
        }
        oddPre.next = virtualEvenNode.next;
        return head;
    }
}
