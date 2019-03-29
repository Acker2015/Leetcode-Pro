package lt_1_200;

import domain.ListNode;
import domain.TreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/29
 */
public class LC_109 {
    private ListNode head;
    private int getSize() {
        ListNode pre = head;
        int len = 0;
        while(pre!=null) {
            len++;
            pre = pre.next;
        }
        return len;
    }
    /**
     * 由于有序链表就可以看成BST的中序遍历
     * 只需要每次保持中点作为头，就可以保持平衡
     *
     * 总是左子树先构建，然后根节点，然后构建右子树， 这样就可以顺序遍历链表取数
     *
     * The time complexity is still O(N), since we still have to process each of the nodes in the linked list once and form corresponding BST nodes.
     */
    private TreeNode buildBST(int left, int right) {
        if (left > right) return null;
        int mid = left + (right-left)/2;
        TreeNode leftNode = buildBST(left, mid-1);
        TreeNode node = new TreeNode(head.val);
        head = head.next;
        node.left = leftNode;
        node.right = buildBST(mid+1, right);
        return node;
    }
    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;
        int size = getSize();
        return buildBST(0, size-1);
    }
}
