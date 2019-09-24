package lt_1_200;

import domain.ListNode;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/18
 */
public class LC_092 {
    /**
     * 1. 找到reverse区间起始节点以及其前驱节点
     * 2. 区间头插链表节点
     * 3. 尾链表连接
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head==null || head.next==null || m==n) return head;
        ListNode virtualNode = new ListNode(0);

        virtualNode.next = head;
        ListNode pre = virtualNode, cur = head;
        int now = 1;
        while (now < m) {
            now++;
            pre = pre.next;
            cur = cur.next;
        }
        ListNode tmpTail = cur, tmp = null;
        while (now <= n) {
            tmp = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = tmp; // 移动cur
            now++;
        }
        tmpTail.next = tmp; // reverse完毕需要将尾链接上
        return virtualNode.next;
    }
}
