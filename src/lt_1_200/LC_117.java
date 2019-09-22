package lt_1_200;

import domain.Node;

public class LC_117 {
    /**
     * [117] Populating Next Right Pointers in Each Node II
     *
     * level traversal
     * 使用虚拟节点virtualHead记录每一层的开始节点，方便下一次层序遍历找到下一层的起始节点
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Node lastLevelNode = root;
        Node virtualHead = new Node(0), pre = virtualHead;
        while (lastLevelNode != null) {
            if (lastLevelNode.left != null) {
                pre.next = lastLevelNode.left;
                pre = pre.next;
            }
            if (lastLevelNode.right != null) {
                pre.next = lastLevelNode.right;
                pre = pre.next;
            }
            lastLevelNode = lastLevelNode.next;
            // 如果本层结束，那么就切换到下一层
            if (lastLevelNode == null) {
                // go down to next level
                lastLevelNode = virtualHead.next;
                virtualHead.next = null;
                pre = virtualHead;
            }
        }
        return root;
    }
}
