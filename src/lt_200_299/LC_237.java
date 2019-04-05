package lt_200_299;

import domain.ListNode;

public class LC_237 {
    /**
     * 删除node
     * 将node.next值更新到node中，将问题转化为删除node.next
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
